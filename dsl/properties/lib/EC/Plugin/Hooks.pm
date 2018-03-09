package EC::Plugin::Hooks;

use strict;
use warnings;
use MIME::Base64 qw(encode_base64);
use JSON;
use base qw(EC::Plugin::HooksCore);

=head1 SYNOPSYS

User-defined hooks

Available hooks types:

    before
    parameters
    request
    response
    parsed
    after

    ua - will be called when User Agent is created

    Accepts step name, hook name, hook code, options

    sub define_hooks {
        my ($self) = @_;

        $self->define_hook('my step', 'before', sub { ( my ($self) = @_; print "I'm before step my step" }, {run_before_shared => 1});
    }


    step name - the name of the step. If value "*" is specified, the hook will be "shared" - it will be executed for every step
    hook name - the name of the hook, see Available hook types
    hook code - CODEREF with the hook code
    options - hook options
        run_before_shared - this hook ("own" step hook) will be executed before shared hook (the one marked with "*")




=head1 SAMPLE


    sub define_hooks {
        my ($self) = @_;

        # step name is 'deploy artifact'
        # hook name is 'request'
        # This hook accepts HTTP::Request object
        $self->define_hook('deploy artifact', 'request', \&deploy_artifact);
    }

    sub deploy_artifact {
        my ($self, $request) = @_;

        # $self is a EC::Plugin::Hooks object. It has method ->plugin, which returns the EC::RESTPlugin object
        my $artifact_path = $self->plugin->parameters($self->plugin->current_step_name)->{filesystemArtifact};

        open my $fh, $artifact_path or die $!;
        binmode $fh;
        my $buffer;
        $self->plugin->logger->info("Writing artifact $artifact_path to the server");

        $request->content(sub {
            my $bytes_read = read($fh, $buffer, 1024);
            if ($bytes_read) {
                return $buffer;
            }
            else {
                return undef;
            }
        });
    }


=cut

# autogen end

sub define_hooks {
    my ($self) = @_;

    $self->define_hook('*', 'request', \&add_authentication);
    $self->define_hook('*', 'response', \&process_response);
    $self->define_hook('Get assignment task list', 'response', \&correct_json_list_response, {run_before_shared => 0});
    $self->define_hook('Get release task list', 'response', \&correct_json_list_response, {run_before_shared => 0});
    $self->define_hook('Get set task list', 'response', \&correct_json_list_response, {run_before_shared => 0});
    $self->define_hook('Create release', 'parameters', \&check_create_release);
    $self->define_hook('Get assignment information', 'parsed', \&create_assignment_info_report);
    $self->define_hook('Get assignment task information', 'parsed', \&create_task_info_report);
    $self->define_hook('Get release task generate listing', 'parsed', \&create_release_task_generate_listing_report);    
    $self->define_hook('Get release task information', 'parsed', \&create_task_info_report);
    $self->define_hook('Get release information', 'parsed', \&create_release_info_report);
    $self->define_hook('Get set deployment information', 'parsed', \&create_set_deployment_info_report);
    $self->define_hook('Get set information', 'parsed', \&create_set_info_report);
}

sub process_response {
    my ($self, $response) = @_;

    return if $response->is_success;

    my $json_error;
    eval {
        $json_error = decode_json($response->content);
        my $message = $json_error->{message};
        if ($message) {
            $self->save_error($message);
            
            $self->plugin->logger->trace( $response->as_string );
            $self->plugin->bail_out( $message );
        }
        1;
    };
}

sub save_error {
    my ($self, $message) = @_;
    my $step_name = $self->plugin->current_step_name;

    my $config = $self->plugin->config->{$step_name}->{resultProperty};
    return unless $config && $config->{show};
    my $property_name = $self->plugin->parameters( $step_name )->{+'resultPropertySheet'};
    
    $self->plugin->ec->setProperty( $property_name, $message );
}

sub correct_json_list_response {
    my ($self, $response) = @_;
    
    if ($response->content =~ /^({"[a-z][A-Za-z]+s":)({.+})(})$/) {
        $response->{'_content'} = $1 . "[" . $2 . "]" . $3;
    }
}    
    
sub check_create_release {
    my ($self, $params) = @_;

    unless ($params->{releasePrefix} || $params->{releaseId}) {
        $self->plugin->bail_out("Release ID or Release prefix must be specified.");
    }
}

sub create_assignment_info_report {
    my ($self, $parsed) = @_;

    my $params = {};
    for my $key (keys %$parsed) {
        my $template_key = (index($key, 'assignment') == 0) ? $key : 'assignment' . ucfirst($key);
        $params->{$template_key} = $parsed->{$key};
    }

    my $report = $self->plugin->render_template_from_property('/projects/@PLUGIN_NAME@/resources/assignmentInfoReport', $params);

    mkdir 'artifacts' or die "Cannot create directory: $!";
    my $random_postfix = $self->plugin->gen_random_numbers();
    my $report_filename = "assignmentInfoReport_$random_postfix.html";
    open my $fh, ">artifacts/$report_filename" or die "Cannot open $report_filename: $!";
    print $fh $report;
    close $fh;

    my $job_step_id = $ENV{COMMANDER_JOBSTEPID};
    my $link = "/commander/jobSteps/$job_step_id/$report_filename";
    my $name = "Assignment Task Info: $parsed->{taskId}";
    $self->plugin->ec->setProperty("/myJob/report-urls/$name", $link);
    eval {
        #### TODO What about if we're not running in a pipeline? Add to job as well
        
        $self->plugin->ec->setProperty("/myPipelineStageRuntime/ec_summary/Evidence from ISPW",
        qq{<html><a href="$link" target="_blank">Link to Assignment details</a></html>}
        );
    };

}

sub create_release_info_report {
    my ($self, $parsed) = @_;

    my $params = {};
    for my $key (keys %$parsed) {
        my $template_key = (index($key, 'release') == 0) ? $key : 'release' . ucfirst($key);
        $params->{$template_key} = $parsed->{$key};
    }

    my $report = $self->plugin->render_template_from_property('/projects/@PLUGIN_NAME@/resources/releaseInfoReport', $params);

    mkdir 'artifacts' or die "Cannot create directory: $!";
    my $random_postfix = $self->plugin->gen_random_numbers();
    my $report_filename = "releaseInfoReport_$random_postfix.html";
    open my $fh, ">artifacts/$report_filename" or die "Cannot open $report_filename: $!";
    print $fh $report;
    close $fh;

    my $job_step_id = $ENV{COMMANDER_JOBSTEPID};
    my $link = "/commander/jobSteps/$job_step_id/$report_filename";
    my $name = "Release Task Info: $parsed->{taskId}";
    $self->plugin->ec->setProperty("/myJob/report-urls/$name", $link);
    eval {
        #### TODO What about if we're not running in a pipeline? Add to job as well
        
        $self->plugin->ec->setProperty("/myPipelineStageRuntime/ec_summary/Evidence from ISPW",
        qq{<html><a href="$link" target="_blank">Link to Release details</a></html>}
        );
    };

}

sub create_release_task_generate_listing_report {
    my ($self, $parsed) = @_;

    my $params = {};
    for my $key (keys %$parsed) {
        my $template_key = (index($key, 'task') == 0) ? $key : 'task' . ucfirst($key);
        $params->{$template_key} = $parsed->{$key};
    }

    my $report = $self->plugin->render_template_from_property('/projects/@PLUGIN_NAME@/resources/releaseTaskGenerateListingReport', $params);

    mkdir 'artifacts' or die "Cannot create directory: $!";
    my $random_postfix = $self->plugin->gen_random_numbers();
    my $report_filename = "releaseTaskGenerateReport_$random_postfix.html";
    open my $fh, ">artifacts/$report_filename" or die "Cannot open $report_filename: $!";
    print $fh $report;
    close $fh;

    my $job_step_id = $ENV{COMMANDER_JOBSTEPID};
    my $link = "/commander/jobSteps/$job_step_id/$report_filename";
    my $name = "Release Task Generate Listing: $parsed->{taskId}";
    $self->plugin->ec->setProperty("/myJob/report-urls/$name", $link);
    eval {
        #### TODO What about if we're not running in a pipeline? Add to job as well
        
        $self->plugin->ec->setProperty("/myPipelineStageRuntime/ec_summary/Evidence from ISPW",
        qq{<html><a href="$link" target="_blank">Link to Generate Listing details</a></html>}
        );
    };

}

#### TODO Add report for 'Get Set Deployment Information' ?with multi-level table?

sub create_set_deployment_info_report {
    #### TODO
}

sub create_set_info_report {
    my ($self, $parsed) = @_;

    my $params = {};
    for my $key (keys %$parsed) {
        my $template_key = (index($key, 'set') == 0) ? $key : 'set' . ucfirst($key);
        $params->{$template_key} = $parsed->{$key};
    }

    my $report = $self->plugin->render_template_from_property('/projects/@PLUGIN_NAME@/resources/setInfoReport', $params);

    mkdir 'artifacts' or die "Cannot create directory: $!";
    my $random_postfix = $self->plugin->gen_random_numbers();
    my $report_filename = "setInfoReport_$random_postfix.html";
    open my $fh, ">artifacts/$report_filename" or die "Cannot open $report_filename: $!";
    print $fh $report;
    close $fh;

    my $job_step_id = $ENV{COMMANDER_JOBSTEPID};
    my $link = "/commander/jobSteps/$job_step_id/$report_filename";
    my $name = "Set Task Info: $parsed->{taskId}";
    $self->plugin->ec->setProperty("/myJob/report-urls/$name", $link);
    eval {
        #### TODO What about if we're not running in a pipeline? Add to job as well
        
        $self->plugin->ec->setProperty("/myPipelineStageRuntime/ec_summary/Evidence from ISPW",
        qq{<html><a href="$link" target="_blank">Link to Set details</a></html>}
        );
    };

}

sub create_task_info_report {
    my ($self, $parsed) = @_;

    my $params = {};
    for my $key (keys %$parsed) {
        my $template_key = (index($key, 'task') == 0) ? $key : 'task' . ucfirst($key);
        $params->{$template_key} = $parsed->{$key};
    }

    my $type = '';
    for (qw/Program SQL IMS CICS/) {
        if ($parsed->{lc $_}) {
            $type = $_;
        }
    }
    $params->{type} = $type;
    my $report = $self->plugin->render_template_from_property('/projects/@PLUGIN_NAME@/resources/' . $params->{containerType} . 'TaskInfoReport', $params);

    mkdir 'artifacts' or die "Cannot create directory: $!";
    my $random_postfix = $self->plugin->gen_random_numbers();
    my $report_filename = $params->{containerType} . "TaskInfoReport_$random_postfix.html";
    open my $fh, ">artifacts/$report_filename" or die "Cannot open $report_filename: $!";
    print $fh $report;
    close $fh;

    my $job_step_id = $ENV{COMMANDER_JOBSTEPID};
    my $link = "/commander/jobSteps/$job_step_id/$report_filename";
    my $name = ucFirst($params->{containerType}) . " Task Info: $parsed->{taskId}";
    $self->plugin->ec->setProperty("/myJob/report-urls/$name", $link);
    eval {
        #### TODO What about if we're not running in a pipeline? Add to job as well
        
        $self->plugin->ec->setProperty("/myPipelineStageRuntime/ec_summary/Evidence from ISPW",
        qq{<html><a href="$link" target="_blank">Link to Task and }.ucFirst($params->{containerType}).qq{ details</a></html>}
        );
    };

}

sub add_authentication {
    my ($self, $request) = @_;

    $self->plugin->logger->debug('Adding auth -- ISPW');
    my $params = $self->plugin->parameters;
    my $config_name = $params->{config};
    my $config = $self->plugin->get_config_values($config_name);

    my $password = $config->{password};
    $request->header('Authorization' => $password);
}

1;
