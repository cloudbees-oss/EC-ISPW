def projName = args.projName

def params = [
//    config: 'specConfig'
]

project projName, {
    procedure 'CreateAssignment', {
        description = ''
        jobNameTemplate = ''
        projectName = projName
        resourceName = ''
        timeLimit = ''
        timeLimitUnits = 'minutes'
        workspaceName = ''

        step 'Create Assignment', {
            projectName = projName
            subprocedure = 'Create Assignment'
            subproject = '/plugins/EC-ISPW/project'
            actualParameter '_owner', 'ELCCLD1'
            actualParameter 'application', 'DEMO'
            actualParameter 'assignmentPrefix', 'DEMO'
            actualParameter 'defaultPath', 'DEV1'
            actualParameter 'description', 'DEMO Assignment'
            actualParameter 'refNumber', ''
            actualParameter 'release', 'QATEST'
            actualParameter 'resultFormat', 'json'
            actualParameter 'resultPropertySheet', '/myJob/createAssignment'
            actualParameter 'stream', 'DEMO'
            actualParameter 'userTag', ''
            actualParameter 'config', 'specConfig'
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
