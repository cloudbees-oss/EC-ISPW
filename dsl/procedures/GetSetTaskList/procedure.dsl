procedure 'Get Set Task List', description: 'Retrieves the Task list for a Set', { // [PROCEDURE]
    // [REST Plugin Wizard step]

    step 'Get set task list',
        command: """
\$[/myProject/scripts/preamble]
use EC::RESTPlugin;
EC::RESTPlugin->new->run_step('Get set task list');
""",
        errorHandling: 'failProcedure',
        exclusiveMode: 'none',
        releaseMode: 'none',
        shell: 'ec-perl',
        timeLimitUnits: 'minutes'
    
    // [REST Plugin Wizard step ends]
    
    
    
    
    

}
