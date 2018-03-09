def projName = args.projName

def params = [
        config   : 'specConfig',
        releaseId: 'QATEST',
        stream: 'DEMO',
        application: 'DEMO',
        defaultPath: 'DEV1'
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
            actualParameter 'assignmentPrefix', 'DEMO'
            actualParameter 'description', 'DEMO Assignment'
            actualParameter 'referenceNumber', ''
            actualParameter 'resultFormat', 'json'
            actualParameter 'resultPropertySheet', '/myJob/createAssignment'
            actualParameter 'userTag', ''
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
