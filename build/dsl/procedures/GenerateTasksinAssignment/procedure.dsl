procedure 'Generate Tasks in Assignment', description: 'Runs generate for the Tasks in Assignment', { // [PROCEDURE]

    step "Set up for callback",description: "If the events parameter is not empty, set callbackType to 'events'; otherwise figure out what context we have been called in and set callbackType appropriately, and set events to an appropriate default value.",
        command: """
use strict;
use ElectricCommander;
use Data::Dumper;
use SOAP::Lite;
use XML::XPath;
\$| = 1;

# Create a single instance of the Perl access to ElectricCommander
my \$ec = new ElectricCommander();

# If events is non-empty, set callbackType to 'events' and quit
if (\$[/javascript ((typeof(myParent.events) != null) && (myParent.events.toString().length > 0)) ? 1 : 0]) {
    \$ec->setProperty("/myParent/callbackType", {value => 'events'});
    exit 0;
}
    
# Figure out the appropriate callback type
# print '!(typeof(myWorkflow.workflowId) === \'undefined\') = \$[/javascript !(typeof(myWorkflow.workflowId) === 'undefined')]' . "\\n";
# print '!(typeof(myPipelineRuntime.id) === \'undefined\') = \$[/javascript !(typeof(myPipelineRuntime.id) === 'undefined')]' . "\\n";
# print '!(typeof(myFlowRuntime.id) === \'undefined\') = \$[/javascript !(typeof(myFlowRuntime.id) === 'undefined')]' . "\\n";
# print '!(typeof(myParent.jobStepId) === \'undefined\') = \$[/javascript !(typeof(myParent.jobStepId) === 'undefined')]' . "\\n";
# print '!(typeof(myJob.jobId) === \'undefined\') = \$[/javascript !(typeof(myJob.jobId) === 'undefined')]' . "\\n";

my \$callbackType = '\$[/javascript (!(typeof(myWorkflow.workflowId) === 'undefined') ? 'workflow' : (!(typeof(myPipelineRuntime.id) === 'undefined') ? 'pipeline' : (!(typeof(myFlowRuntime.id) === 'undefined') ? 'flow' : (!(typeof(myParent.jobStepId) === 'undefined') ? 'jobStep' :(!(typeof(myJob.jobId) === 'undefined') ? 'job' : 'unknown')))))]';

if (\$callbackType eq 'unknown') {
    print "ERROR: Unable to determine an appropriate callback type to use!\\n";
    exit (-1);
}
print "Will handle callback using /plugins/EC-ISPW/project/ec_callback_\$callbackType\\n";

\$ec->setProperty("/myParent/callbackType", {value => \$callbackType});

if (\$callbackType eq 'workflow' ) {
    \$ec->setProperty('/myWorkflow/ec_callback_response', {value => ''});
    \$ec->setProperty("/myParent/events", {value => <<_EOM_
\$[/javascript ((!(typeof(myWorkflow.workflowId) === 'undefined')) ? plugins['EC-ISPW'].project.ec_callback_workflow : '')]
_EOM_
    });
} elsif (\$callbackType eq 'pipeline' ) {
    \$ec->setProperty('/myPipelineRuntime/ec_callback_response', {value => ""});
    \$ec->setProperty("/myParent/events", {value => <<_EOM_
\$[/javascript ((!(typeof(myPipelineRuntime.id) === 'undefined')) ? plugins['EC-ISPW'].project.ec_callback_pipeline : '')]
_EOM_
    });
} elsif (\$callbackType eq 'flow' ) {
    \$ec->setProperty('/myFlowRuntime/ec_callback_response', {value => ""});
    \$ec->setProperty("/myParent/events", {value => <<_EOM_
\$[/javascript ((!(typeof(myFlowRuntime.id) === 'undefined')) ? plugins['EC-ISPW'].project.ec_callback_flow : '')]
_EOM_
    });
} elsif (\$callbackType eq 'jobStep' ) {
    \$ec->setProperty('/myJobStep/ec_callback_response', {value => ''});
    \$ec->setProperty("/myParent/events", {value => <<_EOM_
\$[/javascript ((!(typeof(myParent.jobStepId) === 'undefined')) ? plugins['EC-ISPW'].project.ec_callback_jobStep : '')]
_EOM_
    });
} elsif (\$callbackType eq 'job' ) {
    \$ec->setProperty('/myJob/ec_callback_response', {value => ''});
    \$ec->setProperty("/myParent/events", {value => <<_EOM_
\$[/javascript ((!(typeof(myJob.jobId) === 'undefined')) ? plugins['EC-ISPW'].project.ec_callback_job : '')]
_EOM_
    });
}
""",
        errorHandling: 'abortProcedure',
        exclusiveMode: 'none',
        releaseMode: 'none',
        shell: 'ec-perl'
    // [REST Plugin Wizard step]

    step 'Generate tasks in assignment',
        command: """
\$[/myProject/scripts/preamble]
use EC::RESTPlugin;
EC::RESTPlugin->new->run_step('Generate tasks in assignment');
""",
        errorHandling: 'failProcedure',
        exclusiveMode: 'none',
        releaseMode: 'none',
        shell: 'ec-perl',
        timeLimitUnits: 'minutes'
    
    // [REST Plugin Wizard step ends]
    
    
    step "Wait for callback, then error on failure", description: "This step will be skipped immediately if the events parameter was set (in which case it is the caller's responsibility to wait for and handle the callback); otherwise, it will wait for the callback, and then be skipped if the operation was successful, or will report the error if it was not successful.",    
        command: """
echo "Callback result was '\$[/javascript ( (myParent.callbackType == 'jobStep') ? myParent.ec_callback_response : ( (myParent.callbackType == 'job') ? myJob.ec_callback_response : ( (myParent.callbackType == 'flow') ? myFlowRuntime.ec_callback_response : ( (myParent.callbackType == 'pipeline') ? myPipelineRuntime.ec_callback_response : myWorkflow.ec_callback_response ) ) ) ) ]'!"
exit -1
""",
        condition: "\$[/javascript ( (myParent.callbackType != 'events') && ( ( (myParent.callbackType == 'jobStep') ? myParent.ec_callback_response : ( (myParent.callbackType == 'job') ? myJob.ec_callback_response : ( (myParent.callbackType == 'flow') ? myFlowRuntime.ec_callback_response : ( (myParent.callbackType == 'pipeline') ? myPipelineRuntime.ec_callback_response : myWorkflow.ec_callback_response ) ) ) ) != 'completed' ) ) ]",
        errorHandling: 'abortProcedure',
        exclusiveMode: 'none',
        precondition: "\$[/javascript ( (myParent.callbackType == 'events') || ( (myParent.callbackType == 'jobStep') ? myParent.ec_callback_response : ( (myParent.callbackType == 'job') ? myJob.ec_callback_response : ( (myParent.callbackType == 'flow') ? myFlowRuntime.ec_callback_response : ( (myParent.callbackType == 'pipeline') ? myPipelineRuntime.ec_callback_response : myWorkflow.ec_callback_response ) ) ) ) ) ]",
        releaseMode: 'none',
        timeLimitUnits: 'minutes'

}
