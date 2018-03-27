import com.electriccloud.spec.PluginSpockTestSupport

class ECISPWPluginHelper extends PluginSpockTestSupport {
    def createConfiguration(configName, props = [:]) {
        def username = System.getenv('ISPW_USERNAME') ?: '?'
        def password = System.getenv('ISPW_PASSWORD') ?: '225db5d0-8aea-42a2-a216-c23e42c39c30'
        def instance = System.getenv('ISPW_URL') ?: 'http://192.168.96.182:2020'
        def srid = System.getenv('ISPW_SRID') ?: 'ISPW'

        if (System.getenv('RECREATE_CONFIG')) {
            props.recreate = true
        }

        println("====");
        println(System.getenv('AGENT_IP'));
        println("====");
        

        createPluginConfiguration(
                'EC-ISPW',
                configName,
                [instance: instance, srid: srid],
                username,
                password,
                props
        )
        
        dslFile 'dsl/CreateAgent.dsl', []
    }
}
