import spock.lang.*

class DisplayTaskInformation extends ECISPWPluginHelper {

    static def projectName = 'EC-ISPW Specs Display Task Information'

    def doSetupSpec() {
        createConfiguration('specConfig')
        dslFile 'dsl/DisplayTaskInformation/DisplayTaskInformation.dsl', [projName: projectName, config: 'specConfig']
//        dslFile 'dsl/DisplayTaskInformation/DisplayTaskInformationSingleStep.dsl', [projName: projectName, config:'specConfig']
    }

    def doCleanupSpec() {
        dsl "deleteProject '$projectName'"
    }

    //TODO: 1. Test against assignment
    // 2. Release
    // 3. Set (kinda complicated stuff, since we have to get a Set created first
    // 3-4-5 Asn - Release - Set agains empty setTasksJson - for now there is an issue with failing Perl. - Talk to Roger

    @Unroll
    def "Display Task Information for Assignment"() {
        when: 'a procedure runs'

        def result = dsl """
                runProcedure(
                    projectName: '$projectName',
                    procedureName: 'Display Task Information',
                    actualParameter: [containerType:'assignment', setTasksJson:'{"tasks":[{"taskId":"7E22DBD4287E", "assignment":"DEMO000042"}]}']
                )
            """
        then: 'the procedure finishes successfully'
        assert result?.jobId
        waitUntil {
            jobCompleted result.jobId
        }
        assert jobStatus(result.jobId).outcome == 'success'
    }

    def "Display Task Information for Release"() {
        when: 'a procedure runs'

        def result = dsl """
                  runProcedure(
                      projectName: '$projectName',
                      procedureName: 'Display Task Information',
                      actualParameter: [containerType:'release', setTasksJson:'{"tasks":[{"taskId":"7E22DBD4287E","release":"SPECTEST"}]}']
                  )
              """
        then: 'the procedure finishes successfully'
        assert result?.jobId
        waitUntil {
            jobCompleted result.jobId
        }
        assert jobStatus(result.jobId).outcome == 'success'
    }

    @Unroll
    def "Display Task Information with Emtpy Set Tasks"() {
        when: 'a procedure runs'

        def result = dsl """
                runProcedure(
                    projectName: '$projectName',
                    procedureName: 'Display Task Information',
                    actualParameter: [containerType:'assignment', setTasksJson:'{}']
                )
            """
        then: 'the procedure fails'
        assert result?.jobId
        waitUntil {
            jobCompleted result.jobId
        }
        assert jobStatus(result.jobId).outcome == 'error'
        //TODO: Workaround 404 response
    }

    @Unroll
    def "Display Task Information with Wrong Task ID"() {
        when: 'a procedure runs'

        def result = dsl """
                runProcedure(
                    projectName: '$projectName',
                    procedureName: 'Display Task Information',
                    actualParameter: [containerType:'release', setTasksJson:'{"tasks":[{"taskId":"1234", "release":"QATEST"}]}']
                )
            """
        then: 'the procedure fails'
        assert result?.jobId
        waitUntil {
            jobCompleted result.jobId
        }
        assert jobStatus(result.jobId).outcome == 'error'
        //TODO: Workaround 404 response
    }

}
