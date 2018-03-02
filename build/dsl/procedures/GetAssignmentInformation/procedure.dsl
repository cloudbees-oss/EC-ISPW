procedure 'Get Assignment Information', description: 'Retrieves information about an Assignment', { // [PROCEDURE]
    // [REST Plugin Wizard step]

    step 'Get assignment information',
        command: """
\$[/myProject/scripts/preamble]
use EC::RESTPlugin;
EC::RESTPlugin->new->run_step('Get assignment information');
""",
        errorHandling: 'failProcedure',
        exclusiveMode: 'none',
        releaseMode: 'none',
        shell: 'ec-perl',
        timeLimitUnits: 'minutes'
    
    // [REST Plugin Wizard step ends]
    
    
    
    
    

}
