def procName = 'Wait For Callback'
def stepName = 'wait for callback'
procedure procName, description: 'Waits for property value to be set', {

    step stepName,
        command: new File(pluginDir, "dsl/procedures/WaitForCallback/steps/wait.pl").text,
        errorHandling: 'failProcedure',
        exclusiveMode: 'none',
        releaseMode: 'none',
        shell: 'ec-perl',
        timeLimitUnits: 'minutes'

}
