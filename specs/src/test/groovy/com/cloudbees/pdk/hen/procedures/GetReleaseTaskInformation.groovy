package com.cloudbees.pdk.hen.procedures

import groovy.transform.AutoClone
import com.cloudbees.pdk.hen.*

@AutoClone
//generated
class GetReleaseTaskInformation extends Procedure {

    static GetReleaseTaskInformation create(Plugin plugin) {
        return new GetReleaseTaskInformation(procedureName: 'Get Release Task Information', plugin: plugin, )
    }


    GetReleaseTaskInformation flush() {
        this.flushParams()
        return this
    }

    //Generated
    
    GetReleaseTaskInformation config(String config) {
        this.addParam('config', config)
        return this
    }
    
    
    GetReleaseTaskInformation releaseId(String releaseId) {
        this.addParam('releaseId', releaseId)
        return this
    }
    
    
    GetReleaseTaskInformation resultFormat(String resultFormat) {
        this.addParam('resultFormat', resultFormat)
        return this
    }
    
    GetReleaseTaskInformation resultFormat(ResultFormatOptions resultFormat) {
        this.addParam('resultFormat', resultFormat.toString())
        return this
    }
    
    
    GetReleaseTaskInformation resultPropertySheet(String resultPropertySheet) {
        this.addParam('resultPropertySheet', resultPropertySheet)
        return this
    }
    
    
    GetReleaseTaskInformation taskId(String taskId) {
        this.addParam('taskId', taskId)
        return this
    }
    
    
    
    
    enum ResultFormatOptions {
    
    JSON("json"),
    
    PROPERTYSHEET("propertySheet")
    
    private String value
    ResultFormatOptions(String value) {
        this.value = value
    }

    String toString() {
        return this.value
    }
}
    
}