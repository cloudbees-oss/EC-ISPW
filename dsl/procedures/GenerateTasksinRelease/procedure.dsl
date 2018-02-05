procedure 'Generate Tasks in Release', description: 'Runs generate for the Tasks in Release', { // [PROCEDURE]
    // [REST Plugin Wizard step]

    step 'Generate tasks in release',
        command: """
\$[/myProject/scripts/preamble]
use EC::RESTPlugin;
EC::RESTPlugin->new->run_step('Generate tasks in release');
""",
        errorHandling: 'failProcedure',
        exclusiveMode: 'none',
        releaseMode: 'none',
        shell: 'ec-perl',
        timeLimitUnits: 'minutes'
    
    // [REST Plugin Wizard step ends]
    
    
    

}
