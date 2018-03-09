import org.apache.commons.lang.StringUtils
import spock.lang.*

class GetReleaseTaskList extends ECISPWPluginHelper {

    static def projectName = 'EC-ISPW Specs GetReleaseTaskList'

    def doSetupSpec() {
        createConfiguration('specConfig')
        dslFile 'dsl/GetReleaseTaskList/GetReleaseTaskList.dsl', [projName: projectName]
    }

    def doCleanupSpec() {
        dsl "deleteProject '$projectName'"
    }

    @Unroll
    def "Get Release Task List"() {
        when: 'a procedure runs'

        def result = dsl """
                runProcedure(
                    projectName: '$projectName',
                    procedureName: 'Get Release Task List'
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
    def "Get Release Task List For Empty Release"() {
        when: 'a procedure runs'

        def result = dsl """
                  runProcedure(
                      projectName: '$projectName',
                      procedureName: 'Get Release Task List',
                      actualParameter: ['releaseId':'QATEST']
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
    def "Get Non-existing Release Task List"() {
        when: 'a procedure runs'

        def result = dsl """
                   runProcedure(
                       projectName: '$projectName',
                       procedureName: 'Get Release Task List',
                       actualParameter: ['releaseId':'1234']
                   )
               """
        then: 'the procedure failes'
        assert result?.jobId
        waitUntil {
            jobCompleted result.jobId
        }
        assert jobStatus(result.jobId).outcome == 'success'
        assert getJobProperty("/myJob/releaseTask", result.jobId).toString().equals("{}");
    }
}
