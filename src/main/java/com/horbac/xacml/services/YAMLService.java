package com.horbac.xacml.services;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.core.exc.StreamWriteException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
import com.horbac.xacml.model.Employee;
import com.horbac.xacml.model.Organization;

@Service
public class YAMLService {

	public void parseYamlFile() throws StreamReadException, DatabindException, IOException {
		// Loading the YAML file from the /resources folder
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		// File file = new
		// File(classLoader.getResource("src/main/resources/users.yaml").getFile());
		File file = ResourceUtils.getFile("classpath:users.yaml");

		// Instantiating a new ObjectMapper as a YAMLFactory
		ObjectMapper om = new ObjectMapper(new YAMLFactory());

		// Mapping the employee from the YAML file to the Employee class
		Employee employee = om.readValue(file, Employee.class);

		// Printing out the information
		System.out.println("Employee info " + employee.toString());

		// Access the first element of the list and print it as well
		System.out.println("Accessing first element: " + employee.getColleagues().get(0).toString());
	}

	public void loadOrganization() throws StreamReadException, DatabindException, IOException {

		File file = ResourceUtils.getFile("classpath:units.yaml");

		// Instantiating a new ObjectMapper as a YAMLFactory
		ObjectMapper om = new ObjectMapper(new YAMLFactory());

		// Mapping the employee from the YAML file to the Employee class
		Organization org = om.readValue(file, Organization.class);

		// Printing out the information
		System.out.println("Organisation info " + org.toString());

		// Access the first element of the list and print it as well
		System.out.println("Accessing first unit permissions: " + org.getUnits().get(0).getPermissions());
	}

	public void writeYamlFile(Organization sampleOrg) throws StreamWriteException, DatabindException, IOException {
		// ObjectMapper is instantiated just like before
		ObjectMapper om = new ObjectMapper(new YAMLFactory());
		om.writeValue(new File("src/main/resources/org.yaml"), sampleOrg);
	}
}
