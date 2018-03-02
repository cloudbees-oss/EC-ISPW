procedure 'Get Set Information', description: 'Retrieves information about a Set', { // [PROCEDURE]
    // [REST Plugin Wizard step]

    step 'Get set information',
        command: """
\$[/myProject/scripts/preamble]
use EC::RESTPlugin;
EC::RESTPlugin->new->run_step('Get set information');
""",
        errorHandling: 'failProcedure',
        exclusiveMode: 'none',
        releaseMode: 'none',
        shell: 'ec-perl',
        timeLimitUnits: 'minutes'
    
    // [REST Plugin Wizard step ends]
    
    
    
    
    

}
