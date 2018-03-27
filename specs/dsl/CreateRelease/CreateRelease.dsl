def projName = args.projName

def params = [
        config       : 'specConfig',
        releaseId    : 'QATEST',
        stream       : 'DEMO',
        application  : 'DEMO',
        releasePrefix: '',
        releaseId: ''
]

project projName, {

    procedure 'CreateRelease', {
        description = 'Creates a Release'
        jobNameTemplate = null
        projectName = projName
        resourceName = 'ISPW Agent'
        timeLimitUnits = null
        workspaceName = null
        step 'Create Release', {
            subprocedure = 'Create Release'
            subproject = '/plugins/EC-ISPW/project'
            actualParameter '_owner', 'ELCCLD1'
            actualParameter 'description', 'Demo'
            actualParameter 'referenceNumber', ''
            actualParameter 'resultFormat', 'json'
            actualParameter 'resultPropertySheet', '/myJob/release'
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
