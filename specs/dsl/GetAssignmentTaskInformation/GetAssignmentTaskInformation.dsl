def projName = args.projName

def params = [
        config      : 'specConfig',
        assignmentId: 'DEMO000040',
        taskId      : '7E222BC9CB84'
]

project projName, {
    procedure 'Get Assignment Task Information', {
        projectName = projName

        step 'Get Assignment Task Information', {
            subprocedure = 'Get Assignment Task Information'
            subproject = '/plugins/EC-ISPW/project'
            actualParameter 'config', 'config'
            actualParameter 'resultFormat', 'json'
            actualParameter 'resultPropertySheet', '/myJob/assignmentTask'

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
