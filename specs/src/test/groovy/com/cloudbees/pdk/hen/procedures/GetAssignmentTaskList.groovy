package com.cloudbees.pdk.hen.procedures

import groovy.transform.AutoClone
import com.cloudbees.pdk.hen.*

@AutoClone
//generated
class GetAssignmentTaskList extends Procedure {

    static GetAssignmentTaskList create(Plugin plugin) {
        return new GetAssignmentTaskList(procedureName: 'Get Assignment Task List', plugin: plugin, )
    }


    GetAssignmentTaskList flush() {
        this.flushParams()
        return this
    }

    //Generated
    
    GetAssignmentTaskList assignmentId(String assignmentId) {
        this.addParam('assignmentId', assignmentId)
        return this
    }
    
    
    GetAssignmentTaskList config(String config) {
        this.addParam('config', config)
        return this
    }
    
    
    GetAssignmentTaskList level(String level) {
        this.addParam('level', level)
        return this
    }
    
    
    GetAssignmentTaskList resultFormat(String resultFormat) {
        this.addParam('resultFormat', resultFormat)
        return this
    }
    
    GetAssignmentTaskList resultFormat(ResultFormatOptions resultFormat) {
        this.addParam('resultFormat', resultFormat.toString())
        return this
    }
    
    
    GetAssignmentTaskList resultPropertySheet(String resultPropertySheet) {
        this.addParam('resultPropertySheet', resultPropertySheet)
        return this
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