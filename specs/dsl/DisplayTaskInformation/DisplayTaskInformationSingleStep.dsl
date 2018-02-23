def projName = args.projName

project projName, {
    procedure 'Display Task Information With Single Step', {
        projectName = projName

        step 'Display Task Information with Empty Set Tasks', {
            subprocedure = 'Display Task Information'
            subproject = '/plugins/EC-ISPW/project'
            actualParameter 'resultFormat', 'json'
            actualParameter 'resultPropertySheet', '/myJob/tasksInformation'
            actualParameter 'setTasksJson', ''
            actualParameter 'config', "$args.config"
        }
    }
}
