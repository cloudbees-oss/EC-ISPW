import org.apache.commons.lang.StringUtils
import spock.lang.*

class PromoteAssignment extends ECISPWPluginHelper {

    static def projectName = 'EC-ISPW Specs PromoteAssignment'

    def doSetupSpec() {
        createConfiguration('specConfig')
        dslFile 'dsl/PromoteAssignment/PromoteAssignment.dsl', [projName: projectName]
        dslFile 'dsl/PromoteAssignment/PromoteAssignmentComplex.dsl', [projName: projectName]
    }

    def doCleanupSpec() {
//        dsl "deleteProject '$projectName'"
    }
    
    // TODO: this test will not work properly while we'll not get callbacks working.
    // TODO: place cleanup to run after Promote. not before.
//    @Unroll
//    def "Promote Assignment"() {
//        when: 'a procedure runs'
//
//        def result = dsl """
//                runProcedure(
//                    projectName: '$projectName',
//                    procedureName: 'Promote Assignment With Checks'
//                )
//            """
//        then: 'the procedure finishes successfully'
//        assert result?.jobId
//        waitUntil {
//            jobCompleted result.jobId
//        }
//        assert jobStatus(result.jobId).outcome == 'success'
//        assert StringUtils.isNotEmpty(getJobProperty("/myJob/promoteResult", result.jobId).toString())
//    }

    @Unroll
    def "Promote Assignment with Wrong Assignment ID"() {
        when: 'a procedure runs'

        def result = dsl """
                runProcedure(
                    projectName: '$projectName',
                    procedureName: 'Promote Assignment',
                    actualParameter: ['assignmentId':'1234']
                )
            """
        then: 'the procedure fails'
        assert result?.jobId
        waitUntil {
            jobCompleted result.jobId
        }
        assert jobStatus(result.jobId).outcome == 'error'
        assert getJobProperty("/myJob/promoteResult", result.jobId).toString().equals("Container with type A and identifier 1234       does not exist. Try again with a valid container.");
    }

    @Unroll
    def "Promote Assignment with Wrong Stage"() {
        when: 'a procedure runs'

        def result = dsl """
                    runProcedure(
                        projectName: '$projectName',
                        procedureName: 'Promote Assignment',
                        actualParameter: ['level':'DEV9']
                    )
                """
        then: 'the procedure fails'
        assert result?.jobId
        waitUntil {
            jobCompleted result.jobId
        }
        assert jobStatus(result.jobId).outcome == 'error'
        assert getJobProperty("/myJob/promoteResult", result.jobId).toString().equals("Severe error: Level DEV9 was not found in application DEMO and stream DEMO    . Application Definition meta-data may havechanged.")
    }
}
