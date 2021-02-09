package com.cloudbees.pdk.hen

import com.electriccloud.spec.PluginSpockTestSupport
import spock.lang.Shared

class PromoteAssignmentSpec extends PluginSpockTestSupport {

    @Shared
    ISPW ispw = ISPW.create()
    @Shared
    def runOptions = new RunOptions(timeout: 10)

    def 'promote assignment'() {
        when:
        def r = ispw.promoteAssignment
            .assignmentId('PLAY000005')
            .level('DEV1')
            .runtimeConfig('ISPW')
            .resultPropertySheet('/myJob/result')
            .resultFormat('json')
            .callbackCredential('admin', ISPW.password())
            .run(runOptions)
        then:
        assert r.successful
    }

    def 'promote assignment conflict'() {
        when:
        def r = ispw.promoteAssignment
            .assignmentId('PLAY000006')
            .level('DEV1')
            .runtimeConfig('ISPW')
            .resultPropertySheet('/myJob/result')
            .resultFormat('json')
            .callbackCredential('admin', ISPW.password())
            .run(runOptions)
        then:
        assert !r.successful
    }

}
