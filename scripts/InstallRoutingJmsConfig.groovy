if(grails.util.GrailsUtil.grailsVersion.startsWith("1.0")) {
    includeTargets << new File ( "${grailsHome}/scripts/Init.groovy" )
    includeTargets << new File( "${grailsHome}/scripts/CreateIntegrationTest.groovy")
} else {
    includeTargets << grailsScript("_GrailsInit")
    includeTargets << grailsScript("_GrailsCreateArtifacts")
}

target('default': "Installs RoutingJms config in the /grails-app/conf/ directory") {
    installRoutingJmsConfig()
}

target(installRoutingJmsConfig: "The implementation task") {
    depends(checkVersion)
    def configFile = "${basedir}/grails-app/conf/RoutingJmsConfig.groovy"
    if(!(configFile as File).exists() || confirmInput("RoutingJms config file already exists in your project. Overwrite it?")) {
        Ant.copy(
                file:"${routingJmsPluginDir}/grails-app/conf/RoutingJmsConfig.groovy",
                tofile:configFile,
                overwrite: true
        )
        event("CreatedFile", [configFile])
        event("StatusFinal", ["RoutingJms configuration file was installed into /grails-app/conf/RoutingJmsConfig.groovy"])
    }
}

confirmInput = {String message ->
    Ant.input(message: message, addproperty: "confirm.message", validargs: "y,n")
    Ant.antProject.properties."confirm.message" == "y"
}
