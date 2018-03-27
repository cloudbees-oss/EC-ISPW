import org.apache.commons.lang.StringUtils
import spock.lang.*

class DeployRelease extends ECISPWPluginHelper {

    static def projectName = 'EC-ISPW Specs DeployRelease'

    def doSetupSpec() {
        createConfiguration('specConfig')
        dslFile 'dsl/DeployRelease/DeployRelease.dsl', [projName: projectName]
    }

    def doCleanupSpec() {
//        dsl "deleteProject '$projectName'"
    }

    @Unroll
    def "Deploy Release"() {
        when: 'a procedure runs'

        def result = dsl """
                runProcedure(
                    projectName: '$projectName',
                    procedureName: 'Deploy Release'
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
    def "Deploy Release with Wrong Release ID"() {
        when: 'a procedure runs'

        def result = dsl """
                runProcedure(
                    projectName: '$projectName',
                    procedureName: 'Deploy Release',
                    actualParameter: ['releaseId':'1234']
                )
            """
        then: 'the procedure fails'
        assert result?.jobId
        waitUntil {
            jobCompleted result.jobId
        }
        assert jobStatus(result.jobId).outcome == 'error'
        assert getJobProperty("/myJob/deployResult", result.jobId).toString().equals("Container with type R and identifier 1234       does not exist. Try again with a valid container.");
    }

    @Unroll
    def "Deploy Release with Wrong Stage"() {
        when: 'a procedure runs'

        def result = dsl """
                    runProcedure(
                        projectName: '$projectName',
                        procedureName: 'Deploy Release',
                        actualParameter: ['level':'DEV9']
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
