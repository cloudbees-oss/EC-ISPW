import org.apache.commons.lang.StringUtils
import spock.lang.*

class GetAssignmentInformation extends ECISPWPluginHelper {

    static def projectName = 'EC-ISPW Specs GetAssignmentInformation'

    def doSetupSpec() {
        createConfiguration('specConfig')
        dslFile 'dsl/GetAssignmentInformation/GetAssignmentInformation.dsl', [projName: projectName]
    }

    def doCleanupSpec() {
//        dsl "deleteProject '$projectName'"
    }

    @Unroll
    def "Get Assignment Information"() {
        when: 'a procedure runs'

        def result = dsl """
                runProcedure(
                    projectName: '$projectName',
                    procedureName: 'Get Assignment Information'
                )
            """
        then: 'the procedure finishes successfully'
        assert result?.jobId
        waitUntil {
            jobCompleted result.jobId
        }
        assert jobStatus(result.jobId).outcome == 'success'
        assert StringUtils.isNotEmpty(getJobProperty("/myJob/assignmentInfo", result.jobId).toString())
    }
    
    @Unroll
       def "Get Non-existing Assignment Information"() {
           when: 'a procedure runs'
   
           def result = dsl """
                   runProcedure(
                       projectName: '$projectName',
                       procedureName: 'Get Assignment Information',
                       actualParameter: ['assignmentId':'1234']
                   )
               """
           then: 'the procedure failes'
           assert result?.jobId
           waitUntil {
               jobCompleted result.jobId
           }
           assert jobStatus(result.jobId).outcome == 'error'
           assert getJobProperty("/myJob/assignmentInfo", result.jobId).toString().equals("Assignment with id 1234 not found.");
       }
}
