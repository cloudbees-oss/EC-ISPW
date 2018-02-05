procedure 'Get Set Deployment Information', description: 'Retrieves deployment information about a Set', { // [PROCEDURE]
    // [REST Plugin Wizard step]

    step 'Get set deployment information',
        command: """
\$[/myProject/scripts/preamble]
use EC::RESTPlugin;
EC::RESTPlugin->new->run_step('Get set deployment information');
""",
        errorHandling: 'failProcedure',
        exclusiveMode: 'none',
        releaseMode: 'none',
        shell: 'ec-perl',
        timeLimitUnits: 'minutes'
    
    // [REST Plugin Wizard step ends]
    
    

}
