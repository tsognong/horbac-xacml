package com.horbac.xacml.model;

import java.util.List;
import java.util.stream.Collectors;

public class Organization {
	private String version;
	private String name;
	private List<Unit> units;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Unit> getUnits() {
		return units;
	}

	public void setUnits(List<Unit> units) {
		this.units = units;
	}

	public Organization(String name, List<Unit> units) {
		super();
		this.name = name;
		this.units = units;
	}

	public Organization() {
	}

	public String toString() {
	 return "[ name:" +name + " units: ["+ (units != null? units.stream().map((Unit unit)-> unit.toString()).collect(Collectors.toList()) : "")+"]"+"]";
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}
}
