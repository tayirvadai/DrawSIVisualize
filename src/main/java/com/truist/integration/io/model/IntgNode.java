package com.truist.integration.io.model;

import java.util.Map;

public class IntgNode {
	
	private Integer nodeId;
	public String componentType;
	public String integrationPatternType;
	public String integrationPatternCategory;
	public Map<String,Object> properties;
	public String sendTimers;
	public String name;
	public Integer getNodeId() {
		return nodeId;
	}
	public void setNodeId(Integer nodeId) {
		this.nodeId = nodeId;
	}
	public String getComponentType() {
		return componentType;
	}
	public void setComponentType(String componentType) {
		this.componentType = componentType;
	}
	public String getIntegrationPatternType() {
		return integrationPatternType;
	}
	public void setIntegrationPatternType(String integrationPatternType) {
		this.integrationPatternType = integrationPatternType;
	}
	public String getIntegrationPatternCategory() {
		return integrationPatternCategory;
	}
	public void setIntegrationPatternCategory(String integrationPatternCategory) {
		this.integrationPatternCategory = integrationPatternCategory;
	}
	
	public String getSendTimers() {
		return sendTimers;
	}
	public void setSendTimers(String sendTimers) {
		this.sendTimers = sendTimers;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Map<String, Object> getProperties() {
		return properties;
	}
	public void setProperties(Map<String, Object> properties) {
		this.properties = properties;
	}
	
	

}
