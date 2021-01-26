package com.cloudbees.pdk.hen.procedures

import groovy.transform.AutoClone
import com.cloudbees.pdk.hen.*

@AutoClone
//generated
class DeployAssignment extends Procedure {

    static DeployAssignment create(Plugin plugin) {
        return new DeployAssignment(procedureName: 'Deploy Assignment', plugin: plugin, credentials: [
            
            'callbackCredential': null,
            
        ])
    }


    DeployAssignment flush() {
        this.flushParams()
        return this
    }

    //Generated
    
    DeployAssignment assignmentId(String assignmentId) {
        this.addParam('assignmentId', assignmentId)
        return this
    }
    
    
    DeployAssignment changeType(String changeType) {
        this.addParam('changeType', changeType)
        return this
    }
    
    DeployAssignment changeType(ChangeTypeOptions changeType) {
        this.addParam('changeType', changeType.toString())
        return this
    }
    
    
    DeployAssignment config(String config) {
        this.addParam('config', config)
        return this
    }
    
    
    DeployAssignment deployActiveDate(String deployActiveDate) {
        this.addParam('deployActiveDate', deployActiveDate)
        return this
    }
    
    
    DeployAssignment deployActiveTime(String deployActiveTime) {
        this.addParam('deployActiveTime', deployActiveTime)
        return this
    }
    
    
    DeployAssignment deployImplementationDate(String deployImplementationDate) {
        this.addParam('deployImplementationDate', deployImplementationDate)
        return this
    }
    
    
    DeployAssignment deployImplementationTime(String deployImplementationTime) {
        this.addParam('deployImplementationTime', deployImplementationTime)
        return this
    }
    
    
    DeployAssignment dpenvlst(String dpenvlst) {
        this.addParam('dpenvlst', dpenvlst)
        return this
    }
    
    
    DeployAssignment eventCallbacks(String eventCallbacks) {
        this.addParam('eventCallbacks', eventCallbacks)
        return this
    }
    
    
    DeployAssignment executionStatus(String executionStatus) {
        this.addParam('executionStatus', executionStatus)
        return this
    }
    
    DeployAssignment executionStatus(ExecutionStatusOptions executionStatus) {
        this.addParam('executionStatus', executionStatus.toString())
        return this
    }
    
    
    DeployAssignment httpHeaders(String httpHeaders) {
        this.addParam('httpHeaders', httpHeaders)
        return this
    }
    
    
    DeployAssignment level(String level) {
        this.addParam('level', level)
        return this
    }
    
    
    DeployAssignment resultFormat(String resultFormat) {
        this.addParam('resultFormat', resultFormat)
        return this
    }
    
    DeployAssignment resultFormat(ResultFormatOptions resultFormat) {
        this.addParam('resultFormat', resultFormat.toString())
        return this
    }
    
    
    DeployAssignment resultPropertySheet(String resultPropertySheet) {
        this.addParam('resultPropertySheet', resultPropertySheet)
        return this
    }
    
    
    DeployAssignment runtimeConfig(String runtimeConfig) {
        this.addParam('runtimeConfig', runtimeConfig)
        return this
    }
    
    
    DeployAssignment system(String system) {
        this.addParam('system', system)
        return this
    }
    
    
    
    DeployAssignment callbackCredential(String user, String password) {
        this.addCredential('callbackCredential', user, password)
        return this
    }

    DeployAssignment callbackCredentialReference(String path) {
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