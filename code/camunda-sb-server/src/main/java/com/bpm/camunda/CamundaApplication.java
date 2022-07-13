package com.bpm.camunda;

import org.camunda.bpm.spring.boot.starter.annotation.EnableProcessApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableProcessApplication("digital")
public class CamundaApplication {

	
	/**
	 * <li> web-app: http://localhost:8080/
	 * <li> rest-api: http://localhost:8080/engine-rest/task/
	 * 
	 * @param args
	 */
  public static void main(String... args) {
    SpringApplication.run(CamundaApplication.class, args);
  }
}
