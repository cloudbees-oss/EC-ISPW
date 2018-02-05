procedure 'Get Release Task List', description: 'Retrieves the Task list for a Release', { // [PROCEDURE]
    // [REST Plugin Wizard step]

    step 'Get release task list',
        command: """
\$[/myProject/scripts/preamble]
use EC::RESTPlugin;
EC::RESTPlugin->new->run_step('Get release task list');
""",
        errorHandling: 'failProcedure',
        exclusiveMode: 'none',
        releaseMode: 'none',
        shell: 'ec-perl',
        timeLimitUnits: 'minutes'
    
    // [REST Plugin Wizard step ends]
    
    
    

}
