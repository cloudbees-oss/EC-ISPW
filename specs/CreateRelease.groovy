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
                    actualParameter: ['releasePrefix':'EFTE']
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
        // Error message "Release Identifier and Release Prefix are both set. Only one can be set."
    }

    @Unroll
    def "Create Release with wrong stream name"() {
        when: 'a procedure runs'
        def result = dsl """
                    runProcedure(
                        projectName: '$projectName',
                        procedureName: 'CreateRelease',
                        actualParameter: ['stream':'WRONGSTREAM', 'releasePrefix':'EFTEST']
                    )
                """
        then: 'the procedure fails'
        assert result?.jobId
        waitUntil {
            jobCompleted result.jobId
        }
        assert jobStatus(result.jobId).outcome == 'error'
        //Error message "DEMO WRONGSTR is not a valid application stream. Use an existing application stream and try again."
    }

    @Unroll
    def "Create Release with wrong application name"() {
        when: 'a procedure runs'
        def result = dsl """
                        runProcedure(
                            projectName: '$projectName',
                            procedureName: 'CreateRelease',
                            actualParameter: ['application':'WRONGAPP', 'releasePrefix':'EFTEST']
                        )
                    """
        then: 'the procedure fails'
        assert result?.jobId
        waitUntil {
            jobCompleted result.jobId
        }
        assert jobStatus(result.jobId).outcome == 'error'
        //Error message "WRON DEMO     is not a valid application stream. Use an existing application stream and try again."
    }
}
