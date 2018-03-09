import org.apache.commons.lang.StringUtils
import spock.lang.*

class LoadTask extends ECISPWPluginHelper {

    static def projectName = 'EC-ISPW Specs LoadTask'

    def doSetupSpec() {
        createConfiguration('specConfig')
        dslFile 'dsl/LoadTask/LoadTask.dsl', [projName: projectName]
    }

    def doCleanupSpec() {
        dsl "deleteProject '$projectName'"
    }

    @Unroll
    def "Load Task"() {
        when: 'a procedure runs'

        def result = dsl """
                runProcedure(
                    projectName: '$projectName',
                    procedureName: 'Load Task To Assignment'
                )
            """
        then: 'the procedure finishes successfully'
        assert result?.jobId
        waitUntil {
            jobCompleted result.jobId
        }
        assert jobStatus(result.jobId).outcome == 'success'
        assert StringUtils.isNotEmpty(getJobProperty("/myJob/loadTask", result.jobId).toString())
    }

    @Unroll
    def "Load Task to Wrong Assignment ID"() {
        when: 'a procedure runs'

        def result = dsl """
                runProcedure(
                    projectName: '$projectName',
                    procedureName: 'Load Task To Assignment',
                    actualParameter: ['assignmentId':'1234']
                )
            """
        then: 'the procedure fails'
        assert result?.jobId
        waitUntil {
            jobCompleted result.jobId
        }
        assert jobStatus(result.jobId).outcome == 'error'
        //TODO: Workaround 404 response
//        assert getJobProperty("/myJob/loadTask", result.jobId).toString().equals("{}");
    }

    @Unroll
    def "Load Task with Wrong Stream Name"() {
        when: 'a procedure runs'

        def result = dsl """
                    runProcedure(
                        projectName: '$projectName',
                        procedureName: 'Load Task To Assignment',
                        actualParameter: ['stream':'1234']
                    )
                """
        then: 'the procedure fails'
        assert result?.jobId
        waitUntil {
            jobCompleted result.jobId
        }
        assert jobStatus(result.jobId).outcome == 'error'
        //TODO: Workaround 404 response
//        assert getJobProperty("/myJob/loadTask", result.jobId).toString().equals("{}");
    }
}