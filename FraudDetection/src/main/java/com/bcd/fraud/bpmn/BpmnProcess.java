package com.bcd.fraud.bpmn;

public enum BpmnProcess {

	CARD_FRAUD_DETECTION("diagrams/DetectieFrauda.bpmn", "processFraudDetection"),
	ONBOARDING("diagrams/onboarding.bpmn20.xml", "onboarding");
	
	private String diagramPath;
	private String processKey;

	
	private BpmnProcess(String resourcePath, String processKey) {
		this.diagramPath = resourcePath;
		this.processKey = processKey;
	}

	public String getPathForDiagram() {
		return diagramPath;
	}

	public String getProcessKey() {
		return processKey;
	}
	
	
	
}
