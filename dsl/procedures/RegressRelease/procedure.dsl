procedure 'Regress Release', description: 'Regresses a Release', { // [PROCEDURE]

    step "Set up for callback",description: "If the events parameter is not empty, set callbackType to 'events'; otherwise figure out what context we have been called in and set callbackType appropriately, and set events to an appropriate default value.",
        command: '$[/myProject/scripts/setupCallback]',
        errorHandling: 'abortProcedure',
        exclusiveMode: 'none',
        releaseMode: 'none',
        shell: 'ec-perl'
    // [REST Plugin Wizard step]

    step 'Regress release',
        command: """
\$[/myProject/scripts/preamble]
use EC::RESTPlugin;
EC::RESTPlugin->new->run_step('Regress release');
""",
        errorHandling: 'abortProcedure',
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
