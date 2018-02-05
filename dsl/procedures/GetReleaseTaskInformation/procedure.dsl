procedure 'Get Release Task Information', description: 'Retrieves information about a Task in a Release', { // [PROCEDURE]
    // [REST Plugin Wizard step]

    step 'Get release task information',
        command: """
\$[/myProject/scripts/preamble]
use EC::RESTPlugin;
EC::RESTPlugin->new->run_step('Get release task information');
""",
        errorHandling: 'failProcedure',
        exclusiveMode: 'none',
        releaseMode: 'none',
        shell: 'ec-perl',
        timeLimitUnits: 'minutes'
    
    // [REST Plugin Wizard step ends]
    
    
    

}
