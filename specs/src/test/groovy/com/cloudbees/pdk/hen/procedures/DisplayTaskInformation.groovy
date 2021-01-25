package com.cloudbees.pdk.hen.procedures

import groovy.transform.AutoClone
import com.cloudbees.pdk.hen.*

@AutoClone
//generated
class DisplayTaskInformation extends Procedure {

    static DisplayTaskInformation create(Plugin plugin) {
        return new DisplayTaskInformation(procedureName: 'Display Task Information', plugin: plugin, )
    }


    DisplayTaskInformation flush() {
        this.flushParams()
        return this
    }

    //Generated
    
    DisplayTaskInformation config(String config) {
        this.addParam('config', config)
        return this
    }
    
    
    DisplayTaskInformation containerType(String containerType) {
        this.addParam('containerType', containerType)
        return this
    }
    
    DisplayTaskInformation containerType(ContainerTypeOptions containerType) {
        this.addParam('containerType', containerType.toString())
        return this
    }
    
    
    DisplayTaskInformation resultFormat(String resultFormat) {
        this.addParam('resultFormat', resultFormat)
        return this
    }
    
    DisplayTaskInformation resultFormat(ResultFormatOptions resultFormat) {
        this.addParam('resultFormat', resultFormat.toString())
        return this
    }
    
    
    DisplayTaskInformation resultPropertySheet(String resultPropertySheet) {
        this.addParam('resultPropertySheet', resultPropertySheet)
        return this
    }
    
    
    DisplayTaskInformation setTasksJson(String setTasksJson) {
        this.addParam('setTasksJson', setTasksJson)
        return this
    }
    
    
    
    
    enum ContainerTypeOptions {
    
    ASSIGNMENT("assignment"),
    
    RELEASE("release"),
    
    SET("set")
    
    private String value
    ContainerTypeOptions(String value) {
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