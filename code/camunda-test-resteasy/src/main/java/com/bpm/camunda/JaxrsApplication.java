package com.bpm.camunda;


import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import org.camunda.bpm.engine.rest.impl.CamundaRestResources;
import org.camunda.bpm.engine.rest.impl.DefaultProcessEngineRestServiceImpl;
import org.camunda.bpm.engine.rest.impl.NamedProcessEngineRestServiceImpl;
import org.springframework.stereotype.Component;

@Component
@ApplicationPath("/")
public class JaxrsApplication extends Application {
	 @Override
	  public Set<Class<?>> getClasses() {
	    Set<Class<?>> classes = new HashSet<Class<?>>();
	    // add your own classes
	    // add all camunda engine rest resources (or just add those that you actually need).
	    classes.add(NamedProcessEngineRestServiceImpl.class);

	    // mandatory
	    classes.addAll(CamundaRestResources.getConfigurationClasses());

	    return classes;
	  }
}
