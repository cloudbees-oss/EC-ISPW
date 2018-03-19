package EC::Plugin::ISPW;

use strict;
use warnings;
use base qw(EC::RESTPlugin);
use Data::Dumper;
use LWP::UserAgent;
use JSON;


sub step_display_task_information {
    my ($self) = @_;

    my $parameters = $self->get_params_as_hashref(qw/
        config
        containerType
        setTasksJson
        resultPropertySheet
        resultFormat
    /);

    if($parameters->{setTasksJson} eq "" || $parameters->{setTasksJson} =~ m/^\s*{\s*}\s*$/){
        my $message = "Set Tasks field must contain valid non-empty JSON object.";
        $self->_save_error($message, $parameters->{resultPropertySheet});
        $self->bail_out($message);    
    }
    
    for my $param_name (sort keys %$parameters) {
        my $value = $parameters->{$param_name};
        $self->logger->info(qq{Got parameter "$param_name" with value "$value"});
    }

    my $config = $self->get_config_values($parameters->{config});
    $self->logger->debug($config);

    my $url = URI->new($config->{instance});
    my $password = $config->{password};
    my $srid = $config->{srid};

    my $tasks_response = decode_json($parameters->{setTasksJson});
    my $tasks;
    if (ref $tasks_response eq 'HASH') {
        $tasks = $tasks_response->{tasks};
    }
    else {
        $tasks = $tasks_response;
    }

    my $ua = LWP::UserAgent->new;
    my $result = [];
    for my $task (@$tasks) {
        my $task_id = $task->{taskId};

        my $container = ($parameters->{containerType} eq 'release') ? $task->{container} : $task->{assignment};

        my $uri = URI->new($config->{instance});
        $uri->path_segments('ispw', $srid, (($parameters->{containerType} eq 'release') ? 'releases' : 'assignments'), $container, 'tasks', $task_id);
        my $request = HTTP::Request->new(GET => $uri);
        $request->header('Authorization' => $password);

        my $response = $ua->request($request);

        if ($response->is_success) {
            my $json = decode_json($response->content);
            my $type = $self->_get_type($json);
            $json->{type} = $type;
            push @$result, $json;
        }
        else {
            my $code = $response->code;
            $self->logger->info("Response code: $code");
            my $error = $response->content ? $response->content : $response->status_line;
            $self->_save_error($error, $parameters->{resultPropertySheet});
            $self->bail_out($error);
        }
    }

    $self->_generate_report($result, $parameters->{containerType});
    $self->_save_result($parameters->{resultPropertySheet}, $parameters->{resultFormat}, {tasks => $result});
}   

sub _save_error {
    my ($self, $message, $property_name) = @_;
    $self->ec->setProperty( $property_name, $message );
}

sub _save_result {
    my ($self, $property_name, $selected_format, $parsed_data) = @_;

    $self->logger->info("Got data", JSON->new->pretty->encode($parsed_data));

    if ($selected_format eq 'propertySheet') {

        my $flat_map = $self->_self_flatten_map($parsed_data, $property_name, 'check_errors!');

        for my $key (sort keys %$flat_map) {
            $self->ec->setProperty($key, $flat_map->{$key});
            $self->logger->info("Saved $key -> $flat_map->{$key}");
        }
    }
    elsif ($selected_format eq 'json') {
        my $json = encode_json($parsed_data);
        $self->ec->setProperty($property_name, $json);
        $self->logger->info("Saved answer under $property_name");
    }
    else {
        my $error = "Cannot process format $selected_format: not implemented";
        $self->_save_error($error, $property_name);
        $self->bail_out($error);
    }
}


sub _generate_report {
    my ($self, $result, $containerType) = @_;

    my $template_property = '/plugins/@PLUGIN_KEY@/project/resources/' . $containerType . 'TaskListReport';
    my $params = {tasks => $result};
    $self->logger->trace($params);
    my $report = $self->render_template_from_property($template_property, $params, mt => 1);

    mkdir 'artifacts' or die "Cannot create directory: $!";
    my $random_postfix = $self->gen_random_numbers();
    my $report_filename = "taskListReport_$random_postfix.html";
    open my $fh, ">artifacts/$report_filename" or die "Cannot open $report_filename: $!";
    print $fh $report;
    close $fh;

    my $job_step_id = $ENV{COMMANDER_JOBSTEPID};
    my $link = "/commander/jobSteps/$job_step_id/$report_filename";
    my $name = "Tasks Information";
    $self->ec->setProperty("/myJob/report-urls/$name", $link);
    eval {
        $self->ec->setProperty("/myPipelineStageRuntime/ec_summary/Evidence from ISPW",
        qq{<html><a href="$link" target="_blank">$name</a></html>}
        );
    };



}

sub _get_type {
    my ($self, $json) = @_;

    my $type = '';
    for (qw/Program SQL IMS CICS/) {
        if ($json->{lc $_}) {
            $type = $_;
        }
    }
    return $type;
}


1;
