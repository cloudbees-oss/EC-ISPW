import org.apache.commons.lang.StringUtils
import spock.lang.*

class GetSetInformation extends ECISPWPluginHelper {

    static def projectName = 'EC-ISPW Specs GetSetInformation'

    def doSetupSpec() {
        createConfiguration('specConfig')
        dslFile 'dsl/GetSetInformation/GetSetInformation.dsl', [projName: projectName]
        dslFile 'dsl/GetSetInformation/GetSetInformationWrongSet.dsl', [projName: projectName, config: 'specConfig']

    }

    def doCleanupSpec() {
//        dsl "deleteProject '$projectName'"
    }

    @Unroll
    def "Get Set Information"() {
        when: 'a procedure runs'

        def result = dsl """
                runProcedure(
                    projectName: '$projectName',
                    procedureName: 'Get Set Information'
                )
            """
        then: 'the procedure finishes successfully'
        assert result?.jobId
        waitUntil {
            jobCompleted result.jobId
        }
        assert jobStatus(result.jobId).outcome == 'success'
        assert StringUtils.isNotEmpty(getJobProperty("/myJob/setInfo", result.jobId).toString())

    }

    @Unroll
    def "Get Non-existing Set Information"() {
        when: 'a procedure runs'

        def result = dsl """
                   runProcedure(
                       projectName: '$projectName',
                       procedureName: 'Get Set Information Wrong Set'
                   )
               """
        then: 'the procedure fails'
        assert result?.jobId
        waitUntil {
            jobCompleted result.jobId
        }
        assert jobStatus(result.jobId).outcome == 'error'
        assert getJobProperty("/myJob/setInfo", result.jobId).toString().equals("Could not find Set 1234      . Enter a valid Set name or create a new Set.");
    }
}
