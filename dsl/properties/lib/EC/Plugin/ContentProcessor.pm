package EC::Plugin::ContentProcessor;

use strict;
use warnings;
use JSON;

use base qw(EC::Plugin::ContentProcessorCore);

package EC::Plugin::ContentProcessor;

use strict;
use warnings;
use JSON;
use Data::Dumper;

use base qw(EC::Plugin::ContentProcessorCore);


=head1 SYNOPSYS

Here one can define custom processors for request & response. E.g., request
is not a plain JSON object but a file, or response does not contain JSON.

By default we assume that request body should be in JSON format and
response returns JSON as well.


Two processors can be defined:
    serialize_body - which will be used to serialize request body
    parse_response - which will be used to parse the content of the response

Code may look like the following:

    use constant {
        RETRIEVE_ARTIFACT => 'retrieve artifact',
        DEPLOY_ARTIFACT => 'deploy artifact',
    };


    sub define_processors {
        my ($self) = @_;

        $self->define_processor(DEPLOY_ARTIFACT, 'serialize_body', \&deploy_artifact);
        $self->define_processor(RETRIEVE_ARTIFACT, 'parse_response', \&download_artifact);
    }

    sub deploy_artifact {
        my ($self, $body) = @_;

        my $path = $body->{filesystemArtifact};

        open my $fh, $path or die "Cannot open $path: $!";
        binmode $fh;

        my $data = '';
        my $buffer;
        while( my $bytes_read = read($fh, $buffer, 1024)) {
            $data .= $buffer;
        }

        close $fh;


        # Here we return file content instead of JSON object
        return $data;
    }

    sub download_artifact {
        my ($self, $response) = @_;

        my $directory = $self->plugin->parameters(RETRIEVE_ARTIFACT)->{destination};

        $self->plugin->logger->debug("Destintion is $directory");
        my $filename = $response->header('x-artifactory-filename');


        my $dest_filename = $directory ? "$directory/$filename" : $filename;

        # And here we write a file instead of parsing response body as JSON

        open my $fh, ">$dest_filename" or die "Cannot open $dest_filename: $!";
        print $fh $response->content;
        close $fh;

        $self->plugin->logger->info("Artifact $dest_filename is saved");
        $self->plugin->set_summary("Artifact $dest_filename is saved");
    }


=cut


# autogen code ends here

sub define_processors {
    my ($self) = @_;

    my @steps_with_nested_elements = (
        'Deploy assignment',
        'Deploy release',
        'Fallback set',
        'Generate tasks in assignment',
        'Generate tasks in release',
        'Promote assignment',
        'Promote release',
        'Regress assignment',
        'Regress release',
    );

    for my $step_name (@steps_with_nested_elements) {
        $self->define_processor( $step_name, 'serialize_body', \&add_nested_elements );
    }

    $self->define_processor( 'Create release', 'serialize_body', \&replace_owner );
    $self->define_processor( 'Create assignment', 'serialize_body', \&replace_owner );
}

sub replace_owner {
    my ($self, $body) = @_;

    my $retval = $body;
    my $owner = $body->{_owner};
    delete $retval->{_owner};
    $retval->{owner} = $owner if $owner;

    return encode_json($retval);
}

sub add_nested_elements {
    my ($self, $body) = @_;

    my $retval = $body;
    my $params = $self->plugin->parameters;

    my $headers = $params->{httpHeaders};

    my @lines = split /\n+/, $headers;
    my @httpHeaders = map {
        my ($key, $value) = split /\s*=\s*/, $_;
        {name => $key, value => $value}
    } @lines;

    if (scalar(@httpHeaders) > 0) {
        $retval->{httpHeaders} = \@httpHeaders;
    }

    if ($params->{callbackCredentialUserName} && $params->{callbackCredentialPassword}) {
        $retval->{credentials} = { };
        $retval->{credentials}->{username} = $params->{callbackCredentialUserName};
        $retval->{credentials}->{password} = $params->{callbackCredentialPassword};
    }

    my $events;
    eval {
        my $ec = ElectricCommander->new;
        my $eventsRaw = $params->{events} ||= $ec->getPropertyValue('events');
        print "Events JSON raw: $eventsRaw\n";
        $events = decode_json($eventsRaw);
        1;
    } or do {
        $self->plugin->bail_out( "Events field couldn't be empty and must be JSON. See ISPW documentation." );
    };
    $retval->{events} = $events;
    print Dumper $events;

    return encode_json($retval);
}


1;
