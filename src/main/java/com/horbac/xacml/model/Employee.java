package com.horbac.xacml.model;

import java.util.List;

public class Employee {

	public Employee(String name, int wage, String position, List<Employee> colleagues) {
		this.name = name;
		this.wage = wage;
		this.position = position;
		this.colleagues = colleagues;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getWage() {
		return wage;
	}

	public void setWage(int wage) {
		this.wage = wage;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public List<Employee> getColleagues() {
		return colleagues;
	}

	public void setColleagues(List<Employee> colleagues) {
		this.colleagues = colleagues;
	}

	// Without a default constructor, Jackson will throw an exception
	public Employee() {
	}


	private String name;
	private int wage;
	private String position;
	private List<Employee> colleagues;

	// Getters and setters

	@Override
	public String toString() {
		return "\nName: " + name + "\nWage: " + wage + "\nPosition: " + position + "\nColleagues: " + colleagues + "\n";
	}

}