import org.codehaus.groovy.grails.commons.ConfigurationHolder

class RoutingJmsGrailsPlugin {
    def version = "1.1.4"
    def grailsVersion = "2.0.0 > *"
    // the other plugins this plugin depends on
    def dependsOn = [ routing: '1.1.4' ]
    // resources that are excluded from plugin packaging
    def pluginExcludes = [
            "grails-app/views/error.gsp"
    ]

    // TODO Fill in these fields
    def author = "Matthias Hryniszak"
    def authorEmail = "padcom@gmail.com"
    def title = "JMS integration for the grails-routing plugin"
    def description = '''\\
JMS integration for the grails-routing plugin
'''

    // URL to the plugin's documentation
    def documentation = "http://grails.org/plugin/routing-jms"

    def doWithWebDescriptor = { xml ->
        // TODO Implement additions to web.xml (optional), this event occurs before 
    }

    def doWithSpring = {
        def config = loadConfig().grails.plugin.routing.jms

        activemq(org.apache.activemq.camel.component.ActiveMQComponent) {
            brokerURL = config.brokerURL
        }
    }

    def doWithDynamicMethods = { ctx ->
        // TODO Implement registering dynamic methods to classes (optional)
    }

    def doWithApplicationContext = { applicationContext ->
        // TODO Implement post initialization spring config (optional)
    }

    def onChange = { event ->
        // TODO Implement code that is executed when any artefact that this plugin is
        // watching is modified and reloaded. The event contains: event.source,
        // event.application, event.manager, event.ctx, and event.plugin.
    }

    def onConfigChange = { event ->
        // TODO Implement code that is executed when the project configuration changes.
        // The event is the same as for 'onChange'.
    }

    private loadConfig() {
        def classLoader = new GroovyClassLoader(getClass().getClassLoader())
        def config
        try {
            config = new ConfigSlurper().parse(classLoader.loadClass('RoutingJmsConfig'))
        } catch (e) {
        }
    }
}
