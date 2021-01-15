package com.cloudbees.pdk.hen.procedures

import groovy.transform.AutoClone
import com.cloudbees.pdk.hen.*

@AutoClone
//generated
class CreateRelease extends Procedure {

    static CreateRelease create(Plugin plugin) {
        return new CreateRelease(procedureName: 'Create Release', plugin: plugin, )
    }


    CreateRelease flush() {
        this.flushParams()
        return this
    }

    //Generated
    
    CreateRelease owner(String owner) {
        this.addParam('_owner', owner)
        return this
    }
    
    CreateRelease application(String application) {
        this.addParam('application', application)
        return this
    }
    
    CreateRelease config(String config) {
        this.addParam('config', config)
        return this
    }
    
    CreateRelease description(String description) {
        this.addParam('description', description)
        return this
    }
    
    CreateRelease message(String message) {
        this.addParam('message', message)
        return this
    }
    
    CreateRelease releaseId(String releaseId) {
        this.addParam('releaseId', releaseId)
        return this
    }
    
    CreateRelease resultFormat(String resultFormat) {
        this.addParam('resultFormat', resultFormat)
        return this
    }
    
    CreateRelease resultPropertySheet(String resultPropertySheet) {
        this.addParam('resultPropertySheet', resultPropertySheet)
        return this
    }
    
    CreateRelease stream(String stream) {
        this.addParam('stream', stream)
        return this
    }
    
    CreateRelease userTag(String userTag) {
        this.addParam('userTag', userTag)
        return this
    }
    
    CreateRelease workRefNumber(String workRefNumber) {
        this.addParam('workRefNumber', workRefNumber)
        return this
    }
    

    
}