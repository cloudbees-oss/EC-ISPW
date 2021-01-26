package com.cloudbees.pdk.hen.procedures

import groovy.transform.AutoClone
import com.cloudbees.pdk.hen.*

@AutoClone
//generated
class ISPWConfig extends Procedure {

    static ISPWConfig create(Plugin plugin) {
        return new ISPWConfig(procedureName: 'CreateConfiguration', plugin: plugin, credentials: [
            
            'credential': null,
            
        ])
    }


    ISPWConfig flush() {
        this.flushParams()
        return this
    }

    //Generated
    
    ISPWConfig config(String config) {
        this.addParam('config', config)
        return this
    }
    
    
    ISPWConfig debugLevel(String debugLevel) {
        this.addParam('debugLevel', debugLevel)
        return this
    }
    
    ISPWConfig debugLevel(DebugLevelOptions debugLevel) {
        this.addParam('debugLevel', debugLevel.toString())
        return this
    }
    
    
    ISPWConfig desc(String desc) {
        this.addParam('desc', desc)
        return this
    }
    
    
    ISPWConfig instance(String instance) {
        this.addParam('instance', instance)
        return this
    }
    
    
    ISPWConfig srid(String srid) {
        this.addParam('srid', srid)
        return this
    }
    
    
    
    ISPWConfig credential(String user, String password) {
        this.addCredential('credential', user, password)
        return this
    }

    ISPWConfig credentialReference(String path) {
        this.addCredentialReference('credential', path)
        return this
    }
    
    
    enum DebugLevelOptions {
    
    INFO("0"),
    
    DEBUG("1"),
    
    TRACE("2")
    
    private String value
    DebugLevelOptions(String value) {
        this.value = value
    }

    String toString() {
        return this.value
    }
}
    
}