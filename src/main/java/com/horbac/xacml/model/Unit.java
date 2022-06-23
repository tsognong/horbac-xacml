package com.horbac.xacml.model;

import java.util.List;
import java.util.stream.Collectors;

public class Unit {
	private String name;
	private List<Permission> permissions;
	private List<Unit> subunits;

	public Unit(String name, List<Permission> permissions, List<Unit> subunits) {
		super();
		this.name = name;
		this.permissions = permissions;
		this.subunits = subunits;
	}

	public Unit() {
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Permission> getPermissions() {
		return permissions;
	}

	public void setPermissions(List<Permission> permissions) {
		this.permissions = permissions;
	}

	public List<Unit> getSubunits() {
		return subunits;
	}

	public void setSubunits(List<Unit> subunits) {
		this.subunits = subunits;
	}

	public String toString() {
		return "[ name:" + name + " units: ["
				+ (subunits != null ? subunits.stream().map((Unit unit) -> unit.toString()).collect(Collectors.toList()) : "") + "]" + "]";
	}

}
