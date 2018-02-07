import spock.lang.*

import com.electriccloud.spec.PluginSpockTestSupport

class CreateConfiguration extends PluginSpockTestSupport {

    static def projectName = 'EC-ISPW Specs CreateAssignement'
    
    def doSetupSpec() {
        createConfiguration('specConfig')
        dslFile 'dsl/CreateIssue/CreateIssue.dsl', [projName: projectName]
    }

    def doCleanupSpec() {
        dsl "deleteProject '$projectName'"
    }
    
    @Unroll
    def "Create Assignment with valid params"() {
        when: 'a procedure runs'
        def result = dsl """
                runProcedure(
                    projectName: '/plugins/EC-ISPW/project',
                    procedureName: 'CreateConfiguration',
                    credential: [
                        credentialName: '$configName',
                        userName: '$validUsername',
                        password: '$validPassword'
                    ],
                    actualParameter: [
                        instance: '$validInstance',
                        config: '$configName',
                        credential: '$configName',
                        srid: '$validSRID',
                        desc: '$validDesc'
                    ]
                )
            """
        then: 'the procedure finishes successfully'
        assert result?.jobId
        waitUntil {
            jobCompleted result.jobId
        }
        assert jobStatus(result.jobId).outcome == 'success'
        cleanup: 'delete configuration'
        deleteConfiguration('EC-ISPW', configName)
    }
}
