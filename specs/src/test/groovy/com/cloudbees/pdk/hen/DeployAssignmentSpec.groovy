package com.cloudbees.pdk.hen

import com.electriccloud.spec.PluginSpockTestSupport
import spock.lang.Shared

class DeployAssignmentSpec extends PluginSpockTestSupport {
    @Shared
    ISPW ispw = ISPW.create()

    def 'create release'() {
        when:
        def r = ispw.createRelease
            .releaseId('RELEASE02')
            .application(ISPW.app())
            .stream(ISPW.stream())
            .description('some test description')
            .resultPropertySheet('/myJob/result')
            .resultFormat('json')
            .run()
        then:
        assert r.successful
    }

    def 'generate tasks in assignment'() {
        when:
        def r = ispw.generateTasksinAssignment
            .assignmentId('PLAY000004')
            .level('DEV1')
            .resultFormat('json')
            .runtimeConfig('TEST')
            .resultPropertySheet('/myJob/result')
            .run()
        then:
        assert r.successful
    }

    def 'promote assignment'() {
        when:
        def r = ispw.promoteAssignment
            .assignmentId('PLAY000004')
            .level('DEV1')
            .runtimeConfig('ISPW')
            .resultPropertySheet('/myJob/result')
            .resultFormat('json')
            .run()
        then:
        assert r.successful
    }

    def 'deploy assignment'() {
        when:
        def r = ispw.deployAssignment
            .assignmentId('PLAY000004')
            .level('DEV1')
            .runtimeConfig('ISPW')
            .resultFormat('json')
            .resultPropertySheet('/myJob/result')
            .run()
        then:
        assert r.successful
    }

    def 'promote release'() {
        when:
        def r = ispw.deployRelease
            .releaseId('RELEASE1')
            .level('DEV1')
            .runtimeConfig('ISPW')
            .resultFormat('json')
            .resultPropertySheet('/myJob/result')
            .run()
        then:
        assert r.successful
    }
}


