package com.cloudbees.pdk.hen.procedures

import groovy.transform.AutoClone
import com.cloudbees.pdk.hen.*

@AutoClone
//generated
class RegressRelease extends Procedure {

    static RegressRelease create(Plugin plugin) {
        return new RegressRelease(procedureName: 'Regress Release', plugin: plugin, credentials: [
            
            'callbackCredential': null,
            
        ])
    }


    RegressRelease flush() {
        this.flushParams()
        return this
    }

    //Generated
    
    RegressRelease autoDeploy(boolean autoDeploy) {
        this.addParam('autoDeploy', autoDeploy)
        return this
    }
    
    
    RegressRelease changeType(String changeType) {
        this.addParam('changeType', changeType)
        return this
    }
    
    RegressRelease changeType(ChangeTypeOptions changeType) {
        this.addParam('changeType', changeType.toString())
        return this
    }
    
    
    RegressRelease config(String config) {
        this.addParam('config', config)
        return this
    }
    
    
    RegressRelease eventCallbacks(String eventCallbacks) {
        this.addParam('eventCallbacks', eventCallbacks)
        return this
    }
    
    
    RegressRelease executionStatus(String executionStatus) {
        this.addParam('executionStatus', executionStatus)
        return this
    }
    
    RegressRelease executionStatus(ExecutionStatusOptions executionStatus) {
        this.addParam('executionStatus', executionStatus.toString())
        return this
    }
    
    
    RegressRelease httpHeaders(String httpHeaders) {
        this.addParam('httpHeaders', httpHeaders)
        return this
    }
    
    
    RegressRelease level(String level) {
        this.addParam('level', level)
        return this
    }
    
    
    RegressRelease releaseId(String releaseId) {
        this.addParam('releaseId', releaseId)
        return this
    }
    
    
    RegressRelease resultFormat(String resultFormat) {
        this.addParam('resultFormat', resultFormat)
        return this
    }
    
    RegressRelease resultFormat(ResultFormatOptions resultFormat) {
        this.addParam('resultFormat', resultFormat.toString())
        return this
    }
    
    
    RegressRelease resultPropertySheet(String resultPropertySheet) {
        this.addParam('resultPropertySheet', resultPropertySheet)
        return this
    }
    
    
    RegressRelease runtimeConfig(String runtimeConfig) {
        this.addParam('runtimeConfig', runtimeConfig)
        return this
    }
    
    
    
    RegressRelease callbackCredential(String user, String password) {
        this.addCredential('callbackCredential', user, password)
        return this
    }

    RegressRelease callbackCredentialReference(String path) {
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
    
    enum ExecutionStatusOptions {
    
    IMMEDIATE_I_("I"),
    
    HOLD_H_("H")
    
    private String value
    ExecutionStatusOptions(String value) {
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