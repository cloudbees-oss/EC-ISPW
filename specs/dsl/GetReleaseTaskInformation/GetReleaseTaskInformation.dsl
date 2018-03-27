package dsl.GetReleaseTaskInformation

def projName = args.projName

def params = [
        config   : 'specConfig',
        releaseId: 'QATEST',
        taskId   : '7E222BC9CB84'
]

project projName, {
    procedure 'Get Release Task Information', {
        projectName = projName
        resourceName = 'ISPW Agent'

        step 'Get Release Task Information', {
            subprocedure = 'Get Release Task Information'
            subproject = '/plugins/EC-ISPW/project'
            actualParameter 'config', 'config'
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
