
// Was generated by REST Plugin Wizard
def procName = 'Promote Assignment'
def stepName = 'promote assignment'
procedure procName, description: 'Promotes assignment', {

    step stepName,
        command: """
\$[/myProject/scripts/preamble]
use EC::RESTPlugin;
EC::RESTPlugin->new->run_step('promote assignment');
""",
        errorHandling: 'failProcedure',
        exclusiveMode: 'none',
        releaseMode: 'none',
        shell: 'ec-perl',
        timeLimitUnits: 'minutes'

}
