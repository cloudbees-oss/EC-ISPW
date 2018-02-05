procedure 'Get Release Task Generate Listing', description: 'Retrieves a generate listing for a Task in a Release', { // [PROCEDURE]
    // [REST Plugin Wizard step]

    step 'Get release task generate listing',
        command: """
\$[/myProject/scripts/preamble]
use EC::RESTPlugin;
EC::RESTPlugin->new->run_step('Get release task generate listing');
""",
        errorHandling: 'failProcedure',
        exclusiveMode: 'none',
        releaseMode: 'none',
        shell: 'ec-perl',
        timeLimitUnits: 'minutes'
    
    // [REST Plugin Wizard step ends]
    
    
    

}
