package dsl.GetReleaseTaskList

def projName = args.projName

def params = [
        config     : 'specConfig',
        releaseId  : 'SPECTEST'
]

project projName, {
    procedure 'Get Release Task List', {
        projectName = projName

        step 'Get Release Information', {
            subprocedure = 'Get Release Task List'
            subproject = '/plugins/EC-ISPW/project'
            actualParameter 'level', ''
            actualParameter 'resultFormat', 'json'
            actualParameter 'resultPropertySheet', '/myJob/releaseTask'
           
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

