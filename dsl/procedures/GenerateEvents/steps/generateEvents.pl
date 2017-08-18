use strict;
use warnings;
use ElectricCommander;
use JSON;
use URI::Escape;

my $ec = ElectricCommander->new;

my $eventName = '$[eventName]';
my $method = '$[method]';
my $url = '$[url]';
my $body = '$[body]';
my $headersString = '$[httpHeaders]';
my $credentialsName = '$[overrideCredential]';
my $property = '$[resultProperty]';
my $projectName= '$[projName]';
my $pipelineName = '$[pipelineNameRun]';
my $flagProperty = '$[flagProperty]';

my $hidden = '';
my $event;

if ($projectName && $pipelineName) {
    print "Generating event for run pipeline\n";
    $event = {name => $eventName, url => getPipelineUrl($projectName, $pipelineName), method => 'POST'};
}
elsif ($flagProperty) {
    print "Generating event for setting property\n";
    $event = {name => $eventName, url => getPropertyUrl($flagProperty), method => 'PUT'};
}
else {
    print "Generating custom event\n";
    $event = {name => $eventName, url => $url };
    if ($method) {
        $event->{method} = $method;
    }
}
if ($body) {
    $event->{body} = uri_escape($body);
}

my $headers = getHeaders($headersString);
my $credentials = getCredentials($credentialsName);
if ($headers) {
    $event->{httpHeaders} = $headers;
}
if ($credentials) {
    $event->{credentials} = $credentials;
}

my $events = getEvents($property);
push @$events, $event;
print "Events: " . printEvents($events) . "\n\n";

$ec->setProperty($property, encode_json($events));
print "Saved events to property $property\n";


sub printEvents {
    my ($events) = @_;

    my $json = JSON->new->utf8->pretty->encode($events);
    return $json;
}

sub getEvents {
    my ($property) = @_;

    my $events_json = '';
    eval {
        my $xpath = $ec->getProperty($property);
        $events_json = $xpath->find('//value')->string_value;
        1;
    };
    my $events = [];
    eval {
        $events = decode_json($events_json);
        1;
    };
    return $events;
}

sub getHeaders {
    my ($headers_string) = @_;
    return unless $headers_string;

    my $retval = [];
    eval {
        my $headers = decode_json($headers_string);
        for (keys %$headers) {
            push @$retval, {value => $headers->{$_}, name => $_};
        }
        1;
    } or do {
        my @lines = split(/\n+/, $headers_string);
        for my $line (@lines) {
            my ($key, $value) = split(/\s*=\s*/, $line);
            push @$retval, {name => $key, value => $value};
        }
    };
    return $retval;
}


sub getCredentials {
    my ($credentials_name) = @_;

    return unless $credentials_name;
    my $xpath = $ec->getFullCredential($credentials_name);
    my $userName = $xpath->findvalue('//userName')->string_value;
    my $password = $xpath->findvalue('//password')->string_value;
    $hidden = $password;
    return {username => $userName, password => $password};
}


sub getPipelineUrl {
    my ($projectName, $pipelineName) = @_;

    my $servername = $ENV{COMMANDER_SERVER};
    my $path = 'rest/v1.0/pipelines';
    my $uri = URI->new('https://' . $servername . '/' . $path);
    $uri->query_form(projectName => $projectName, pipelineName => $pipelineName);
    return "$uri";
}


sub getPropertyUrl {
    my ($propertyName) = @_;

    my $servername = $ENV{COMMANDER_SERVER};
    my $path = 'rest/v1.0/properties/';
    my $uri = URI->new("https://$servername/$path" . uri_escape($propertyName));
    my $value = $eventName =~ /completed|closed|always/i ? 'success' : 'failure';
    $uri->query_form(value => $value);
    return "$uri";
}
