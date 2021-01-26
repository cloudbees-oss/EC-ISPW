package com.cloudbees.pdk.hen.procedures

import groovy.transform.AutoClone
import com.cloudbees.pdk.hen.*

@AutoClone
//generated
class GetSetInformation extends Procedure {

    static GetSetInformation create(Plugin plugin) {
        return new GetSetInformation(procedureName: 'Get Set Information', plugin: plugin, )
    }


    GetSetInformation flush() {
        this.flushParams()
        return this
    }

    //Generated
    
    GetSetInformation config(String config) {
        this.addParam('config', config)
        return this
    }
    
    
    GetSetInformation resultFormat(String resultFormat) {
        this.addParam('resultFormat', resultFormat)
        return this
    }
    
    GetSetInformation resultFormat(ResultFormatOptions resultFormat) {
        this.addParam('resultFormat', resultFormat.toString())
        return this
    }
    
    
    GetSetInformation resultPropertySheet(String resultPropertySheet) {
        this.addParam('resultPropertySheet', resultPropertySheet)
        return this
    }
    
    
    GetSetInformation setId(String setId) {
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