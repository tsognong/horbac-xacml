package com.horbac.xacml.pep;

import org.ow2.authzforce.core.pdp.api.DecisionRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.horbac.xacml.model.Request;
import com.horbac.xacml.services.PDPEngineService;

import oasis.names.tc.xacml._3_0.core.schema.wd_17.DecisionType;

@RestController
public class PEPController {
	@Autowired
	protected PDPEngineService pdpService;
	
	@GetMapping("/pdp")
	public DecisionType checkRequest(@RequestParam String subjectId, @RequestParam String resourceId,
			@RequestParam  String actionId) {		
		Request req = new Request(subjectId, resourceId, actionId);		
		return pdpService.evaluate(req);
	}

}