procedure 'Get Release Information', description: 'Retrieves information about a Release', { // [PROCEDURE]
    // [REST Plugin Wizard step]

    step 'Get release information',
        command: """
\$[/myProject/scripts/preamble]
use EC::RESTPlugin;
EC::RESTPlugin->new->run_step('Get release information');
""",
        errorHandling: 'failProcedure',
        exclusiveMode: 'none',
        releaseMode: 'none',
        shell: 'ec-perl',
        timeLimitUnits: 'minutes'
    
    // [REST Plugin Wizard step ends]
    
    
    
    
    

}
