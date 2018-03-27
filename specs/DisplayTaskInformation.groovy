import org.apache.commons.lang.StringUtils
import spock.lang.*

class DisplayTaskInformation extends ECISPWPluginHelper {

    static def projectName = 'EC-ISPW Specs Display Task Information'

    def doSetupSpec() {
        createConfiguration('specConfig')
        dslFile 'dsl/DisplayTaskInformation/DisplayTaskInformation.dsl', [projName: projectName, config: 'specConfig']
    }

    def doCleanupSpec() {
//        dsl "deleteProject '$projectName'"
    }
    
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
        assert StringUtils.isNotEmpty(getJobProperty("/myJob/tasksInformation", result.jobId).toString())
    }

    def "Display Task Information for Release"() {
        when: 'a procedure runs'

        def result = dsl """
                  runProcedure(
                      projectName: '$projectName',
                      procedureName: 'Display Task Information',
                      actualParameter: [containerType:'release', setTasksJson:'{"tasks":[{"taskId":"7E22DBD4287E","container":"SPECTEST"}]}']
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
    def "Display Task Information with Emtpy String Set Tasks"() {
        when: 'a procedure runs'

        def result = dsl """
                runProcedure(
                    projectName: '$projectName',
                    procedureName: 'Display Task Information',
                    actualParameter: [containerType:'assignment', setTasksJson:'']
                )
            """
        then: 'the procedure fails'
        assert result?.jobId
        waitUntil {
            jobCompleted result.jobId
        }
        assert jobStatus(result.jobId).outcome == 'error'
        assert (getJobProperty("/myJob/tasksInformation", result.jobId).toString().equals("Set Tasks field must contain valid non-empty JSON object."))
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
        assert (getJobProperty("/myJob/tasksInformation", result.jobId).toString().equals("Set Tasks field must contain valid non-empty JSON object."))
    }

    @Unroll
    def "Display Task Information with Wrong Task ID"() {
        when: 'a procedure runs'

        def result = dsl """
                runProcedure(
                    projectName: '$projectName',
                    procedureName: 'Display Task Information',
                    actualParameter: [containerType:'release', setTasksJson:'{"tasks":[{"taskId":"1234", "container":"QATEST"}]}']
                )
            """
        then: 'the procedure fails'
        assert result?.jobId
        waitUntil {
            jobCompleted result.jobId
        }
        assert jobStatus(result.jobId).outcome == 'error'
        assert (getJobProperty("/myJob/tasksInformation", result.jobId).toString().equals("{\"message\":\"Task 1234 not found in Release QATEST\"}"))
    }

}
