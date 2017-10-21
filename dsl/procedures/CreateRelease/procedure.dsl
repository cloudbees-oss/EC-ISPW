procedure 'Create Release', description: 'Creates a Release', { // [PROCEDURE]
    // [REST Plugin Wizard step]

    step 'Create release',
        command: """
\$[/myProject/scripts/preamble]
use EC::RESTPlugin;
EC::RESTPlugin->new->run_step('Create release');
""",
        errorHandling: 'failProcedure',
        exclusiveMode: 'none',
        releaseMode: 'none',
        shell: 'ec-perl',
        timeLimitUnits: 'minutes'
    
    // [REST Plugin Wizard step ends]
    
    
    
    
    

}
