package com.horbac.xacml.model;

public class Request {
	private String subjectId;
	private String resourceId;
	private String actionId;

	public String getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(String subjectId) {
		this.subjectId = subjectId;
	}

	public String getResourceId() {
		return resourceId;
	}

	public void setResourceId(String resourceId) {
		this.resourceId = resourceId;
	}

	public String getActionId() {
		return actionId;
	}

	public void setActionId(String actionId) {
		this.actionId = actionId;
	}

	public Request(String subjectId, String resourceId, String actionId) {
		this.subjectId = subjectId;
		this.resourceId = resourceId;
		this.actionId = actionId;
	}

	public Request() {
	}
}
