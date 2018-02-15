import spock.lang.*

class CreateAssignment extends ECISPWPluginHelper {

    static def projectName = 'EC-ISPW Specs CreateAssignement'
    
    def doSetupSpec() {
        createConfiguration('specConfig')
        dslFile 'dsl/CreateAssignment/CreateAssignment.dsl', [projName: projectName]
    }

    def doCleanupSpec() {
//        dsl "deleteProject '$projectName'"
    }
    
    @Unroll
    def "Create Assignment with valid params"() {
        when: 'a procedure runs'
        def result = dsl """
                runProcedure(
                    projectName: '$projectName',
                    procedureName: 'CreateAssignment',
                    actualParameter: [config: 'specConfig']
                )
            """
        then: 'the procedure finishes successfully'
        assert result?.jobId
        waitUntil {
            jobCompleted result.jobId
        }
        assert jobStatus(result.jobId).outcome == 'success'
    }
}
