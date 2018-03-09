def projName = args.projName

def params = [
        setTasksJson:'',
        containerType:'assignment'
]

project projName, {
    
    procedure 'Display Task Information', {
        projectName = projName

        step 'Display Task Information', {
            subprocedure = 'Display Task Information'
            subproject = '/plugins/EC-ISPW/project'
            actualParameter 'resultFormat', 'json'
            actualParameter 'config', "$args.config"
            actualParameter 'resultPropertySheet', '/myJob/tasksInformation'
            actualParameter 'setTasksJson', ''
            
            params.each { key, value ->
                actualParameter key, '$[' + key + ']'
            }

            params.each { key, value ->
                formalParameter key, defaultValue: value, {
                    type = 'textarea'
                }
            }
        }
    }
}
