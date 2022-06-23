package com.horbac.xacml.config;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import com.fasterxml.jackson.core.exc.StreamWriteException;
import com.fasterxml.jackson.databind.DatabindException;
import com.google.common.collect.Lists;
import com.horbac.xacml.model.Organization;
import com.horbac.xacml.model.Permission;
import com.horbac.xacml.model.Unit;
import com.horbac.xacml.services.YAMLService;

@Component
public class GenerateAPISListener implements ApplicationListener<ContextRefreshedEvent>{	
	
final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(GenerateAPISListener.class);
@Autowired
protected YAMLService yaml;
@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		System.out.println("Received spring custom event - " + event.getTimestamp());
	    ApplicationContext applicationContext = event.getApplicationContext();
	    List<RequestMappingInfo> apis = new ArrayList<RequestMappingInfo>();
	    
	    RequestMappingHandlerMapping requestMappingHandlerMapping = applicationContext
	        .getBean("requestMappingHandlerMapping", RequestMappingHandlerMapping.class);
	    Map<RequestMappingInfo, HandlerMethod> map = requestMappingHandlerMapping
	        .getHandlerMethods();
	    map.forEach((key, value) -> apis.add(key))   ;
	    
	    try {
			insert(apis);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

private void insert(List<RequestMappingInfo> apis) throws StreamWriteException, DatabindException, IOException {
	Organization org = new Organization();
	org.setName(null);
	org.setVersion("0.0");
	
	Unit unit = new Unit();
	unit.setName("Root");
	unit.setSubunits(null);
	List<Unit> units = new ArrayList<Unit>();
	List<Permission> permissions = new ArrayList<Permission>();

	for (RequestMappingInfo req : apis) {
		List<String> methods = List.copyOf(req.getMethodsCondition().getMethods()).stream().map((RequestMethod item) -> item.name()).collect(Collectors.toList());
		
		String api = String.join(",", List.copyOf(req.getDirectPaths()));
		permissions.add(
				new Permission(api, methods.isEmpty() ? null : methods)
				);
	}
	unit.setPermissions(permissions);
	units.add(unit);
	
	org.setUnits(units);
	yaml.writeYamlFile(org);
}

}
