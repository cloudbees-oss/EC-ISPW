import org.apache.commons.lang.StringUtils
import spock.lang.*

class GetReleaseInformation extends ECISPWPluginHelper {

    static def projectName = 'EC-ISPW Specs GetReleaseInformation'

    def doSetupSpec() {
        createConfiguration('specConfig')
        dslFile 'dsl/GetReleaseInformation/GetReleaseInformation.dsl', [projName: projectName]
    }

    def doCleanupSpec() {
        dsl "deleteProject '$projectName'"
    }

    @Unroll
    def "Get Release Information"() {
        when: 'a procedure runs'

        def result = dsl """
                runProcedure(
                    projectName: '$projectName',
                    procedureName: 'Get Release Information'
                )
            """
        then: 'the procedure finishes successfully'
        assert result?.jobId
        waitUntil {
            jobCompleted result.jobId
        }
        assert jobStatus(result.jobId).outcome == 'success'
        assert StringUtils.isNotEmpty(getJobProperty("/myJob/releaseInformation", result.jobId).toString())
    }

    @Unroll
    def "Get Non-existing Release Information"() {
        when: 'a procedure runs'

        def result = dsl """
                   runProcedure(
                       projectName: '$projectName',
                       procedureName: 'Get Release Information',
                       actualParameter: ['releaseId':'1234']
                   )
               """
        then: 'the procedure failes'
        assert result?.jobId
        waitUntil {
            jobCompleted result.jobId
        }
        assert jobStatus(result.jobId).outcome == 'error'
        assert getJobProperty("/myJob/releaseInformation", result.jobId).toString().equals("Release with id 1234 not found.");
    }
}
