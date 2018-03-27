package dsl.PromoteRelease

def projName = args.projName

def params = [
        config              : 'specConfig',
        releaseId           : 'SPECTEST',
        callbackCredential  : "ispw2",
        runtimeConfiguration: "ISPW"
]

project projName, {

    credential 'ispw2', {
        userName = 'admin'
        password = 'changeme'
    }

    procedure 'Promote Release With Checks', {
        projectName = projName
        resourceName = 'ISPW Agent'

        step 'Get Release Info', {
            subprocedure = 'Get Release Task List'
            subproject = '/plugins/EC-ISPW/project'
            actualParameter 'resultFormat', 'json'
            actualParameter 'resultPropertySheet', '/myJob/releaseInfo'
            actualParameter 'config', params.config
            actualParameter 'releaseId', params.releaseId
        }

        step 'Regress Release', {
            condition = '$[/javascript JSON.parse(\'$[/myJob/releaseInfo]\').tasks.length>0 && JSON.parse(\'$[/myJob/releaseInfo]\').tasks[0].level==\'STG1\' ]'
            errorHandling = 'failProcedure'
            subprocedure = 'Regress Release'
            subproject = '/plugins/EC-ISPW/project'
            actualParameter 'changeType', 'S'
            actualParameter 'events', '[{"name":"completed","method":"PUT","url":"http://localhost"},{"name":"failed","method":"PUT","url":"http://localhost"},{"name":"terminated","method":"PUT","url":"http://localhost"}]'
            actualParameter 'executionStatus', ''
            actualParameter 'httpHeaders', ''
            actualParameter 'resultFormat', 'json'
            actualParameter 'resultPropertySheet', '/myJob/regressReleaseResult'
            actualParameter 'level', 'STG1'

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

        step 'Generate Tasks In Assignment', {
            condition = '$[/javascript JSON.parse(\'$[/myJob/releaseInfo]\').tasks.length>0 && JSON.parse(\'$[/myJob/releaseInfo]\').tasks[0].level==\'STG1\' ]'
            subprocedure = 'Generate Tasks in Assignment'
            subproject = '/plugins/EC-ISPW/project'
            actualParameter 'httpHeaders', ''
            actualParameter 'resultFormat', 'json'
            actualParameter 'resultPropertySheet', '/myJob/generateAssignmentTasks'
            actualParameter 'assignmentId', 'DEMO000042'
            actualParameter 'config', params.config
            actualParameter 'callbackCredential', params.callbackCredential
            actualParameter 'runtimeConfiguration', params.runtimeConfiguration
            actualParameter 'level', 'DEV1'

            actualParameter 'events', '[{"name":"completed","method":"PUT","url":"http://localhost"},{"name":"failed","method":"PUT","url":"http://localhost"},{"name":"terminated","method":"PUT","url":"http://localhost"}]'

            attachCredential(credentialName: "/projects/$projName/credentials/ispw2")
        }

        step 'Promote Release', {
            errorHandling = 'failProcedure'
            subprocedure = 'Promote Release'
            subproject = '/plugins/EC-ISPW/project'
            actualParameter 'changeType', 'S'
            actualParameter 'events', '[{"name":"completed","method":"PUT","url":"http://localhost"},{"name":"failed","method":"PUT","url":"http://localhost"},{"name":"terminated","method":"PUT","url":"http://localhost"}]'
            actualParameter 'executionStatus', ''
            actualParameter 'httpHeaders', ''
            actualParameter 'resultFormat', 'json'
            actualParameter 'resultPropertySheet', '/myJob/promoteReleaseResult'
            actualParameter 'level', 'DEV1'

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
