import org.apache.commons.lang.StringUtils
import spock.lang.*

class GetSetTaskList extends ECISPWPluginHelper {

    static def projectName = 'EC-ISPW Specs GetSetTaskList'

    def doSetupSpec() {
        createConfiguration('specConfig')
        dslFile 'dsl/GetSetTaskList/GetSetTaskList.dsl', [projName: projectName]
        dslFile 'dsl/GetSetTaskList/GetSetTaskListWrongSet.dsl', [projName: projectName, config:'specConfig']
    }

    def doCleanupSpec() {
        dsl "deleteProject '$projectName'"
    }

    @Unroll
    def "Get Set Task List"() {
        when: 'a procedure runs'

        def result = dsl """
                runProcedure(
                    projectName: '$projectName',
                    procedureName: 'Get Set Task List'
                )
            """
        then: 'the procedure finishes successfully'
        assert result?.jobId
        waitUntil {
            jobCompleted result.jobId
        }
        assert jobStatus(result.jobId).outcome == 'success'
        assert StringUtils.isNotEmpty(getJobProperty("/myJob/setTasks", result.jobId).toString())
    }
    
    @Unroll
       def "Get Non-existing Set Task List"() {
           when: 'a procedure runs'
   
           def result = dsl """
                   runProcedure(
                       projectName: '$projectName',
                       procedureName: 'Get Set Task List Wrong Set'
                   )
               """
           then: 'the procedure fails'
           assert result?.jobId
           waitUntil {
               jobCompleted result.jobId
           }
           assert jobStatus(result.jobId).outcome == 'success'
           assert getJobProperty("/myJob/setTasks", result.jobId).toString().equals("{\"message\":\"Results: No tasks available.\"}");
       }
}
