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
    
}