package com.cloudbees.pdk.hen.procedures

import groovy.transform.AutoClone
import com.cloudbees.pdk.hen.*

@AutoClone
//generated
class GetReleaseInformation extends Procedure {

    static GetReleaseInformation create(Plugin plugin) {
        return new GetReleaseInformation(procedureName: 'Get Release Information', plugin: plugin, )
    }


    GetReleaseInformation flush() {
        this.flushParams()
        return this
    }

    //Generated
    
    GetReleaseInformation config(String config) {
        this.addParam('config', config)
        return this
    }
    
    GetReleaseInformation releaseId(String releaseId) {
        this.addParam('releaseId', releaseId)
        return this
    }
    
    GetReleaseInformation resultFormat(String resultFormat) {
        this.addParam('resultFormat', resultFormat)
        return this
    }
    
    GetReleaseInformation resultPropertySheet(String resultPropertySheet) {
        this.addParam('resultPropertySheet', resultPropertySheet)
        return this
    }
    

    
}