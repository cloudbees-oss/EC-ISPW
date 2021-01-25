package com.cloudbees.pdk.hen.procedures

import groovy.transform.AutoClone
import com.cloudbees.pdk.hen.*

@AutoClone
//generated
class GenerateTasksinAssignment extends Procedure {

    static GenerateTasksinAssignment create(Plugin plugin) {
        return new GenerateTasksinAssignment(procedureName: 'Generate Tasks in Assignment', plugin: plugin, credentials: [
            
            'callbackCredential': null,
            
        ])
    }


    GenerateTasksinAssignment flush() {
        this.flushParams()
        return this
    }

    //Generated
    
    GenerateTasksinAssignment assignmentId(String assignmentId) {
        this.addParam('assignmentId', assignmentId)
        return this
    }
    
    
    GenerateTasksinAssignment autoDeploy(boolean autoDeploy) {
        this.addParam('autoDeploy', autoDeploy)
        return this
    }
    
    
    GenerateTasksinAssignment changeType(String changeType) {
        this.addParam('changeType', changeType)
        return this
    }
    
    GenerateTasksinAssignment changeType(ChangeTypeOptions changeType) {
        this.addParam('changeType', changeType.toString())
        return this
    }
    
    
    GenerateTasksinAssignment config(String config) {
        this.addParam('config', config)
        return this
    }
    
    
    GenerateTasksinAssignment eventCallbacks(String eventCallbacks) {
        this.addParam('eventCallbacks', eventCallbacks)
        return this
    }
    
    
    GenerateTasksinAssignment execStat(String execStat) {
        this.addParam('execStat', execStat)
        return this
    }
    
    GenerateTasksinAssignment execStat(ExecStatOptions execStat) {
        this.addParam('execStat', execStat.toString())
        return this
    }
    
    
    GenerateTasksinAssignment httpHeaders(String httpHeaders) {
        this.addParam('httpHeaders', httpHeaders)
        return this
    }
    
    
    GenerateTasksinAssignment level(String level) {
        this.addParam('level', level)
        return this
    }
    
    
    GenerateTasksinAssignment resultFormat(String resultFormat) {
        this.addParam('resultFormat', resultFormat)
        return this
    }
    
    GenerateTasksinAssignment resultFormat(ResultFormatOptions resultFormat) {
        this.addParam('resultFormat', resultFormat.toString())
        return this
    }
    
    
    GenerateTasksinAssignment resultPropertySheet(String resultPropertySheet) {
        this.addParam('resultPropertySheet', resultPropertySheet)
        return this
    }
    
    
    GenerateTasksinAssignment runtimeConfig(String runtimeConfig) {
        this.addParam('runtimeConfig', runtimeConfig)
        return this
    }
    
    
    
    GenerateTasksinAssignment callbackCredential(String user, String password) {
        this.addCredential('callbackCredential', user, password)
        return this
    }

    GenerateTasksinAssignment callbackCredentialReference(String path) {
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