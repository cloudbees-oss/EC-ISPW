def projName = args.projName

def params = [
        config            : 'specConfig',
        assignmentId      : 'DEMO000042',
        level             : 'DEV1',
        callbackCredential: "ispw2"
]

project projName, {

    credential 'ispw2', {
        userName = 'admin'
        password = 'changeme'
    }

    procedure 'Deploy Assignment', {
        projectName = projName
        resourceName = 'ISPW Agent'
        step 'Deploy Assignment', {
            errorHandling = 'failProcedure'
            subprocedure = 'Deploy Assignment'
            subproject = '/plugins/EC-ISPW/project'
            actualParameter 'changeType', 'S'
            actualParameter 'events', '[{"name":"completed","method":"PUT","url":"http://localhost"},{"name":"failed","method":"PUT","url":"http://localhost"},{"name":"terminated","method":"PUT","url":"http://localhost"}]'
            actualParameter 'executionStatus', ''
            actualParameter 'httpHeaders', ''
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
