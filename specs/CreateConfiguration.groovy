import spock.lang.*

import com.electriccloud.spec.PluginSpockTestSupport

class CreateConfiguration extends PluginSpockTestSupport {

    @Shared
    def validUsername

    @Shared
    def validPassword

    @Shared
    def validInstance

    @Shared
    def validSRID

    @Shared
    def validDesc


    def doSetupSpec() {
        validUsername = System.getenv('ISPW_USERNAME') ?: '?'
        validPassword = System.getenv('ISPW_PASSWORD') ?: '225db5d0-8aea-42a2-a216-c23e42c39c30'
        validInstance = System.getenv('ISPW_URL') ?: 'http://192.168.96.182:2020'
        validSRID = System.getenv('ISPW_SRID') ?: 'ISPW'
        validDesc = System.getenv('ISPW_SRID') ?: 'ISPW Plugin config description.'
    }

    @Unroll
    def "Create configuration with valid params"() {
        given: 'configuration name'
        def configName = randomize('my test config')
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
