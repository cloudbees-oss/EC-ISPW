package com.cloudbees.pdk.hen.procedures

import groovy.transform.AutoClone
import com.cloudbees.pdk.hen.*

@AutoClone
//generated
class LoadTask extends Procedure {

    static LoadTask create(Plugin plugin) {
        return new LoadTask(procedureName: 'Load Task', plugin: plugin, )
    }


    LoadTask flush() {
        this.flushParams()
        return this
    }

    //Generated
    
    LoadTask action(String action) {
        this.addParam('action', action)
        return this
    }
    
    LoadTask alternateName(String alternateName) {
        this.addParam('alternateName', alternateName)
        return this
    }
    
    LoadTask application(String application) {
        this.addParam('application', application)
        return this
    }
    
    LoadTask assignmentId(String assignmentId) {
        this.addParam('assignmentId', assignmentId)
        return this
    }
    
    LoadTask baseVersion(String baseVersion) {
        this.addParam('baseVersion', baseVersion)
        return this
    }
    
    LoadTask cics(boolean cics) {
        this.addParam('cics', cics)
        return this
    }
    
    LoadTask clazz(String clazz) {
        this.addParam('clazz', clazz)
        return this
    }
    
    LoadTask config(String config) {
        this.addParam('config', config)
        return this
    }
    
    LoadTask container(String container) {
        this.addParam('container', container)
        return this
    }
    
    LoadTask currentLevel(String currentLevel) {
        this.addParam('currentLevel', currentLevel)
        return this
    }
    
    LoadTask dateTime(String dateTime) {
        this.addParam('dateTime', dateTime)
        return this
    }
    
    LoadTask environment(String environment) {
        this.addParam('environment', environment)
        return this
    }
    
    LoadTask extension(String extension) {
        this.addParam('extension', extension)
        return this
    }
    
    LoadTask flags(String flags) {
        this.addParam('flags', flags)
        return this
    }
    
    LoadTask generateSequence(String generateSequence) {
        this.addParam('generateSequence', generateSequence)
        return this
    }
    
    LoadTask ims(boolean ims) {
        this.addParam('ims', ims)
        return this
    }
    
    LoadTask internalVersion(String internalVersion) {
        this.addParam('internalVersion', internalVersion)
        return this
    }
    
    LoadTask level(String level) {
        this.addParam('level', level)
        return this
    }
    
    LoadTask message(String message) {
        this.addParam('message', message)
        return this
    }
    
    LoadTask moduleName(String moduleName) {
        this.addParam('moduleName', moduleName)
        return this
    }
    
    LoadTask moduleType(String moduleType) {
        this.addParam('moduleType', moduleType)
        return this
    }
    
    LoadTask operation(String operation) {
        this.addParam('operation', operation)
        return this
    }
    
    LoadTask option1(String option1) {
        this.addParam('option1', option1)
        return this
    }
    
    LoadTask option2(String option2) {
        this.addParam('option2', option2)
        return this
    }
    
    LoadTask option3(String option3) {
        this.addParam('option3', option3)
        return this
    }
    
    LoadTask option4(String option4) {
        this.addParam('option4', option4)
        return this
    }
    
    LoadTask option5(String option5) {
        this.addParam('option5', option5)
        return this
    }
    
    LoadTask program(boolean program) {
        this.addParam('program', program)
        return this
    }
    
    LoadTask release(String release) {
        this.addParam('release', release)
        return this
    }
    
    LoadTask replaceVersion(String replaceVersion) {
        this.addParam('replaceVersion', replaceVersion)
        return this
    }
    
    LoadTask resultFormat(String resultFormat) {
        this.addParam('resultFormat', resultFormat)
        return this
    }
    
    LoadTask resultPropertySheet(String resultPropertySheet) {
        this.addParam('resultPropertySheet', resultPropertySheet)
        return this
    }
    
    LoadTask set(String set) {
        this.addParam('set', set)
        return this
    }
    
    LoadTask sql(boolean sql) {
        this.addParam('sql', sql)
        return this
    }
    
    LoadTask startingLevel(String startingLevel) {
        this.addParam('startingLevel', startingLevel)
        return this
    }
    
    LoadTask status(String status) {
        this.addParam('status', status)
        return this
    }
    
    LoadTask stream(String stream) {
        this.addParam('stream', stream)
        return this
    }
    
    LoadTask taskId(String taskId) {
        this.addParam('taskId', taskId)
        return this
    }
    
    LoadTask url(String url) {
        this.addParam('url', url)
        return this
    }
    
    LoadTask userId(String userId) {
        this.addParam('userId', userId)
        return this
    }
    
    LoadTask version(String version) {
        this.addParam('version', version)
        return this
    }
    

    
}