package com.restws.java.main.client;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;

import org.json.JSONObject;

public class RestServiceClient {
	private static final String SERVICE_URL = "http://localhost:8080/RestJsonWebService/api/updateCustomerCity";

	public static void main(String[] args) {
		
		try {
			final JSONObject jsonInnerObj = new JSONObject();
			jsonInnerObj.put("id", "1");
			jsonInnerObj.put("city", "Maple shade");

			final JSONObject jsonFinalObj = new JSONObject();
			jsonFinalObj.put("customer", jsonInnerObj);
			
			try {
				URL url = new URL(SERVICE_URL);
				URLConnection connection = url.openConnection();
				connection.setDoOutput(true);
				connection.setRequestProperty("Content-Type", "application/json");
				connection.setConnectTimeout(8000);
				connection.setReadTimeout(8000);
				
				OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream());
				out.write(jsonFinalObj.toString());
				out.close();

				BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
				while (null != in.readLine()) {
					System.out.println("Web Service Invoked Successfully..");
				}
				in.close();

			} catch (final Exception e) {
				System.out.println("\nError while calling Web Service"+e);
			}
		} catch (final Exception e) {
			System.out.println(e);
		}
	}
}
