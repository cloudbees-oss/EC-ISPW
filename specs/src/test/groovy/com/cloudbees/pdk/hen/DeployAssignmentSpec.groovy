package com.cloudbees.pdk.hen

import com.cloudbees.pdk.hen.procedures.RegressRelease
import com.electriccloud.spec.PluginSpockTestSupport
import spock.lang.Ignore
import spock.lang.Shared

@Ignore
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

    def 'get assignment'() {
        when:
        def r = ispw.getAssignmentInformation
            .assignmentId(ISPW.assignment())
            .resultFormat('json')
            .resultPropertySheet('/myJob/result')
            .run()
        then:
        assert r.successful
    }

    def 'get release'() {
        when:
        def r = ispw.getReleaseInformation
            .releaseId('RELEASE1')
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

    def 'generate tasks in release'() {
        when:
        def r = ispw.generateTasksinRelease
            .releaseId('RELEASE1')
            .level('DEV1')
            .resultFormat('json')
            .runtimeConfig('TEST')
            .resultPropertySheet('/myJob/result')
            .run()
        then:
        assert r.successful
    }

    def 'regress release'() {
        when:
        def r = ispw.regressRelease
            .releaseId('RELEASE1')
            .level('DEV1')
            .resultFormat('json')
            .runtimeConfig('TEST')
            .changeType(RegressRelease.ChangeTypeOptions.INCIDENTAL_I_)
            .resultPropertySheet('/myJob/result')
            .run()
        then:
        assert r.successful
    }

    def 'promote assignment'() {
        when:
        def r = ispw.promoteAssignment
            .assignmentId('PLAY000004')
            .level('STG1')
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
            .level('DEV2')
            .runtimeConfig('ISPW')
            .resultFormat('json')
            .resultPropertySheet('/myJob/result')
            .run()
        then:
        assert r.successful
    }

    def 'promote release'() {
        when:
        def r = ispw.promoteRelease
            .releaseId('RELEASE1')
            .level('STG1')
            .runtimeConfig('ISPW')
            .resultFormat('json')
            .resultPropertySheet('/myJob/result')
            .run()
        then:
        assert r.successful
    }

    def 'deploy release'() {
        when:
        def r = ispw.deployRelease
            .releaseId('RELEASE01')
            .level('QA1')
            .runtimeConfig('ISPW')
            .resultFormat('json')
            .resultPropertySheet('/myJob/result')
            .run()
        then:
        assert r.successful
    }
}


