package com.example;

import java.text.ParseException;

import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;

import com.bcd.fraud.bpmn.BpmnProcess;
import com.bcd.fraud.bpmn.ProcessManager;

public class OnboardingRequest {
	
  public static void main(String[] args) throws ParseException {
	  ProcessManager processManager = new ProcessManager();
	  
	  ProcessDefinition onboardingProcessDefinition = processManager.deployProcess(BpmnProcess.Onboarding);
	  ProcessInstance onboardProcessInstance = processManager.startProcessInstance(BpmnProcess.Onboarding);
	  
	  processManager.runProcess(onboardingProcessDefinition, onboardProcessInstance);
	  
  }
}
