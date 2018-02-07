def projName = args.projName

def params = [
    config: 'specConfig',
    assignee: '',
    jiraProject: '',
    summary: '',
    issueType: 'Task',
    affectsVersion: '',
    labels: '',
    description: '',
    additionalParameters: '',
    createLink: ''
]

project projName, {
    procedure 'Create Assignment', {
      step 'Create Assignment', {
        subprocedure = 'CreateAssignment'
        subproject = '/plugins/EC-ISPW/project'
        timeLimit = ''
        timeLimitUnits = 'minutes'
        workingDirectory = null
        workspaceName = ''

        params.each { key, value ->
            actualParameter key, '$[' + key + ']'
        }
      }

      params.each { key, value ->
        formalParameter key, defaultValue: value, {
            type = 'textarea'
        }
      }
    }
}
