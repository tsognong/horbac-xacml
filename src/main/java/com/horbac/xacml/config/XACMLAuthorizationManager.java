package com.horbac.xacml.config;

import java.util.function.Supplier;

import org.ow2.authzforce.core.pdp.api.DecisionRequest;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.authorization.AuthorizationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.access.intercept.RequestAuthorizationContext;
import org.springframework.stereotype.Component;

import com.horbac.xacml.model.Request;
import com.horbac.xacml.services.PDPEngineService;

import oasis.names.tc.xacml._3_0.core.schema.wd_17.DecisionType;

@Component
public class XACMLAuthorizationManager implements AuthorizationManager<RequestAuthorizationContext> {

	@Autowired
	protected PDPEngineService pdpService;
	
	@Override
	public AuthorizationDecision check(Supplier<Authentication> authentication, RequestAuthorizationContext object) {
		// TODO Auto-generated method stub
		final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(XACMLAuthorizationManager.class);
		
			//final DecisionRequest request = createRequest(authentication, object);
			final Request req = new Request(authentication.get().getName(), object.getRequest().getRequestURI(), object.getRequest().getMethod());
			LOGGER.debug("XACML authorization result: {}, {}", authentication, object);
			System.out.println("Requête.........: " + "AUTH...." + authentication.get().getPrincipal().toString()+ 
					"  => method:"+object.getRequest().getMethod()+" URI: "+object.getRequest().getRequestURI()+
					" METHOD:"+object.getRequest().getMethod()
					+" PARAMS: "+object.getRequest().getParameter("subjectId")+" URL: "
					+object.getRequest().getRequestURL().toString()
					);
			DecisionType res = pdpService.evaluate(req);
			
			LOGGER.debug("XACML authorization request: {}, result {}", req, new  AuthorizationDecision(res == DecisionType.PERMIT).toString());
			return new  AuthorizationDecision(res == DecisionType.PERMIT);
		
	}

}
