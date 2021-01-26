package com.cloudbees.pdk.hen.procedures

import groovy.transform.AutoClone
import com.cloudbees.pdk.hen.*

@AutoClone
//generated
class DeployRelease extends Procedure {

    static DeployRelease create(Plugin plugin) {
        return new DeployRelease(procedureName: 'Deploy Release', plugin: plugin, credentials: [
            
            'callbackCredential': null,
            
        ])
    }


    DeployRelease flush() {
        this.flushParams()
        return this
    }

    //Generated
    
    DeployRelease changeType(String changeType) {
        this.addParam('changeType', changeType)
        return this
    }
    
    DeployRelease changeType(ChangeTypeOptions changeType) {
        this.addParam('changeType', changeType.toString())
        return this
    }
    
    
    DeployRelease config(String config) {
        this.addParam('config', config)
        return this
    }
    
    
    DeployRelease deployActiveDate(String deployActiveDate) {
        this.addParam('deployActiveDate', deployActiveDate)
        return this
    }
    
    
    DeployRelease deployActiveTime(String deployActiveTime) {
        this.addParam('deployActiveTime', deployActiveTime)
        return this
    }
    
    
    DeployRelease deployImplementationDate(String deployImplementationDate) {
        this.addParam('deployImplementationDate', deployImplementationDate)
        return this
    }
    
    
    DeployRelease deployImplementationTime(String deployImplementationTime) {
        this.addParam('deployImplementationTime', deployImplementationTime)
        return this
    }
    
    
    DeployRelease dpenvlst(String dpenvlst) {
        this.addParam('dpenvlst', dpenvlst)
        return this
    }
    
    
    DeployRelease eventCallbacks(String eventCallbacks) {
        this.addParam('eventCallbacks', eventCallbacks)
        return this
    }
    
    
    DeployRelease executionStatus(String executionStatus) {
        this.addParam('executionStatus', executionStatus)
        return this
    }
    
    DeployRelease executionStatus(ExecutionStatusOptions executionStatus) {
        this.addParam('executionStatus', executionStatus.toString())
        return this
    }
    
    
    DeployRelease httpHeaders(String httpHeaders) {
        this.addParam('httpHeaders', httpHeaders)
        return this
    }
    
    
    DeployRelease level(String level) {
        this.addParam('level', level)
        return this
    }
    
    
    DeployRelease releaseId(String releaseId) {
        this.addParam('releaseId', releaseId)
        return this
    }
    
    
    DeployRelease resultFormat(String resultFormat) {
        this.addParam('resultFormat', resultFormat)
        return this
    }
    
    DeployRelease resultFormat(ResultFormatOptions resultFormat) {
        this.addParam('resultFormat', resultFormat.toString())
        return this
    }
    
    
    DeployRelease resultPropertySheet(String resultPropertySheet) {
        this.addParam('resultPropertySheet', resultPropertySheet)
        return this
    }
    
    
    DeployRelease runtimeConfig(String runtimeConfig) {
        this.addParam('runtimeConfig', runtimeConfig)
        return this
    }
    
    
    DeployRelease system(String system) {
        this.addParam('system', system)
        return this
    }
    
    
    
    DeployRelease callbackCredential(String user, String password) {
        this.addCredential('callbackCredential', user, password)
        return this
    }

    DeployRelease callbackCredentialReference(String path) {
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