package com.cloudbees.pdk.hen

import com.electriccloud.spec.PluginSpockTestSupport
import spock.lang.Shared

class GetSetSpec extends PluginSpockTestSupport {
    //GetSet here means "get ispw object named set" and not get and set something
    @Shared
    ISPW ispw = ISPW.create()

    def 'get set'() {
        when:
        def r = ispw.getSetInformation
            .setId('S000000140')
            .resultPropertySheet('/myJob/result')
            .resultFormat('json')
            .run()
        then:
        assert r.successful
    }

    def 'get set task list'() {
        when:
        def r = ispw.getSetTaskList
            .setId('S000000140')
            .resultPropertySheet('/myJob/result')
            .resultFormat('json')
            .run()
        then:
        assert r.successful
    }

}
