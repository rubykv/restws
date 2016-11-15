package com.restws.java.main.service;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.restws.java.main.dto.Employee;
import com.restws.java.main.service.impl.CustomerService;

@Component
@Path("/")
public class EmployeeRestService {

	@Autowired
	CustomerService custService;

	@POST
	@Path("/updateEmployeeInfo")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateEmployeeInfo(InputStream incomingData) {
		final StringBuilder inputBuilder = new StringBuilder();
		boolean result = false;
		
		try {
			final BufferedReader in = new BufferedReader(new InputStreamReader(incomingData));
			String line = null;
			while ((line = in.readLine()) != null) {
				inputBuilder.append(line);
			}
			final JSONObject jsonObj = new JSONObject(inputBuilder.toString());
			final String jsonInString = (String) jsonObj.get("empObj");

			final ObjectMapper mapper = new ObjectMapper();
			final Employee employee = mapper.readValue(jsonInString, Employee.class);

			result = custService.updateEmployeeInfo(employee);
		} catch (final Exception exception) {
			System.out.println("Error ooccured inside updateEmployeeInfo():EmployeeRestService " + exception);
		}
		return Response.status(200).entity(inputBuilder.toString()).build();
	}

	@GET
	@Path("/verify")
	@Produces(MediaType.TEXT_PLAIN)
	public Response verifyRESTService(InputStream incomingData) {
		String result = "RestJsonWebService is up and running ";
		return Response.status(200).entity(result).build();
	}
}
