package com.bpm.camunda;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@ComponentScan(value = { "com.bpm.camunda" })
@Slf4j
public class MainSpringApplication implements CommandLineRunner {
	public static void main(String[] args) {
		try {
			SpringApplication.run(MainSpringApplication.class, args);
		} catch (Exception e) {
			log.error("Ignore error: {}", e.getMessage());
		}
	}

	@Override
	public void run(String... args) throws Exception {
		// setup environment items
		this.initConfigurations();
	}

	private void initConfigurations() {
		log.info("init Configurations");
		DefaultProcessEngineProvider.init();
	}
}