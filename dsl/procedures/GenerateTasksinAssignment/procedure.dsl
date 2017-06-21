
// Was generated by REST Plugin Wizard
def procName = 'Generate Tasks in Assignment'
def stepName = 'generate tasks in assignment'
procedure procName, description: 'Generates Tasks in Assignment', {

    step stepName,
        command: """
\$[/myProject/scripts/preamble]
use EC::RESTPlugin;
EC::RESTPlugin->new->run_step('generate tasks in assignment');
""",
        errorHandling: 'failProcedure',
        exclusiveMode: 'none',
        releaseMode: 'none',
        shell: 'ec-perl',
        timeLimitUnits: 'minutes'

}
