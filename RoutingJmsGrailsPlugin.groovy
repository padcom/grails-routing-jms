class RoutingJmsGrailsPlugin {
	def version        = '1.2.0'
	def grailsVersion  = '2.0.0 > *'
	def author         = 'Matthias Hryniszak'
	def authorEmail    = 'padcom@gmail.com'
	def title          = 'JMS integration for the grails-routing plugin'
	def description    = 'Provides JMS integration for the grails-routing plugin'
	def documentation  = 'http://grails.org/plugin/routing-jms'

	def doWithSpring = {
		def config = application.config.grails.plugin.routing.jms

		activemq(org.apache.activemq.camel.component.ActiveMQComponent) {
			brokerURL = config.brokerURL ?: 'vm://LocalBroker'
		}
	}
}
