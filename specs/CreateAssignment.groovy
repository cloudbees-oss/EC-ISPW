import spock.lang.*

class CreateAssignment extends ECISPWPluginHelper {

    static def projectName = 'EC-ISPW Specs CreateAssignment'

    def doSetupSpec() {
        createConfiguration('specConfig')
        dslFile 'dsl/CreateAssignment/CreateAssignment.dsl', [projName: projectName]
    }

    def doCleanupSpec() {
        dsl "deleteProject '$projectName'"
    }

    @Unroll
    def "Create Assignment with valid params"() {
        when: 'a procedure runs'
        def result = dsl """
                runProcedure(
                    projectName: '$projectName',
                    procedureName: 'CreateAssignment'
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
    def "Create Assignment with wrong release"() {
        when: 'a procedure runs'
        def result = dsl """
                runProcedure(
                    projectName: '$projectName',
                    procedureName: 'CreateAssignment',
                    actualParameter: ['releaseId':'WRONGRELEASE']
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
    def "Create Assignment with wrong stream name"() {
        when: 'a procedure runs'
        def result = dsl """
                    runProcedure(
                        projectName: '$projectName',
                        procedureName: 'CreateAssignment',
                        actualParameter: ['stream':'WRONGSTREAM']
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
    def "Create Assignment with wrong application name"() {
        when: 'a procedure runs'
        def result = dsl """
                        runProcedure(
                            projectName: '$projectName',
                            procedureName: 'CreateAssignment',
                            actualParameter: ['application':'WRONGAPP']
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
    def "Create Assignment with wrong Stage"() {
        when: 'a procedure runs'
        def result = dsl """
                            runProcedure(
                                projectName: '$projectName',
                                procedureName: 'CreateAssignment',
                                actualParameter: ['defaultPath':'DEV5']
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
