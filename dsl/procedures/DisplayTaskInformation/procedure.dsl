
procedure 'Display Task Information', description: 'Queries several tasks and generates a report for them', { // [PROCEDURE]
    // [REST Plugin Wizard step]

    step 'Display task information',
        command: '''
\$[/myProject/scripts/preamble]
use EC::Plugin::ISPW;
EC::Plugin::ISPW->new->step_display_task_information($params{containerType});

''',
        errorHandling: 'failProcedure',
        exclusiveMode: 'none',
        releaseMode: 'none',
        shell: 'ec-perl',
        timeLimitUnits: 'minutes'

    // [REST Plugin Wizard step ends]

}
