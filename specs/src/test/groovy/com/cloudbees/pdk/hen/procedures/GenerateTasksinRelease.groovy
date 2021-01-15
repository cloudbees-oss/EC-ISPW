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
    
    GenerateTasksinRelease changeTypeTODOIsthisignored(String changeTypeTODOIsthisignored) {
        this.addParam('changeType #### TODO Is this ignored?', changeTypeTODOIsthisignored)
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
    
    GenerateTasksinRelease execStatTODOIsthisignored(String execStatTODOIsthisignored) {
        this.addParam('execStat #### TODO Is this ignored?', execStatTODOIsthisignored)
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
    
}