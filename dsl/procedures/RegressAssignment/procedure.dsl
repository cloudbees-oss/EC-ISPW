procedure 'Regress Assignment', description: 'Regresses an Assignment', { // [PROCEDURE]
    // [REST Plugin Wizard step]

    step 'Regress assignment',
        command: """
\$[/myProject/scripts/preamble]
use EC::RESTPlugin;
EC::RESTPlugin->new->run_step('Regress assignment');
""",
        errorHandling: 'failProcedure',
        exclusiveMode: 'none',
        releaseMode: 'none',
        shell: 'ec-perl',
        timeLimitUnits: 'minutes'
    
    // [REST Plugin Wizard step ends]
    
    
    

}
