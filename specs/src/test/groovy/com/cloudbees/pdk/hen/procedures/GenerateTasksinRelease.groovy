package com.cloudbees.pdk.hen.procedures

import groovy.transform.AutoClone
import com.cloudbees.pdk.hen.*

@AutoClone
//generated
class GenerateTasksinRelease extends Procedure {

    static GenerateTasksinRelease create(Plugin plugin) {
        return new GenerateTasksinRelease(procedureName: 'Generate Tasks in Release', plugin: plugin, credentials: [
            
            'callbackCredential': null,
            
        ])
    }


    GenerateTasksinRelease flush() {
        this.flushParams()
        return this
    }

    //Generated
    
    GenerateTasksinRelease changeType(String changeType) {
        this.addParam('changeType', changeType)
        return this
    }
    
    GenerateTasksinRelease changeType(ChangeTypeOptions changeType) {
        this.addParam('changeType', changeType.toString())
        return this
    }
    
    
    GenerateTasksinRelease config(String config) {
        this.addParam('config', config)
        return this
    }
    
    
    GenerateTasksinRelease eventCallbacks(String eventCallbacks) {
        this.addParam('eventCallbacks', eventCallbacks)
        return this
    }
    
    
    GenerateTasksinRelease execStat(String execStat) {
        this.addParam('execStat', execStat)
        return this
    }
    
    GenerateTasksinRelease execStat(ExecStatOptions execStat) {
        this.addParam('execStat', execStat.toString())
        return this
    }
    
    
    GenerateTasksinRelease httpHeaders(String httpHeaders) {
        this.addParam('httpHeaders', httpHeaders)
        return this
    }
    
    
    GenerateTasksinRelease level(String level) {
        this.addParam('level', level)
        return this
    }
    
    
    GenerateTasksinRelease releaseId(String releaseId) {
        this.addParam('releaseId', releaseId)
        return this
    }
    
    
    GenerateTasksinRelease resultFormat(String resultFormat) {
        this.addParam('resultFormat', resultFormat)
        return this
    }
    
    GenerateTasksinRelease resultFormat(ResultFormatOptions resultFormat) {
        this.addParam('resultFormat', resultFormat.toString())
        return this
    }
    
    
    GenerateTasksinRelease resultPropertySheet(String resultPropertySheet) {
        this.addParam('resultPropertySheet', resultPropertySheet)
        return this
    }
    
    
    GenerateTasksinRelease runtimeConfig(String runtimeConfig) {
        this.addParam('runtimeConfig', runtimeConfig)
        return this
    }
    
    
    
    GenerateTasksinRelease callbackCredential(String user, String password) {
        this.addCredential('callbackCredential', user, password)
        return this
    }

    GenerateTasksinRelease callbackCredentialReference(String path) {
        this.addCredentialReference('callbackCredential', path)
        return this
    }
    
    
    enum ChangeTypeOptions {
    
    STANDARD_S_("S"),
    
    INCIDENTAL_I_("I"),
    
    EMERGENCY_E_("E")
    
    private String value
    ChangeTypeOptions(String value) {
        this.value = value
    }

    String toString() {
        return this.value
    }
}
    
    enum ExecStatOptions {
    
    IMMEDIATE_I_("I"),
    
    HOLD_H_("H")
    
    private String value
    ExecStatOptions(String value) {
        this.value = value
    }

    String toString() {
        return this.value
    }
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