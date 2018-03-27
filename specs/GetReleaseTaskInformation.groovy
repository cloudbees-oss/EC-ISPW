import org.apache.commons.lang.StringUtils
import spock.lang.*

class GetReleaseTaskInformation extends ECISPWPluginHelper {

    static def projectName = 'EC-ISPW Specs GetReleaseTaskInformation'

    def doSetupSpec() {
        createConfiguration('specConfig')
        dslFile 'dsl/GetReleaseTaskInformation/GetReleaseTaskInformation.dsl', [projName: projectName]
    }

    def doCleanupSpec() {
//        dsl "deleteProject '$projectName'"
    }

    @Unroll
    def "Get Release Task Information"() {
        when: 'a procedure runs'

        def result = dsl """
                runProcedure(
                    projectName: '$projectName',
                    procedureName: 'Get Release Task Information'
                )
            """
        then: 'the procedure finishes successfully'
        assert result?.jobId
        waitUntil {
            jobCompleted result.jobId
        }
        assert jobStatus(result.jobId).outcome == 'success'
        assert StringUtils.isNotEmpty(getJobProperty("/myJob/releaseTask", result.jobId).toString())
    }

    @Unroll
    def "Get Non-existing Release Task Information"() {
        when: 'a procedure runs'

        def result = dsl """
                   runProcedure(
                       projectName: '$projectName',
                       procedureName: 'Get Release Task Information',
                       actualParameter: ['releaseId':'1234']
                   )
               """
        then: 'the procedure failes'
        assert result?.jobId
        waitUntil {
            jobCompleted result.jobId
        }
        assert jobStatus(result.jobId).outcome == 'error'
        assert getJobProperty("/myJob/releaseTask", result.jobId).toString().equals("Task 7E222BC9CB84 not found in Release 1234");
    }

    @Unroll
    def "Get Release Non-existing Task Information"() {
        when: 'a procedure runs'

        def result = dsl """
                     runProcedure(
                         projectName: '$projectName',
                         procedureName: 'Get Release Task Information',
                         actualParameter: ['taskId':'1234']
                     )
                 """
        then: 'the procedure failes'
        assert result?.jobId
        waitUntil {
            jobCompleted result.jobId
        }
        assert jobStatus(result.jobId).outcome == 'error'
        assert getJobProperty("/myJob/releaseTask", result.jobId).toString().equals("Task 1234 not found in Release QATEST");
    }
}
