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
    
}