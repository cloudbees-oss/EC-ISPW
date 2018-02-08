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
  [procedureName: 'Create Assignment', stepName: 'Create assignment'],
  [procedureName: 'Create Release', stepName: 'Create release'],
  [procedureName: 'Deploy Assignment', stepName: 'Deploy assignment'],
  [procedureName: 'Deploy Release', stepName: 'Deploy release'],
  [procedureName: 'Display Task Information', stepName: 'Display task information'],
  [procedureName: 'Generate Tasks in Assignment', stepName: 'Generate tasks in assignment'],
  [procedureName: 'Generate Tasks in Release', stepName: 'Generate tasks in release'],
  [procedureName: 'Get Assignment Information', stepName: 'Get assignment information'],
  [procedureName: 'Get Assignment Task Information', stepName: 'Get assignment task information'],
  [procedureName: 'Get Assignment Task List', stepName: 'Get assignment task list'],
  [procedureName: 'Get Release Information', stepName: 'Get release information'],
  [procedureName: 'Get Release Task Information', stepName: 'Get release task information'],
  [procedureName: 'Get Release Task List', stepName: 'Get release task list'],
  [procedureName: 'Get Set Information', stepName: 'Get set information'],
  [procedureName: 'Get Set Task List', stepName: 'Get set task list'],
  [procedureName: 'Load Task', stepName: 'Load task'],
  [procedureName: 'Promote Assignment', stepName: 'Promote assignment'],
  [procedureName: 'Promote Release', stepName: 'Promote release'],
  [procedureName: 'Regress Release', stepName: 'Regress release']
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
	property 'ec_callback_jobStep', value: '[{ "name":"completed", "method":"PUT", "url":"https://$[/server/hostIP]:8443/rest/v1.0/properties/ec_callback_response?jobStepId=$[/myParent/jobStepId]&value=completed" }, { "name":"failed", "method":"PUT", "url":"https://$[/server/hostIP]:8443/rest/v1.0/properties/ec_callback_response?jobStepId=$[/myParent/jobStepId]&value=failed" }, { "name":"terminated", "method":"PUT", "url":"https://$[/server/hostIP]:8443/rest/v1.0/properties/ec_callback_response?jobStepId=$[/myParent/jobStepId]&value=terminated" }]'
	property 'ec_callback_pipeline', value: '[{ "name":"completed", "method":"PUT", "url":"https://$[/server/hostIP]:8443/rest/v1.0/properties/ec_callback_response?flowRuntimeId=$[/myPipelineRuntime/id]&value=completed" }, { "name":"failed", "method":"PUT", "url":"https://$[/server/hostIP]:8443/rest/v1.0/properties/ec_callback_response?flowRuntimeId=$[/myPipelineRuntime/id]&value=failed" }, { "name":"terminated", "method":"PUT", "url":"https://$[/server/hostIP]:8443/rest/v1.0/properties/ec_callback_response?flowRuntimeId=$[/myPipelineRuntime/id]&value=terminated" }]'
	property 'ec_callback_workflow', value: '[{ "name":"completed", "method":"PUT", "url":"https://$[/server/hostIP]:8443/rest/v1.0/properties/ec_callback_response?workflowId=$[/myWorkflow/workflowId]&value=completed" }, { "name":"failed", "method":"PUT", "url":"https://$[/server/hostIP]:8443/rest/v1.0/properties/ec_callback_response?workflowId=$[/myWorkflow/workflowId]&value=failed" }, { "name":"terminated", "method":"PUT", "url":"https://$[/server/hostIP]:8443/rest/v1.0/properties/ec_callback_response?workflowId=$[/myWorkflow/workflowId]&value=terminated" }]'

}

// Copy existing plugin configurations from the previous
// version to this version. At the same time, also attach
// the credentials to the required plugin procedure steps.
upgrade(upgradeAction, pluginName, otherPluginName, stepsWithAttachedCredentials)
