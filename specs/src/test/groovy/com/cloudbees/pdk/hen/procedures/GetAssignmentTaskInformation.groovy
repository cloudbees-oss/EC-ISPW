package com.cloudbees.pdk.hen.procedures

import groovy.transform.AutoClone
import com.cloudbees.pdk.hen.*

@AutoClone
//generated
class GetAssignmentTaskInformation extends Procedure {

    static GetAssignmentTaskInformation create(Plugin plugin) {
        return new GetAssignmentTaskInformation(procedureName: 'Get Assignment Task Information', plugin: plugin, )
    }


    GetAssignmentTaskInformation flush() {
        this.flushParams()
        return this
    }

    //Generated
    
    GetAssignmentTaskInformation assignmentId(String assignmentId) {
        this.addParam('assignmentId', assignmentId)
        return this
    }
    
    GetAssignmentTaskInformation config(String config) {
        this.addParam('config', config)
        return this
    }
    
    GetAssignmentTaskInformation resultFormat(String resultFormat) {
        this.addParam('resultFormat', resultFormat)
        return this
    }
    
    GetAssignmentTaskInformation resultPropertySheet(String resultPropertySheet) {
        this.addParam('resultPropertySheet', resultPropertySheet)
        return this
    }
    
    GetAssignmentTaskInformation taskId(String taskId) {
        this.addParam('taskId', taskId)
        return this
    }
    

    
}