def projName = args.projName

def params = [
        config            : 'specConfig',
        releaseId         : 'QATEST',
        level             : 'DEV1',
        callbackCredential: "ispw2"
]

project projName, {

    credential 'ispw2', {
        userName = 'admin'
        password = 'changeme'
    }

    procedure 'Deploy Release', {
        projectName = projName

        step 'Deploy Release', {
            subprocedure = 'Deploy Release'
            subproject = '/plugins/EC-ISPW/project'
            actualParameter 'changeType', 'S'
            actualParameter 'config', 'config'
            actualParameter 'events', ''
            actualParameter 'executionStatus', ''
            actualParameter 'httpHeaders', ''
            actualParameter 'releaseId', 'QATEST'
            actualParameter 'resultFormat', 'json'
            actualParameter 'resultPropertySheet', '/myJob/deployResult'
            actualParameter 'runtimeConfiguration', 'ISPW'

            params.each { key, value ->
                actualParameter key, '$[' + key + ']'
            }

            params.each { key, value ->
                formalParameter key, defaultValue: value, {
                    type = 'textarea'
                }
            }
            attachCredential(credentialName: "/projects/$projName/credentials/ispw2")

        }
    }
}
