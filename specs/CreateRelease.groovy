import spock.lang.*
import org.apache.commons.lang.RandomStringUtils

class CreateRelease extends ECISPWPluginHelper {

    static def projectName = 'EC-ISPW Specs CreateRelease'

    def doSetupSpec() {
        createConfiguration('specConfig')
        dslFile 'dsl/CreateRelease/CreateRelease.dsl', [projName: projectName]
    }

    def doCleanupSpec() {
        dsl "deleteProject '$projectName'"
    }

    @Unroll
    def "Create Release with Release ID"() {
        when: 'a procedure runs'

        def result = dsl """
                runProcedure(
                    projectName: '$projectName',
                    procedureName: 'CreateRelease',
                    actualParameter: ['releaseId':org.apache.commons.lang.RandomStringUtils.random(8, true, true)]
                )
            """
        then: 'the procedure finishes successfully'
        assert result?.jobId
        waitUntil {
            jobCompleted result.jobId
        }
        assert jobStatus(result.jobId).outcome == 'success'
    }

    def "Create Release with Release Prefix"() {
        when: 'a procedure runs'

        def result = dsl """
                runProcedure(
                    projectName: '$projectName',
                    procedureName: 'CreateRelease',
                    actualParameter: ['releasePrefix':'EFTEST']
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
    def "Create Release with Release Id and Prefix"() {
        when: 'a procedure runs'
        def result = dsl """
                runProcedure(
                    projectName: '$projectName',
                    procedureName: 'CreateRelease',
                    actualParameter: ['releaseId':org.apache.commons.lang.RandomStringUtils.random(8, true, true), 'releasePrefix':'EFTEST']
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
    def "Create Release with wrong stream name"() {
        when: 'a procedure runs'
        def result = dsl """
                    runProcedure(
                        projectName: '$projectName',
                        procedureName: 'CreateRelease',
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
    def "Create Release with wrong application name"() {
        when: 'a procedure runs'
        def result = dsl """
                        runProcedure(
                            projectName: '$projectName',
                            procedureName: 'CreateRelease',
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
}
