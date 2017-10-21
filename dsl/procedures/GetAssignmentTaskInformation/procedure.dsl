procedure 'Get Assignment Task Information', description: 'Retrieves information about a Task in an Assignment', { // [PROCEDURE]
    // [REST Plugin Wizard step]

    step 'Get assignment task information',
        command: """
\$[/myProject/scripts/preamble]
use EC::RESTPlugin;
EC::RESTPlugin->new->run_step('Get assignment task information');
""",
        errorHandling: 'failProcedure',
        exclusiveMode: 'none',
        releaseMode: 'none',
        shell: 'ec-perl',
        timeLimitUnits: 'minutes'
    
    // [REST Plugin Wizard step ends]
    
    
    
    
    

}
