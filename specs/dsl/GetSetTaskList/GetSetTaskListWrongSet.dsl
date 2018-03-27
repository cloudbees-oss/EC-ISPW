def projName = args.projName
project projName, {
    procedure 'Get Set Task List Wrong Set', {
        projectName = projName
        resourceName = 'ISPW Agent'

        step 'Get Set Task List', {
            subprocedure = 'Get Set Task List'
            subproject = '/plugins/EC-ISPW/project'
            actualParameter 'config', args.config
            actualParameter 'resultFormat', 'json'
            actualParameter 'resultPropertySheet', '/myJob/setTasks'
            actualParameter 'setId', '1234'
        }
    }
}

