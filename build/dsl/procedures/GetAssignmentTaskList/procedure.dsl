procedure 'Get Assignment Task List', description: 'Retrieves the Task list for an Assignment', { // [PROCEDURE]
    // [REST Plugin Wizard step]

    step 'Get assignment task list',
        command: """
\$[/myProject/scripts/preamble]
use EC::RESTPlugin;
EC::RESTPlugin->new->run_step('Get assignment task list');
""",
        errorHandling: 'failProcedure',
        exclusiveMode: 'none',
        releaseMode: 'none',
        shell: 'ec-perl',
        timeLimitUnits: 'minutes'
    
    // [REST Plugin Wizard step ends]
    
    
    
    
    

}
