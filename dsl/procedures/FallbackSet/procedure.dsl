procedure 'Fallback Set', description: 'Fallbacks a Set from a previous deployment', { // [PROCEDURE]
    // [REST Plugin Wizard step]

    step 'Fallback set',
        command: """
\$[/myProject/scripts/preamble]
use EC::RESTPlugin;
EC::RESTPlugin->new->run_step('Fallback set');
""",
        errorHandling: 'failProcedure',
        exclusiveMode: 'none',
        releaseMode: 'none',
        shell: 'ec-perl',
        timeLimitUnits: 'minutes'
    
    // [REST Plugin Wizard step ends]
    
    

}
