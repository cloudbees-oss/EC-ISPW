def projName = args.projName

def params = [
        config            : 'specConfig',
        assignmentId      : 'DEMO000041',
        stream            : 'DEMO'
]

project projName, {
    procedure 'Load Task To Assignment', {
        projectName = projName
        resourceName = 'ISPW Agent'

        step 'Load Task To Assignment', {
            subprocedure = 'Load Task'
            subproject = '/plugins/EC-ISPW/project'
            actualParameter 'application', 'DEMO'
            actualParameter 'moduleName', 'DEMOTST1'
            actualParameter 'moduleType', 'COB'
            actualParameter 'resultFormat', 'json'
            actualParameter 'resultPropertySheet', '/myJob/loadTask'
            actualParameter 'currentLevel', 'DEV1'
            actualParameter 'startingLevel', 'DEV1'
            
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
