import org.apache.commons.lang.StringUtils
import spock.lang.*

class DeployAssignment extends ECISPWPluginHelper {

    static def projectName = 'EC-ISPW Specs DeployAssignment'

    def doSetupSpec() {
        createConfiguration('specConfig')
        dslFile 'dsl/DeployAssignment/DeployAssignment.dsl', [projName: projectName]
    }

    def doCleanupSpec() {
//        dsl "deleteProject '$projectName'"
    }

    @Unroll
    def "Deploy Assignment"() {
        when: 'a procedure runs'

        def result = dsl """
                runProcedure(
                    projectName: '$projectName',
                    procedureName: 'Deploy Assignment'
                )
            """
        then: 'the procedure finishes successfully'
        assert result?.jobId
        waitUntil {
            jobCompleted result.jobId
        }
        assert jobStatus(result.jobId).outcome == 'success'
        assert StringUtils.isNotEmpty(getJobProperty("/myJob/deployResult", result.jobId).toString())
    }

    @Unroll
    def "Deploy Assignment with Wrong Assignment ID"() {
        when: 'a procedure runs'

        def result = dsl """
                runProcedure(
                    projectName: '$projectName',
                    procedureName: 'Deploy Assignment',
                    actualParameter: ['assignmentId':'1234']
                )
            """
        then: 'the procedure fails'
        assert result?.jobId
        waitUntil {
            jobCompleted result.jobId
        }
        assert jobStatus(result.jobId).outcome == 'error'
        assert getJobProperty("/myJob/deployResult", result.jobId).toString().equals("Container with type A and identifier 1234       does not exist. Try again with a valid container.");
    }

    @Unroll
    def "Deploy Assignment with Wrong Stage"() {
        when: 'a procedure runs'

        def result = dsl """
                    runProcedure(
                        projectName: '$projectName',
                        procedureName: 'Deploy Assignment',
                        actualParameter: ['level':'DEV9 ']
                    )
                """
        then: 'the procedure fails'
        assert result?.jobId
        waitUntil {
            jobCompleted result.jobId
        }
        assert jobStatus(result.jobId).outcome == 'error'

        assert getJobProperty("/myJob/deployResult", result.jobId).toString() ==~ /Set S.+ must contain tasks before a lock set request is made./
    }
}
