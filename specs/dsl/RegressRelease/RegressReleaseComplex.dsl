package dsl.RegressRelease

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

    procedure 'Regress Release With Checks', {
        projectName = projName

        step 'Get Release Info', {
            subprocedure = 'Get Release Task List'
            subproject = '/plugins/EC-ISPW/project'
            actualParameter 'config', params.config
            actualParameter 'releaseId', params.releaseId
            actualParameter 'resultFormat', 'json'
            actualParameter 'resultPropertySheet', '/myJob/releaseInfo'
        }

        step 'Promote Release', {
            condition = '$[/javascript JSON.parse(\'$[/myJob/releaseInfo]\').tasks.length>0 && JSON.parse(\'$[/myJob/releaseInfo]\').tasks[0].level==\'DEV1\' ]'
            projectName = 'EC-ISPW Specs RegressRelease'
            subprocedure = 'Promote Release'
            subproject = '/plugins/EC-ISPW/project'
            actualParameter 'autoDeploy', 'false'
            actualParameter 'changeType', 'S'
            actualParameter 'config', params.config
            actualParameter 'events', '[{\"name\":\"completed\",\"method\":\"PUT\",\"url\":\"http://localhost\"},{\"name\":\"failed\",\"method\":\"PUT\",\"url\":\"http://localhost\"},{\"name\":\"terminated\",\"method\":\"PUT\",\"url\":\"http://localhost\"}]'
            actualParameter 'executionStatus', ''
            actualParameter 'httpHeaders', ''
            actualParameter 'resultFormat', 'json'
            actualParameter 'resultPropertySheet', '/myJob/promoteResult'

            attachCredential(credentialName: "/projects/$projName/credentials/ispw2")

            params.each { key, value ->
                actualParameter key, '$[' + key + ']'
            }

            params.each { key, value ->
                formalParameter key, defaultValue: value, {
                    type = 'textarea'
                }
            }

            actualParameter 'level', 'DEV1'
        }

        step 'Regress Release', {
            errorHandling = 'failProcedure'
            subprocedure = 'Regress Release'
            subproject = '/plugins/EC-ISPW/project'
            actualParameter 'changeType', 'S'
            actualParameter 'events', '[{"name":"completed","method":"PUT","url":"http://localhost"},{"name":"failed","method":"PUT","url":"http://localhost"},{"name":"terminated","method":"PUT","url":"http://localhost"}]'
            actualParameter 'executionStatus', ''
            actualParameter 'httpHeaders', ''
            actualParameter 'resultFormat', 'json'
            actualParameter 'resultPropertySheet', '/myJob/regressReleaseResult'

            attachCredential(credentialName: "/projects/$projName/credentials/ispw2")

            params.each { key, value ->
                actualParameter key, '$[' + key + ']'
            }

            params.each { key, value ->
                formalParameter key, defaultValue: value, {
                    type = 'textarea'
                }
            }

            actualParameter 'level', 'STG1'
        }
    }
}
