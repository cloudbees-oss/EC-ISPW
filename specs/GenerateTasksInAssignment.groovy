import org.apache.commons.lang.StringUtils
import spock.lang.*

class GenerateTasksInAssignment extends ECISPWPluginHelper {

    static def projectName = 'EC-ISPW Specs GenerateTasksInAssignment'

    def doSetupSpec() {
        createConfiguration('specConfig')
        dslFile 'dsl/GenerateTasksInAssignment/GenerateTasksInAssignment.dsl', [projName: projectName]
    }

    def doCleanupSpec() {
        dsl "deleteProject '$projectName'"
    }

    //TODO: Check what if COBOL Code will have syntax error -> one more testcase?
    @Unroll
    def "Generate Tasks In Assignment"() {
        when: 'a procedure runs'

        def result = dsl """
                runProcedure(
                    projectName: '$projectName',
                    procedureName: 'Generate Tasks In Assignment',
                                        actualParameter: ['eventz':'[{"name":"completed","method":"PUT","url":"http://localhost"},{"name":"failed","method":"PUT","url":"http://localhost"},{"name":"terminated","method":"PUT","url":"http://localhost"}]']
                )
            """
        then: 'the procedure finishes successfully'
        assert result?.jobId
        waitUntil {
            jobCompleted result.jobId
        }
        assert jobStatus(result.jobId).outcome == 'success'
        assert StringUtils.isNotEmpty(getJobProperty("/myJob/generateAssignmentTasks", result.jobId).toString())

    }

    @Unroll
    def "Generate Tasks In Assignment With Wrong Assignment ID"() {
        when: 'a procedure runs'

        def result = dsl """
                runProcedure(
                    projectName: '$projectName',
                    procedureName: 'Generate Tasks In Assignment',
                    actualParameter: ['assignmentId':'1234', 'eventz':'[{"name":"completed","method":"PUT","url":"http://localhost"},{"name":"failed","method":"PUT","url":"http://localhost"},{"name":"terminated","method":"PUT","url":"http://localhost"}]']
                )
            """
        then: 'the procedure fails'
        assert result?.jobId
        waitUntil {
            jobCompleted result.jobId
        }
        assert jobStatus(result.jobId).outcome == 'error'
        assert getJobProperty("/myJob/generateAssignmentTasks", result.jobId).toString().equals("Container with type A and identifier 1234       does not exist. Try again with a valid container.")
    }
}
