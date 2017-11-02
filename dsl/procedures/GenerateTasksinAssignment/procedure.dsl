procedure 'Generate Tasks in Assignment', description: 'Runs generate for the Tasks in Assignment', { // [PROCEDURE]

    step "Set up for callback",
        command: """
use strict;
use ElectricCommander;
use Data::Dumper;
use SOAP::Lite;
use XML::XPath;
\$| = 1;

# Create a single instance of the Perl access to ElectricCommander
my \$ec = new ElectricCommander();

\$ec->setProperty("\$[/javascript ( (myParent.callbackType == 'job') ? '/myJob/ec_callback_response' : ( (myParent.callbackType == 'flow') ? '/myFlowRuntime/ec_callback_response' : '/myPipelineRuntime/ec_callback_response' ) ) ]", {value => ""});
\$ec->setProperty("/myParent/events", {value => <<_EOM_
\$[/plugins/EC-ISPW/project/ec_callback_\$[callbackType]]
_EOM_
});
""",
        errorHandling: 'abortProcedure',
        exclusiveMode: 'none',
        condition: "\$[/javascript myParent.callbackType != 'custom']",
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
    
    step "Wait for callback, then error on failure", description: "This step will be skipped immediately if the Callback Type is set to 'custom' (in which case it is the caller's responsibility to make the operation wait for the callback the call to be synchronous); otherwise, it will wait for the callback, and then be skipped if the operation was successful, or will report the error if it was not successful.",
        command: """
echo "Callback result was '\$[/javascript ( (myParent.callbackType != 'custom') && ( (myParent.callbackType == 'job') ? myJob.ec_callback_response : ( (myParent.callbackType == 'flow') ? myFlowRuntime.ec_callback_response : myPipelineRuntime.ec_callback_response ) ) ) ]'!"
exit -1
""",
        condition: "\$[/javascript ( (myParent.callbackType != 'custom') && ( ( (myParent.callbackType == 'job') ? myJob.ec_callback_response : ( (myParent.callbackType == 'flow') ? myFlowRuntime.ec_callback_response : myPipelineRuntime.ec_callback_response ) )  != 'completed' ) ) ]",
        errorHandling: 'abortProcedure',
        exclusiveMode: 'none',
        precondition: "\$[/javascript ( (myParent.callbackType == 'custom') || ( (myParent.callbackType == 'job') ? myJob.ec_callback_response : ( (myParent.callbackType == 'flow') ? myFlowRuntime.ec_callback_response : myPipelineRuntime.ec_callback_response ) ) ) ]",
        releaseMode: 'none',
        timeLimitUnits: 'minutes'

}
