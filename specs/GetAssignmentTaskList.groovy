import org.apache.commons.lang.StringUtils
import spock.lang.*

class GetAssignmentTaskList extends ECISPWPluginHelper {

    static def projectName = 'EC-ISPW Specs GetAssignmentTaskList'

    def doSetupSpec() {
        createConfiguration('specConfig')
        dslFile 'dsl/GetAssignmentTaskList/GetAssignmentTaskList.dsl', [projName: projectName]
    }

    def doCleanupSpec() {
//        dsl "deleteProject '$projectName'"
    }

    @Unroll
    def "Get Assignment Task List"() {
        when: 'a procedure runs'

        def result = dsl """
                runProcedure(
                    projectName: '$projectName',
                    procedureName: 'Get Assignment Task List'
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
    def "Get Non-existing Assignment Task List"() {
        when: 'a procedure runs'

        def result = dsl """
                   runProcedure(
                       projectName: '$projectName',
                       procedureName: 'Get Assignment Task List',
                       actualParameter: ['assignmentId':'1234']
                   )
               """
        then: 'the procedure failes'
        assert result?.jobId
        waitUntil {
            jobCompleted result.jobId
        }
        assert jobStatus(result.jobId).outcome == 'success'
        assert getJobProperty("/myJob/assignmentTask", result.jobId).toString().equals("{}");
    }
}
