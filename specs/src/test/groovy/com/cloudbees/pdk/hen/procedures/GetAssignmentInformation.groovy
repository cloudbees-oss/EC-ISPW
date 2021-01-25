package com.cloudbees.pdk.hen.procedures

import groovy.transform.AutoClone
import com.cloudbees.pdk.hen.*

@AutoClone
//generated
class GetAssignmentInformation extends Procedure {

    static GetAssignmentInformation create(Plugin plugin) {
        return new GetAssignmentInformation(procedureName: 'Get Assignment Information', plugin: plugin, )
    }


    GetAssignmentInformation flush() {
        this.flushParams()
        return this
    }

    //Generated
    
    GetAssignmentInformation assignmentId(String assignmentId) {
        this.addParam('assignmentId', assignmentId)
        return this
    }
    
    
    GetAssignmentInformation config(String config) {
        this.addParam('config', config)
        return this
    }
    
    
    GetAssignmentInformation resultFormat(String resultFormat) {
        this.addParam('resultFormat', resultFormat)
        return this
    }
    
    GetAssignmentInformation resultFormat(ResultFormatOptions resultFormat) {
        this.addParam('resultFormat', resultFormat.toString())
        return this
    }
    
    
    GetAssignmentInformation resultPropertySheet(String resultPropertySheet) {
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