import spock.lang.*
import org.apache.commons.lang.StringUtils

class PromoteRelease extends ECISPWPluginHelper {

    static def projectName = 'EC-ISPW Specs PromoteRelease'

    def doSetupSpec() {
        createConfiguration('specConfig')
        dslFile 'dsl/PromoteRelease/PromoteRelease.dsl', [projName: projectName]
        dslFile 'dsl/PromoteRelease/PromoteReleaseComplex.dsl', [projName: projectName]
    }

    def doCleanupSpec() {
        dsl "deleteProject '$projectName'"
    }

    // TODO: this test will not work properly while we'll not get callbacks working.
    // TODO: place cleanup to run after Promote. not before.
//    @Unroll
//    def "Promote Release"() {
//        when: 'a procedure runs'
//
//        def result = dsl """
//                runProcedure(
//                    projectName: '$projectName',
//                    procedureName: 'Promote Release With Checks'
//                )
//            """
//        then: 'the procedure finishes successfully'
//        assert result?.jobId
//        waitUntil {
//            jobCompleted result.jobId
//        }
//        assert jobStatus(result.jobId).outcome == 'success'
//        assert StringUtils.isNotEmpty(getJobProperty("/myJob/promoteReleaseResult", result.jobId).toString())
//    }

    @Unroll
    def "Promote Release with Wrong Release ID"() {
        when: 'a procedure runs'

        def result = dsl """
                runProcedure(
                    projectName: '$projectName',
                    procedureName: 'Promote Release',
                    actualParameter: ['releaseId':'1234']
                )
            """
        then: 'the procedure fails'
        assert result?.jobId
        waitUntil {
            jobCompleted result.jobId
        }
        assert jobStatus(result.jobId).outcome == 'error'
        assert getJobProperty("/myJob/promoteReleaseResult", result.jobId).toString().equals("Container with type R and identifier 1234       does not exist. Try again with a valid container.")
    }

    @Unroll
    def "Promote Release with Wrong Stage"() {
        when: 'a procedure runs'

        def result = dsl """
                    runProcedure(
                        projectName: '$projectName',
                        procedureName: 'Promote Release',
                        actualParameter: ['level':'DEV9']
                    )
                """
        then: 'the procedure fails'
        assert result?.jobId
        waitUntil {
            jobCompleted result.jobId
        }
        assert jobStatus(result.jobId).outcome == 'error'
        assert getJobProperty("/myJob/promoteReleaseResult", result.jobId).toString().equals("Severe error: Level DEV9 was not found in application DEMO and stream DEMO    . Application Definition meta-data may havechanged.")
    }
}
