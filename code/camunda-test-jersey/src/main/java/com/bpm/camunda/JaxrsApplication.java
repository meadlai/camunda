package com.bpm.camunda;


import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import org.camunda.bpm.engine.rest.impl.CamundaRestResources;
import org.springframework.stereotype.Component;

@Component
@ApplicationPath("/sample-app/")
public class JaxrsApplication extends Application {
	 @Override
	  public Set<Class<?>> getClasses() {
	    Set<Class<?>> classes = new HashSet<Class<?>>();
	    // add your own classes
	    // add all camunda engine rest resources (or just add those that you actually need).
	    classes.addAll(CamundaRestResources.getResourceClasses());

	    // mandatory
	    classes.addAll(CamundaRestResources.getConfigurationClasses());

	    return classes;
	  }
}
