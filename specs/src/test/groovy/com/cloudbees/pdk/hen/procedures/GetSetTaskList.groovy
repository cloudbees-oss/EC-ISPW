package com.cloudbees.pdk.hen.procedures

import groovy.transform.AutoClone
import com.cloudbees.pdk.hen.*

@AutoClone
//generated
class GetSetTaskList extends Procedure {

    static GetSetTaskList create(Plugin plugin) {
        return new GetSetTaskList(procedureName: 'Get Set Task List', plugin: plugin, )
    }


    GetSetTaskList flush() {
        this.flushParams()
        return this
    }

    //Generated
    
    GetSetTaskList config(String config) {
        this.addParam('config', config)
        return this
    }
    
    
    GetSetTaskList resultFormat(String resultFormat) {
        this.addParam('resultFormat', resultFormat)
        return this
    }
    
    GetSetTaskList resultFormat(ResultFormatOptions resultFormat) {
        this.addParam('resultFormat', resultFormat.toString())
        return this
    }
    
    
    GetSetTaskList resultPropertySheet(String resultPropertySheet) {
        this.addParam('resultPropertySheet', resultPropertySheet)
        return this
    }
    
    
    GetSetTaskList setId(String setId) {
        this.addParam('setId', setId)
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