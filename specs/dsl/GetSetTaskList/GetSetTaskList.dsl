def projName = args.projName
def params = [
        config: 'specConfig'
]
project projName, {
    procedure 'Get Set Task List', {
        projectName = projName

        step 'Trigger Set Creation', {
            subprocedure = 'Generate Tasks in Assignment'
            subproject = '/plugins/EC-ISPW/project'
            actualParameter 'assignmentId', 'DEMO000042'
            actualParameter 'callbackCredential', ''
            actualParameter 'events', '[{"name":"completed","method":"PUT","url":"http://localhost"},{"name":"failed","method":"PUT","url":"http://localhost"},{"name":"terminated","method":"PUT","url":"http://localhost"}]'
            actualParameter 'httpHeaders', ''
            actualParameter 'level', 'DEV1'
            actualParameter 'resultFormat', 'json'
            actualParameter 'resultPropertySheet', '/myJob/generateResult'
            actualParameter 'runtimeConfiguration', 'ISPW'

            params.each { key, value ->
                actualParameter key, '$[' + key + ']'
            }

            params.each { key, value ->
                formalParameter key, defaultValue: value, {
                    type = 'textarea'
                }
            }
        }

        step 'Get Set Task List', {
            subprocedure = 'Get Set Task List'
            subproject = '/plugins/EC-ISPW/project'
            actualParameter 'config', params.config
            actualParameter 'resultFormat', 'json'
            actualParameter 'resultPropertySheet', '/myJob/setTasks'
            actualParameter 'setId', '\$[/javascript JSON.parse(\'\$[/myJob/generateResult]\').setId]'
        }
    }
}

