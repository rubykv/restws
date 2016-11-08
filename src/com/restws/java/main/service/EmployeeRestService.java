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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.restws.java.main.impl.CustomerService;

@Component
@Path("/")
public class EmployeeRestService{
	
	@Autowired
	CustomerService custService;

	@POST
	@Path("/updateCustomerCity")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateCustomerCity(InputStream incomingData) {
		StringBuilder inputBuilder = new StringBuilder();
		boolean result = false;
		try {
			BufferedReader in = new BufferedReader(new InputStreamReader(incomingData));
			String line = null;
			while ((line = in.readLine()) != null) {
				inputBuilder.append(line);
			    result = custService.updateCustomerCity("1");
			}
		} catch (Exception e) {
			System.out.println("Error Parsing: - ");
		}
		System.out.println("Data updated successfully" + result);
 
		return Response.status(200).entity(inputBuilder.toString()).build();
	}
 
	@GET
	@Path("/verify")
	@Produces(MediaType.TEXT_PLAIN)
	public Response verifyRESTService(InputStream incomingData) {
		String result = "WE ARE UP AND RUNNING...HURRAY!!!!";
		return Response.status(200).entity(result).build();
	}
}
