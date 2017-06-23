
// Was generated by REST Plugin Wizard
def procName = 'Regress Release'
def stepName = 'regress release'
procedure procName, description: 'Regresses release', {

    step stepName,
        command: """
\$[/myProject/scripts/preamble]
use EC::RESTPlugin;
EC::RESTPlugin->new->run_step('regress release');
""",
        errorHandling: 'failProcedure',
        exclusiveMode: 'none',
        releaseMode: 'none',
        shell: 'ec-perl',
        timeLimitUnits: 'minutes'

}
