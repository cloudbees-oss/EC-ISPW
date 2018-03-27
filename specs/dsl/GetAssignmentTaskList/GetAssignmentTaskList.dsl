def projName = args.projName

def params = [
        config     : 'specConfig',
        assignmentId  : 'DEMO000040'
]

project projName, {
    procedure 'Get Assignment Task List', {
        projectName = projName
        resourceName = 'ISPW Agent'

        step 'Get Assignment Information', {
            subprocedure = 'Get Assignment Task List'
            subproject = '/plugins/EC-ISPW/project'
            actualParameter 'level', ''
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

