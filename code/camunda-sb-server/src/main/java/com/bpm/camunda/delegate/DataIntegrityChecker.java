package com.bpm.camunda.delegate;

import java.util.Set;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@Component("dataIntegrityChecker")
public class DataIntegrityChecker implements JavaDelegate {

	@Override
	public void execute(DelegateExecution execution) throws Exception {
		//${fullfill}
		//${!fullfill}
		boolean fullfill = this.check(execution);
		log.info("DataIntegrityChecker result is: {}", fullfill);
		execution.setVariable("fullfill", fullfill);
		//
        if (!fullfill) {
        	log.info("Add new error message here");
            execution.setVariable("errorMessage", "Please fill the name and id in your request form!");
        }
	}
	
	private boolean check(DelegateExecution execution) {
        String id = (String) execution.getVariable("id");
        String name = (String) execution.getVariable("name");
        log.info("The user id is: {}", id);
        log.info("The user name is: {}", name);
        Set<String> vlist = execution.getVariableNames();
        log.info("VariableNames is");
        vlist.forEach(log::info);
        Set<String> llist = execution.getVariableNamesLocal();
        log.info("VariableNamesLocal is");
        llist.forEach(log::info);
        return id != null && !id.isEmpty() && name != null && !name.isEmpty();
    }

}
