package com.bpm.camunda.task;

import java.util.Collections;

import org.camunda.bpm.client.ExternalTaskClient;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ExternalTask {

	public static void main(String[] args) {
		ExternalTaskClient client = ExternalTaskClient.create()
                .baseUrl("http://localhost:8080/engine-rest")
                .asyncResponseTimeout(10000).build();

        client.subscribe("ex_task_notify")
                .lockDuration(1000)
                .handler((externalTask, externalTaskService) -> {
                    String id = (String) externalTask.getVariable("id");
                    log.info("id is: {}", id);
                    String name = (String) externalTask.getVariable("name");
                    log.info("name is: {}", name);
                    String result = "done";
                    externalTaskService.complete(externalTask, Collections.singletonMap("isQualified", result));
                    log.info("ExternalTask Completed: " + result);
                }).open();

	}

}
