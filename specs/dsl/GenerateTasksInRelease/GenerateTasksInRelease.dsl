def projName = args.projName

def params = [
        config            : 'specConfig',
        level             : 'DEV1',
        callbackCredential: "ispw2",
        releaseId      : "SPECTEST"
]

project projName, {

    credential 'ispw2', {
        userName = 'admin'
        password = 'changeme'
    }

    procedure 'Generate Tasks In Release', {
        projectName = projName
        resourceName = 'ISPW Agent'

        step 'Generate Tasks In Release', {
            subprocedure = 'Generate Tasks in Release'
            subproject = '/plugins/EC-ISPW/project'
            actualParameter 'httpHeaders', ''
            actualParameter 'resultFormat', 'json'
            actualParameter 'resultPropertySheet', '/myJob/generateReleaseTasks'
            actualParameter 'runtimeConfiguration', 'ISPW'
            actualParameter 'events', '$[eventz]'
            formalParameter 'eventz', defaultValue: null, {type = 'textarea'}

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
