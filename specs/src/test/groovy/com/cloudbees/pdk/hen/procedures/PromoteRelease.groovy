package com.cloudbees.pdk.hen.procedures

import groovy.transform.AutoClone
import com.cloudbees.pdk.hen.*

@AutoClone
//generated
class PromoteRelease extends Procedure {

    static PromoteRelease create(Plugin plugin) {
        return new PromoteRelease(procedureName: 'Promote Release', plugin: plugin, credentials: [
            
            'callbackCredential': null,
            
        ])
    }


    PromoteRelease flush() {
        this.flushParams()
        return this
    }

    //Generated
    
    PromoteRelease autoDeploy(boolean autoDeploy) {
        this.addParam('autoDeploy', autoDeploy)
        return this
    }
    
    
    PromoteRelease changeType(String changeType) {
        this.addParam('changeType', changeType)
        return this
    }
    
    PromoteRelease changeType(ChangeTypeOptions changeType) {
        this.addParam('changeType', changeType.toString())
        return this
    }
    
    
    PromoteRelease config(String config) {
        this.addParam('config', config)
        return this
    }
    
    
    PromoteRelease eventCallbacks(String eventCallbacks) {
        this.addParam('eventCallbacks', eventCallbacks)
        return this
    }
    
    
    PromoteRelease executionStatus(String executionStatus) {
        this.addParam('executionStatus', executionStatus)
        return this
    }
    
    PromoteRelease executionStatus(ExecutionStatusOptions executionStatus) {
        this.addParam('executionStatus', executionStatus.toString())
        return this
    }
    
    
    PromoteRelease httpHeaders(String httpHeaders) {
        this.addParam('httpHeaders', httpHeaders)
        return this
    }
    
    
    PromoteRelease level(String level) {
        this.addParam('level', level)
        return this
    }
    
    
    PromoteRelease releaseId(String releaseId) {
        this.addParam('releaseId', releaseId)
        return this
    }
    
    
    PromoteRelease resultFormat(String resultFormat) {
        this.addParam('resultFormat', resultFormat)
        return this
    }
    
    PromoteRelease resultFormat(ResultFormatOptions resultFormat) {
        this.addParam('resultFormat', resultFormat.toString())
        return this
    }
    
    
    PromoteRelease resultPropertySheet(String resultPropertySheet) {
        this.addParam('resultPropertySheet', resultPropertySheet)
        return this
    }
    
    
    PromoteRelease runtimeConfig(String runtimeConfig) {
        this.addParam('runtimeConfig', runtimeConfig)
        return this
    }
    
    
    
    PromoteRelease callbackCredential(String user, String password) {
        this.addCredential('callbackCredential', user, password)
        return this
    }

    PromoteRelease callbackCredentialReference(String path) {
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