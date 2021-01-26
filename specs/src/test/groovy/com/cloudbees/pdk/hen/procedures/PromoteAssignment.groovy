package com.cloudbees.pdk.hen.procedures

import groovy.transform.AutoClone
import com.cloudbees.pdk.hen.*

@AutoClone
//generated
class PromoteAssignment extends Procedure {

    static PromoteAssignment create(Plugin plugin) {
        return new PromoteAssignment(procedureName: 'Promote Assignment', plugin: plugin, credentials: [
            
            'callbackCredential': null,
            
        ])
    }


    PromoteAssignment flush() {
        this.flushParams()
        return this
    }

    //Generated
    
    PromoteAssignment assignmentId(String assignmentId) {
        this.addParam('assignmentId', assignmentId)
        return this
    }
    
    
    PromoteAssignment autoDeploy(boolean autoDeploy) {
        this.addParam('autoDeploy', autoDeploy)
        return this
    }
    
    
    PromoteAssignment changeType(String changeType) {
        this.addParam('changeType', changeType)
        return this
    }
    
    PromoteAssignment changeType(ChangeTypeOptions changeType) {
        this.addParam('changeType', changeType.toString())
        return this
    }
    
    
    PromoteAssignment config(String config) {
        this.addParam('config', config)
        return this
    }
    
    
    PromoteAssignment eventCallbacks(String eventCallbacks) {
        this.addParam('eventCallbacks', eventCallbacks)
        return this
    }
    
    
    PromoteAssignment executionStatus(String executionStatus) {
        this.addParam('executionStatus', executionStatus)
        return this
    }
    
    PromoteAssignment executionStatus(ExecutionStatusOptions executionStatus) {
        this.addParam('executionStatus', executionStatus.toString())
        return this
    }
    
    
    PromoteAssignment httpHeaders(String httpHeaders) {
        this.addParam('httpHeaders', httpHeaders)
        return this
    }
    
    
    PromoteAssignment level(String level) {
        this.addParam('level', level)
        return this
    }
    
    
    PromoteAssignment resultFormat(String resultFormat) {
        this.addParam('resultFormat', resultFormat)
        return this
    }
    
    PromoteAssignment resultFormat(ResultFormatOptions resultFormat) {
        this.addParam('resultFormat', resultFormat.toString())
        return this
    }
    
    
    PromoteAssignment resultPropertySheet(String resultPropertySheet) {
        this.addParam('resultPropertySheet', resultPropertySheet)
        return this
    }
    
    
    PromoteAssignment runtimeConfig(String runtimeConfig) {
        this.addParam('runtimeConfig', runtimeConfig)
        return this
    }
    
    
    
    PromoteAssignment callbackCredential(String user, String password) {
        this.addCredential('callbackCredential', user, password)
        return this
    }

    PromoteAssignment callbackCredentialReference(String path) {
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