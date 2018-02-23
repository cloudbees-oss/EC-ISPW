def projName = args.projName

def params = [
        config     : 'specConfig',
        assignmentId  : 'DEMO000040'
]

project projName, {
    procedure 'Get Assignment Information', {
        projectName = projName

        step 'Get Assignment Information', {
            subprocedure = 'Get Assignment Information'
            subproject = '/plugins/EC-ISPW/project'
            actualParameter 'resultFormat', 'json'
            actualParameter 'resultPropertySheet', '/myJob/assignment'

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

