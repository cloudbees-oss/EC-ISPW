package com.cloudbees.pdk.hen

import com.cloudbees.pdk.hen.procedures.*
import com.cloudbees.pdk.hen.Plugin

import static com.cloudbees.pdk.hen.Utils.env

class ISPW extends Plugin {

    static ISPW create() {
        String resName = env('ISPW_RESOURCE')
        ISPW plugin = new ISPW(name: 'EC-ISPW', defaultResource: resName)
        String resPort = env('ISPW_RESOURCE_PORT')
        ServerHandler.getInstance().setupResource( resName, resName, resPort as int)
        plugin.configure(plugin.config)
        return plugin
    }

    static String app() {
        return 'PLAY'
    }

    static String stream() {
        return 'PLAY'
    }

    static String assignment() {
        return 'PLAY000004'
    }

    static ISPW createWithoutConfig() {
        ISPW plugin = new ISPW(name: 'EC-ISPW')
        return plugin
    }

    static String password() {
        return env('COMMANDER_PASSWORD')
    }

    //user-defined after boilerplate was generated, default parameters setup
    ISPWConfig config = ISPWConfig
        .create(this)
        .srid('ISPW')
        .instance(env('ISPW_INSTANCE'))
        .debugLevel('10')
        .credential('', env("ISPW_TOKEN"))




    CreateAssignment createAssignment = CreateAssignment.create(this)

    CreateRelease createRelease = CreateRelease.create(this)

    DeployAssignment deployAssignment = DeployAssignment.create(this)

    DeployRelease deployRelease = DeployRelease.create(this)

    DisplayTaskInformation displayTaskInformation = DisplayTaskInformation.create(this)

    GenerateTasksinAssignment generateTasksinAssignment = GenerateTasksinAssignment.create(this)

    GenerateTasksinRelease generateTasksinRelease = GenerateTasksinRelease.create(this)

    GetAssignmentInformation getAssignmentInformation = GetAssignmentInformation.create(this)

    GetAssignmentTaskInformation getAssignmentTaskInformation = GetAssignmentTaskInformation.create(this)

    GetAssignmentTaskList getAssignmentTaskList = GetAssignmentTaskList.create(this)

    GetReleaseInformation getReleaseInformation = GetReleaseInformation.create(this)

    GetReleaseTaskInformation getReleaseTaskInformation = GetReleaseTaskInformation.create(this)

    GetReleaseTaskList getReleaseTaskList = GetReleaseTaskList.create(this)

    GetSetInformation getSetInformation = GetSetInformation.create(this)

    GetSetTaskList getSetTaskList = GetSetTaskList.create(this)

    LoadTask loadTask = LoadTask.create(this)

    PromoteAssignment promoteAssignment = PromoteAssignment.create(this)

    PromoteRelease promoteRelease = PromoteRelease.create(this)

    RegressRelease regressRelease = RegressRelease.create(this)

}