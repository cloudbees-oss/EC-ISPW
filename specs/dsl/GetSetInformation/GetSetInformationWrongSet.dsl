def projName = args.projName
project projName, {
    procedure 'Get Set Information Wrong Set', {
        projectName = projName
        
        step 'Get Set Information', {
           subprocedure = 'Get Set Information'
           subproject = '/plugins/EC-ISPW/project'
            actualParameter 'config', "$args.config"
           actualParameter 'resultFormat', 'json'
           actualParameter 'resultPropertySheet', '/myJob/set'
           actualParameter 'setId', '1234'
         }
    }
}

