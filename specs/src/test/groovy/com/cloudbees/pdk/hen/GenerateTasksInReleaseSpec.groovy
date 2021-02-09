package com.cloudbees.pdk.hen

import com.cloudbees.pdk.hen.procedures.GenerateTasksinRelease
import com.electriccloud.spec.PluginSpockTestSupport
import spock.lang.Shared

class GenerateTasksInReleaseSpec extends PluginSpockTestSupport {
    @Shared
    ISPW ispw = ISPW.create()
    @Shared
    def runOptions = new RunOptions(timeout: 10)

    def 'generate tasks in release'() {
        when:
        def r = ispw.generateTasksinRelease
            .releaseId(ISPW.VALID_RELEASE)
            .level('DEV1')
            .resultFormat('json')
            .runtimeConfig('TEST')
            .changeType(GenerateTasksinRelease.ChangeTypeOptions.STANDARD_S_)
            .resultPropertySheet('/myJob/result')
            .callbackCredential('admin', ISPW.password())
            .run(runOptions)
        then:
        assert r.successful
    }

    def 'generate tasks in release conflict'() {
        when:
        def r = ispw.generateTasksinRelease
            .releaseId(ISPW.INVALID_RELEASE)
            .level('DEV1')
            .resultFormat('json')
            .resultPropertySheet('/myJob/result')
            .changeType(GenerateTasksinRelease.ChangeTypeOptions.STANDARD_S_)
            .callbackCredential('admin', ISPW.password())
            .run(runOptions)
        then:
        assert !r.successful
    }


}
