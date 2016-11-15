package com.restws.java.main.client;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;

import org.bson.Document;
import org.json.JSONObject;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.ServerAddress;
import com.mongodb.MongoClientOptions.Builder;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.restws.java.main.dto.Employee;

public class RestServiceClient {
	private static final String SERVICE_URL = "http://localhost:8080/RestJsonWebService/api/updateEmployeeInfo";
	
	public static void main(String[] args) {
		try {
			final MongoClient mongoClient = getMongoClient();
			final MongoCollection<Document> collection = getCollection(mongoClient);
			retrieveDocuments(collection, "Before update: ");

			final Employee emp = new Employee("1", "RubyShiv", "Maple shade", "1234");
			final ObjectMapper mapper = new ObjectMapper();
			final String jsonInString = mapper.writeValueAsString(emp);

			final JSONObject jsonFinalObj = new JSONObject();
			jsonFinalObj.put("empObj", jsonInString);

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

				retrieveDocuments(collection, "After update: ");
				in.close();

			} catch (final Exception exception) {
				System.out.println("\nError while calling Web Service" + exception);
			}
		} catch (final Exception e) {
			System.out.println(e);
		}
	}

	private static MongoClient getMongoClient() {
		final Builder clientBuilder = MongoClientOptions.builder().connectTimeout(9000);
		final MongoClientOptions options = clientBuilder.build();
		final MongoClient mongoClient = new MongoClient(new ServerAddress("localhost", 27017), options);
		return mongoClient;
	}

	private static MongoCollection<Document> getCollection(final MongoClient mongoClient) {
		MongoDatabase db = mongoClient.getDatabase("employeeportal");
		MongoCollection<Document> collection = db.getCollection("employee");
		return collection;
	}

	private static void retrieveDocuments(MongoCollection<Document> collection, String msg) {
		for (Document doc : collection.find()) {
			System.out.println(msg + doc.toJson());
		}
	}
}
