def projName = args.projName
def params = [
        config: 'specConfig'
]
project projName, {
    procedure 'Get Set Information', {
        projectName = projName

        step 'Trigger Set Creation', {
            subprocedure = 'Generate Tasks in Assignment'
            subproject = '/plugins/EC-ISPW/project'
            actualParameter 'assignmentId', 'DEMO000042'
            actualParameter 'callbackCredential', ''
            actualParameter 'events', '{}'
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
        
        step 'Get Set Information', {
           subprocedure = 'Get Set Information'
           subproject = '/plugins/EC-ISPW/project'
            actualParameter 'config', "$args.config"
           actualParameter 'resultFormat', 'json'
           actualParameter 'resultPropertySheet', '/myJob/set'
           actualParameter 'setId', '\$[/javascript JSON.parse(\'\$[/myJob/generateResult]\').setId]'
         }
    }
}

