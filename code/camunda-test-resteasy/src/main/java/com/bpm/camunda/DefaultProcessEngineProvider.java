package com.bpm.camunda;

import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.ProcessEngineConfiguration;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DefaultProcessEngineProvider {

	private DefaultProcessEngineProvider() {
		log.info("DefaultProcessEngineProvider");
	}

	/**
	 * http://localhost:8080/engine/mead/task
	 */
	protected static void init() {
//		ProcessEngineProvider pep = new ContainerManagedProcessEngineProvider();
		ProcessEngineConfiguration.createStandaloneProcessEngineConfiguration();
		ProcessEngine pe1 = ProcessEngineConfiguration.createStandaloneInMemProcessEngineConfiguration()
				.setDatabaseSchemaUpdate(ProcessEngineConfiguration.DB_SCHEMA_UPDATE_FALSE)
				.setJdbcUrl("jdbc:h2:mem:my-own-db;DB_CLOSE_DELAY=1000").setJobExecutorActivate(true)
				.setDatabaseSchemaUpdate("true").buildProcessEngine();
		
		ProcessEngine pe2 = ProcessEngineConfiguration.createStandaloneInMemProcessEngineConfiguration()
		.setDatabaseSchemaUpdate(ProcessEngineConfiguration.DB_SCHEMA_UPDATE_FALSE)
		.setJdbcUrl("jdbc:h2:mem:mead-db;DB_CLOSE_DELAY=1000").setJobExecutorActivate(true)
		.setDatabaseSchemaUpdate("true").setProcessEngineName("mead").buildProcessEngine();
//		ProcessEngines.registerProcessEngine(processEngine);
		log.info("ProcessEngine created: {}", pe1.getName());
		
		log.info("ProcessEngine created: {}", pe2.getName());
	}

}
