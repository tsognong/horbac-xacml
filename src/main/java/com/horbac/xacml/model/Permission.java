package com.horbac.xacml.model;

import java.util.List;

public class Permission {
	private String api;	
	private List<String> methods;
	public Permission(String api, List<String> methods) {
		super();
		this.api = api;
		this.methods = methods;
	}
	
	public Permission() {
	}
	
	
	public String getApi() {
		return api;
	}

	public void setApi(String api) {
		this.api = api;
	}

	public List<String> getMethods() {
		return methods;
	}

	public void setMethods(List<String> methods) {
		this.methods = methods;
	}

	@Override
	public String toString() {
		return "Permission [api=" + api + ", methods=" + methods + "]";
	}

	
	

}
