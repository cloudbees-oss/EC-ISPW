package com.cloudbees.pdk.hen.procedures

import groovy.transform.AutoClone
import com.cloudbees.pdk.hen.*

@AutoClone
//generated
class GetReleaseTaskList extends Procedure {

    static GetReleaseTaskList create(Plugin plugin) {
        return new GetReleaseTaskList(procedureName: 'Get Release Task List', plugin: plugin, )
    }


    GetReleaseTaskList flush() {
        this.flushParams()
        return this
    }

    //Generated
    
    GetReleaseTaskList config(String config) {
        this.addParam('config', config)
        return this
    }
    
    GetReleaseTaskList level(String level) {
        this.addParam('level', level)
        return this
    }
    
    GetReleaseTaskList releaseId(String releaseId) {
        this.addParam('releaseId', releaseId)
        return this
    }
    
    GetReleaseTaskList resultFormat(String resultFormat) {
        this.addParam('resultFormat', resultFormat)
        return this
    }
    
    GetReleaseTaskList resultPropertySheet(String resultPropertySheet) {
        this.addParam('resultPropertySheet', resultPropertySheet)
        return this
    }
    

    
}