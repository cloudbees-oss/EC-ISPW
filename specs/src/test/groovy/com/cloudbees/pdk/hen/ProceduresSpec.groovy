package com.cloudbees.pdk.hen

import com.cloudbees.pdk.hen.procedures.GenerateTasksinRelease
import com.cloudbees.pdk.hen.procedures.RegressRelease
import com.electriccloud.spec.PluginSpockTestSupport
import spock.lang.Ignore
import spock.lang.Shared

//@Ignore
class ProceduresSpec extends PluginSpockTestSupport {
    @Shared
    ISPW ispw = ISPW.create()
    @Shared
    def runOptions = new RunOptions(timeout: 10)

    @Ignore
    def 'create release'() {
        when:
        def r = ispw.createRelease
            .releaseId('RELEASE02')
            .application(ISPW.app())
            .stream(ISPW.stream())
            .description('some test description')
            .resultPropertySheet('/myJob/result')
            .resultFormat('json')
            .run(runOptions)
        then:
        assert r.successful
    }

    def 'get assignment'() {
        when:
        def r = ispw.getAssignmentInformation
            .assignmentId(ISPW.assignment())
            .resultFormat('json')
            .resultPropertySheet('/myJob/result')
            .run(runOptions)
        then:
        assert r.successful
    }

    @Ignore
    def 'get release'() {
        when:
        def r = ispw.getReleaseInformation
            .releaseId('RELEASE1')
            .resultPropertySheet('/myJob/result')
            .resultFormat('json')
            .run(runOptions)
        then:
        assert r.successful
    }

    def 'create assignment'() {
        //when:
        //def r = ispw.createAssignment.stream(ISPW.stream())
    }

    @Ignore
    def 'generate tasks in assignment'() {
        when:
        def r = ispw.generateTasksinAssignment
            .assignmentId('PLAY000006')
            .level('DEV1')
            .resultFormat('json')
            .runtimeConfig('TEST')
            .callbackCredential('admin', ISPW.password())
            .resultPropertySheet('/myJob/result')
            .run(runOptions)
        then:
        assert r.successful
    }

    @Ignore
    def 'regress release'() {
        when:
        def r = ispw.regressRelease
            .releaseId('RELEASE3')
            .level('DEV1')
            .resultFormat('json')
            .runtimeConfig('TEST')
            .changeType(RegressRelease.ChangeTypeOptions.EMERGENCY_E_)
            .resultPropertySheet('/myJob/result')
            .callbackCredential('admin', ISPW.password())
            .run(runOptions)
        then:
        assert r.successful
    }

    @Ignore
    def 'promote assignment'() {
        when:
        def r = ispw.promoteAssignment
            .assignmentId('PLAY000007')
            .level('DEV1')
            .runtimeConfig('ISPW')
            .resultPropertySheet('/myJob/result')
            .resultFormat('json')
            .callbackCredential('admin', ISPW.password())
            .run(runOptions)
        then:
        assert r.successful
    }

    @Ignore
    def 'deploy assignment'() {
        when:
        def r = ispw.deployAssignment
            .assignmentId('PLAY000004')
            .level('DEV2')
            .runtimeConfig('ISPW')
            .resultFormat('json')
            .callbackCredential('admin', ISPW.password())
            .resultPropertySheet('/myJob/result')
            .run(runOptions)
        then:
        assert r.successful
    }

    @Ignore
    def 'promote release'() {
        when:
        def r = ispw.promoteRelease
            .releaseId('RELEASE3')
            .level('STG1')
            .runtimeConfig('ISPW')
            .resultFormat('json')
            .resultPropertySheet('/myJob/result')
            .callbackCredential('admin', ISPW.password())
            .run(runOptions)
        then:
        assert r.successful
    }

    @Ignore
    def 'deploy release'() {
        when:
        def r = ispw.deployRelease
            .releaseId('RELEASE01')
            .level('QA1')
            .runtimeConfig('ISPW')
            .resultFormat('json')
            .resultPropertySheet('/myJob/result')
            .run(runOptions)
        then:
        assert r.successful
    }
}


