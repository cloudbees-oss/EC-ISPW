import spock.lang.*

class PromoteAssignment extends ECISPWPluginHelper {

    static def projectName = 'EC-ISPW Specs PromoteAssignment'

    def doSetupSpec() {
        createConfiguration('specConfig')
        dslFile 'dsl/PromoteAssignment/PromoteAssignment.dsl', [projName: projectName]
    }

    def doCleanupSpec() {
        dsl "deleteProject '$projectName'"
    }

    @Unroll
    def "Promote Assignment"() {
        when: 'a procedure runs'

        def result = dsl """
                runProcedure(
                    projectName: '$projectName',
                    procedureName: 'Promote Assignment'
                )
            """
        then: 'the procedure finishes successfully'
        assert result?.jobId
        waitUntil {
            jobCompleted result.jobId
        }
        assert jobStatus(result.jobId).outcome == 'success'
    }

    @Unroll
    def "Promote Assignment with Wrong Assignment ID"() {
        when: 'a procedure runs'

        def result = dsl """
                runProcedure(
                    projectName: '$projectName',
                    procedureName: 'Promote Assignment',
                    actualParameter: ['assignmentId':'1234']
                )
            """
        then: 'the procedure fails'
        assert result?.jobId
        waitUntil {
            jobCompleted result.jobId
        }
        assert jobStatus(result.jobId).outcome == 'error'
    }

    @Unroll
    def "Promote Assignment with Wrong Stage"() {
        when: 'a procedure runs'

        def result = dsl """
                    runProcedure(
                        projectName: '$projectName',
                        procedureName: 'Promote Assignment',
                        actualParameter: ['level':'DEV1000']
                    )
                """
        then: 'the procedure fails'
        assert result?.jobId
        waitUntil {
            jobCompleted result.jobId
        }
        assert jobStatus(result.jobId).outcome == 'error'
    }
}
