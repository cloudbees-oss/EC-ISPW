import spock.lang.*
import org.apache.commons.lang.StringUtils

class RegressRelease extends ECISPWPluginHelper {

    static def projectName = 'EC-ISPW Specs RegressRelease'

    def doSetupSpec() {
        createConfiguration('specConfig')
        dslFile 'dsl/RegressRelease/RegressReleaseComplex.dsl', [projName: projectName]
        dslFile 'dsl/RegressRelease/RegressRelease.dsl', [projName: projectName]
    }

    def doCleanupSpec() {
//        dsl "deleteProject '$projectName'"
    }

// TODO: This testcase will now work without callbacks.
// Regress should be performed from STG1 level
// If Task now is at DEV1 level, there is no place to regress
//    @Unroll
//    def "Regress Release"() {
//        when: 'a procedure runs'
//
//        def result = dsl """
//                runProcedure(
//                    projectName: '$projectName',
//                    procedureName: 'Regress Release With Checks'
//                )
//            """
//        then: 'the procedure finishes successfully'
//        assert result?.jobId
//        waitUntil {
//            jobCompleted result.jobId
//        }
//        assert jobStatus(result.jobId).outcome == 'success'
//    assert StringUtils.isNotEmpty(getJobProperty("/myJob/regressReleaseResult", result.jobId).toString())
    
//    }

    @Unroll
    def "Regress Release with Wrong Release ID"() {
        when: 'a procedure runs'

        def result = dsl """
                runProcedure(
                    projectName: '$projectName',
                    procedureName: 'Regress Release',
                    actualParameter: ['releaseId':'1234']
                )
            """
        then: 'the procedure fails'
        assert result?.jobId
        waitUntil {
            jobCompleted result.jobId
        }
        assert jobStatus(result.jobId).outcome == 'error'
        assert getJobProperty("/myJob/regressReleaseResult", result.jobId).toString().equals("Container with type R and identifier 1234       does not exist. Try again with a valid container.");
    }

    @Unroll
    def "Regress Release with Wrong Stage"() {
        when: 'a procedure runs'

        def result = dsl """
                    runProcedure(
                        projectName: '$projectName',
                        procedureName: 'Regress Release',
                        actualParameter: ['level':'DEV9']
                    )
                """
        then: 'the procedure fails'
        assert result?.jobId
        waitUntil {
            jobCompleted result.jobId
        }
        assert jobStatus(result.jobId).outcome == 'error'
        assert getJobProperty("/myJob/regressReleaseResult", result.jobId).toString()==~ /Set S.+ must contain tasks before a lock set request is made./
    }
}
