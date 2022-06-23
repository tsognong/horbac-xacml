package com.horbac.xacml.pep;

import java.io.IOException;

import org.ow2.authzforce.core.pdp.api.DecisionRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.horbac.xacml.model.Request;
import com.horbac.xacml.services.PDPEngineService;
import com.horbac.xacml.services.YAMLService;

import oasis.names.tc.xacml._3_0.core.schema.wd_17.DecisionType;

@RestController
public class PEPController {
	@Autowired
	protected PDPEngineService pdpService;

	@Autowired
	protected YAMLService yamlService;

	@GetMapping("/pdp")
	public DecisionType checkRequest(@RequestParam String subjectId, @RequestParam String resourceId,
			@RequestParam String actionId) throws StreamReadException, DatabindException, IOException {
		Request req = new Request(subjectId, resourceId, actionId);

		yamlService.parseYamlFile();

		//yamlService.writeYamlFile();

		yamlService.loadOrganization();

		return pdpService.evaluate(req);
	}
	

	@PostMapping("/pdp")
	public DecisionType submitRequest(@RequestParam String subjectId, @RequestParam String resourceId,
			@RequestParam String actionId) throws StreamReadException, DatabindException, IOException {
		Request req = new Request(subjectId, resourceId, actionId);

		//yamlService.parseYamlFile();

		//yamlService.writeYamlFile();

		//yamlService.loadOrganization();

		return pdpService.evaluate(req);
	}

}