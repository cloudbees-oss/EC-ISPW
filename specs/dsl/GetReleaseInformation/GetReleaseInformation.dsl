def projName = args.projName

def params = [
        config     : 'specConfig',
        releaseId  : 'QATEST'
]

project projName, {
    procedure 'Get Release Information', {
        projectName = projName

        step 'Get Release Information', {
            subprocedure = 'Get Release Information'
            subproject = '/plugins/EC-ISPW/project'
            actualParameter 'resultFormat', 'json'
            actualParameter 'resultPropertySheet', '/myJob/releaseInformation'

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

