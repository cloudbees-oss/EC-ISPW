import groovy.transform.BaseScript
import com.electriccloud.commander.dsl.util.BasePlugin

//noinspection GroovyUnusedAssignment
@BaseScript BasePlugin baseScript

// Variables available for use in DSL code
def pluginName = args.pluginName
def upgradeAction = args.upgradeAction
def otherPluginName = args.otherPluginName

def pluginKey = getProject("/plugins/$pluginName/project").pluginKey
def pluginDir = getProperty("/projects/$pluginName/pluginDir").value

//List of procedure steps to which the plugin configuration credentials need to be attached

// ** steps with attached credentials
def stepsWithAttachedCredentials = [
  [procedureName: 'Create Assignment', stepName: 'create assignment'],
  [procedureName: 'Create Release', stepName: 'create release'],
  [procedureName: 'Deploy Assignment', stepName: 'deploy assignment'],
  [procedureName: 'Deploy Release', stepName: 'deploy release'],
  [procedureName: 'Generate Tasks in Assignment', stepName: 'generate tasks in assignment'],
  [procedureName: 'Get Assignment Information', stepName: 'Get Assignment Information'],
  [procedureName: 'Get Assignment Task Information', stepName: 'Get Assignment Task Information'],
  [procedureName: 'Get Assignment Task List', stepName: 'Get Assignment Task List'],
  [procedureName: 'Get Release Information', stepName: 'get release information'],
  [procedureName: 'Get Set Information', stepName: 'get set information'],
  [procedureName: 'Get Set Task List', stepName: 'get set task list'],
  [procedureName: 'Load Task', stepName: 'Load Task'],
  [procedureName: 'Promote Assignment', stepName: 'promote assignment'],
  [procedureName: 'Promote Release', stepName: 'promote release'],
  [procedureName: 'Regress Release', stepName: 'regress release']
]
// ** end steps with attached credentials

project pluginName, {

	loadPluginProperties(pluginDir, pluginName)
	loadProcedures(pluginDir, pluginKey, pluginName, stepsWithAttachedCredentials)
	//plugin configuration metadata
	property 'ec_config', {
		form = '$[' + "/projects/${pluginName}/procedures/CreateConfiguration/ec_parameterForm]"
		property 'fields', {
			property 'desc', {
				property 'label', value: 'Description'
				property 'order', value: '1'
			}
		}
	}
	// callback events list properties
	property 'ec_callback_flow', value: '[{ "name":"completed", "method":"PUT", "url":"https://$[/server/hostIP]:8443/rest/v1.0/properties/ec_callback_response?flowRuntimeId=$[/myFlowRuntime/id]&value=completed" }, { "name":"failed", "method":"PUT", "url":"https://$[/server/hostIP]:8443/rest/v1.0/properties/ec_callback_response?flowRuntimeId=$[/myFlowRuntime/id]&value=failed" }, { "name":"terminated", "method":"PUT", "url":"https://$[/server/hostIP]:8443/rest/v1.0/properties/ec_callback_response?flowRuntimeId=$[/myFlowRuntime/id]&value=terminated" }]'
	property 'ec_callback_job', value: '[{ "name":"completed", "method":"PUT", "url":"https://$[/server/hostIP]:8443/rest/v1.0/properties/ec_callback_response?jobId=$[/myJob/jobId]&value=completed" }, { "name":"failed", "method":"PUT", "url":"https://$[/server/hostIP]:8443/rest/v1.0/properties/ec_callback_response?jobId=$[/myJob/jobId]&value=failed" }, { "name":"terminated", "method":"PUT", "url":"https://$[/server/hostIP]:8443/rest/v1.0/properties/ec_callback_response?jobId=$[/myJob/jobId]&value=terminated" }]'
	property 'ec_callback_pipeline', value: '[{ "name":"completed", "method":"PUT", "url":"https://$[/server/hostIP]:8443/rest/v1.0/properties/ec_callback_response?flowRuntimeId=$[/myPipelineRuntime/id]&value=completed" }, { "name":"failed", "method":"PUT", "url":"https://$[/server/hostIP]:8443/rest/v1.0/properties/ec_callback_response?flowRuntimeId=$[/myPipelineRuntime/id]&value=failed" }, { "name":"terminated", "method":"PUT", "url":"https://$[/server/hostIP]:8443/rest/v1.0/properties/ec_callback_response?flowRuntimeId=$[/myPipelineRuntime/id]&value=terminated" }]'

}

// Copy existing plugin configurations from the previous
// version to this version. At the same time, also attach
// the credentials to the required plugin procedure steps.
upgrade(upgradeAction, pluginName, otherPluginName, stepsWithAttachedCredentials)
