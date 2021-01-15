package com.cloudbees.pdk.hen.procedures

import groovy.transform.AutoClone
import com.cloudbees.pdk.hen.*

@AutoClone
//generated
class CreateAssignment extends Procedure {

    static CreateAssignment create(Plugin plugin) {
        return new CreateAssignment(procedureName: 'Create Assignment', plugin: plugin, )
    }


    CreateAssignment flush() {
        this.flushParams()
        return this
    }

    //Generated
    
    CreateAssignment owner(String owner) {
        this.addParam('_owner', owner)
        return this
    }
    
    CreateAssignment application(String application) {
        this.addParam('application', application)
        return this
    }
    
    CreateAssignment assignmentPrefix(String assignmentPrefix) {
        this.addParam('assignmentPrefix', assignmentPrefix)
        return this
    }
    
    CreateAssignment config(String config) {
        this.addParam('config', config)
        return this
    }
    
    CreateAssignment defaultPath(String defaultPath) {
        this.addParam('defaultPath', defaultPath)
        return this
    }
    
    CreateAssignment description(String description) {
        this.addParam('description', description)
        return this
    }
    
    CreateAssignment refNumber(String refNumber) {
        this.addParam('refNumber', refNumber)
        return this
    }
    
    CreateAssignment release(String release) {
        this.addParam('release', release)
        return this
    }
    
    CreateAssignment resultFormat(String resultFormat) {
        this.addParam('resultFormat', resultFormat)
        return this
    }
    
    CreateAssignment resultPropertySheet(String resultPropertySheet) {
        this.addParam('resultPropertySheet', resultPropertySheet)
        return this
    }
    
    CreateAssignment stream(String stream) {
        this.addParam('stream', stream)
        return this
    }
    
    CreateAssignment userTag(String userTag) {
        this.addParam('userTag', userTag)
        return this
    }
    

    
}