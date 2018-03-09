import org.apache.commons.lang.StringUtils
import spock.lang.*

class GetAssignmentTaskInformation extends ECISPWPluginHelper {

    static def projectName = 'EC-ISPW Specs GetAssignmentTaskInformation'

    def doSetupSpec() {
        createConfiguration('specConfig')
        dslFile 'dsl/GetAssignmentTaskInformation/GetAssignmentTaskInformation.dsl', [projName: projectName]
    }

    def doCleanupSpec() {
        dsl "deleteProject '$projectName'"
    }

    @Unroll
    def "Get Assignment Task Information"() {
        when: 'a procedure runs'

        def result = dsl """
                runProcedure(
                    projectName: '$projectName',
                    procedureName: 'Get Assignment Task Information'
                )
            """
        then: 'the procedure finishes successfully'
        assert result?.jobId
        waitUntil {
            jobCompleted result.jobId
        }
        assert jobStatus(result.jobId).outcome == 'success'
        assert StringUtils.isNotEmpty(getJobProperty("/myJob/assignmentTask", result.jobId).toString())
    }

    @Unroll
    def "Get Non-existing Assignment Task Information"() {
        when: 'a procedure runs'

        def result = dsl """
                   runProcedure(
                       projectName: '$projectName',
                       procedureName: 'Get Assignment Task Information',
                       actualParameter: ['assignmentId':'1234']
                   )
               """
        then: 'the procedure failes'
        assert result?.jobId
        waitUntil {
            jobCompleted result.jobId
        }
        assert jobStatus(result.jobId).outcome == 'error'
        assert getJobProperty("/myJob/assignmentTask", result.jobId).toString().equals("Task 7E222BC9CB84 not found in Assignment 1234");
    }

    @Unroll
    def "Get Assignment Non-existing Task Information"() {
        when: 'a procedure runs'

        def result = dsl """
                     runProcedure(
                         projectName: '$projectName',
                         procedureName: 'Get Assignment Task Information',
                         actualParameter: ['taskId':'1234']
                     )
                 """
        then: 'the procedure failes'
        assert result?.jobId
        waitUntil {
            jobCompleted result.jobId
        }
        assert jobStatus(result.jobId).outcome == 'error'
        assert getJobProperty("/myJob/assignmentTask", result.jobId).toString().equals("Task 1234 not found in Assignment DEMO000040");
    }
}