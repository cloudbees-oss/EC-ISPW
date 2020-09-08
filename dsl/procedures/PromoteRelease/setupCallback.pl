use strict;
use ElectricCommander;
use Data::Dumper;
use SOAP::Lite;
use XML::XPath;
$| = 1;

# Create a single instance of the Perl access to ElectricCommander
my $ec = new ElectricCommander();

my $hasEvents = eval { $ec->getPropertyValue('/myParent/events') };

# my $nonEmpty = '$[/javascript ((typeof(myParent.events) != null) && (myParent.events.toString().length > 0)) ? 1 : 0]' + 0;

# If events is non-empty, set callbackType to 'events' and quit
if ($hasEvents) {
    $ec->setProperty("/myParent/callbackType", {value => 'events'});
    exit 0;
}

# Figure out the appropriate callback type
# print '!(typeof(myWorkflow.workflowId) === \'undefined\') = \$[/javascript !(typeof(myWorkflow.workflowId) === 'undefined')]' . "\\n";
# print '!(typeof(myPipelineRuntime.id) === \'undefined\') = \$[/javascript !(typeof(myPipelineRuntime.id) === 'undefined')]' . "\\n";
# print '!(typeof(myFlowRuntime.id) === \'undefined\') = \$[/javascript !(typeof(myFlowRuntime.id) === 'undefined')]' . "\\n";
# print '!(typeof(myParent.jobStepId) === \'undefined\') = \$[/javascript !(typeof(myParent.jobStepId) === 'undefined')]' . "\\n";
# print '!(typeof(myJob.jobId) === \'undefined\') = \$[/javascript !(typeof(myJob.jobId) === 'undefined')]' . "\\n";

my $callbackType = q{$[/javascript (!(typeof(myWorkflow.workflowId) === 'undefined') ? 'workflow' : (!(typeof(myPipelineRuntime.id) === 'undefined') ? 'pipeline' : (!(typeof(myFlowRuntime.id) === 'undefined') ? 'flow' : (!(typeof(myParent.jobStepId) === 'undefined') ? 'jobStep' :(!(typeof(myJob.jobId) === 'undefined') ? 'job' : 'unknown')))))]};

if ($callbackType eq 'unknown') {
    print "ERROR: Unable to determine an appropriate callback type to use!\n";
    exit (-1);
}
print "Will handle callback using /plugins/EC-ISPW/project/ec_callback_$callbackType\n";

$ec->setProperty("/myParent/callbackType", {value => $callbackType});

if ($callbackType eq 'workflow' ) {
    $ec->setProperty('/myWorkflow/ec_callback_response', {value => ''});
    $ec->setProperty("/myParent/events", {value => <<_EOM_
\$[/javascript ((!(typeof(myWorkflow.workflowId) === 'undefined')) ? plugins['EC-ISPW'].project.ec_callback_workflow : '')]
_EOM_
    });
} elsif ($callbackType eq 'pipeline' ) {
    $ec->setProperty('/myPipelineRuntime/ec_callback_response', {value => ""});
    $ec->setProperty("/myParent/events", {value => <<_EOM_
\$[/javascript ((!(typeof(myPipelineRuntime.id) === 'undefined')) ? plugins['EC-ISPW'].project.ec_callback_pipeline : '')]
_EOM_
    });
} elsif ($callbackType eq 'flow' ) {
    $ec->setProperty('/myFlowRuntime/ec_callback_response', {value => ""});
    $ec->setProperty("/myParent/events", {value => <<_EOM_
\$[/javascript ((!(typeof(myFlowRuntime.id) === 'undefined')) ? plugins['EC-ISPW'].project.ec_callback_flow : '')]
_EOM_
    });
} elsif ($callbackType eq 'jobStep' ) {
    $ec->setProperty('/myJobStep/ec_callback_response', {value => ''});
    $ec->setProperty("/myParent/events", {value => <<_EOM_
\$[/javascript ((!(typeof(myParent.jobStepId) === 'undefined')) ? plugins['EC-ISPW'].project.ec_callback_jobStep : '')]
_EOM_
    });
} elsif ($callbackType eq 'job' ) {
    $ec->setProperty('/myJob/ec_callback_response', {value => ''});
    $ec->setProperty("/myParent/events", {value => <<_EOM_
\$[/javascript ((!(typeof(myJob.jobId) === 'undefined')) ? plugins['EC-ISPW'].project.ec_callback_job : '')]
_EOM_
    });
}
