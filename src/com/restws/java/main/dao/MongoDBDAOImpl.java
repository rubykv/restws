package com.restws.java.main.dao;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoClientOptions.Builder;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.UpdateResult;
import com.restws.java.main.dto.Employee;

public class MongoDBDAOImpl implements MongoDBDAO {

	@Override
	public boolean updateEmployeeInfo(final Employee emp) {

		final Builder clientBuilder = MongoClientOptions.builder().connectTimeout(9000);
		final MongoClientOptions options = clientBuilder.build();
		final MongoClient mongoClient = new MongoClient(new ServerAddress("localhost", 27017), options);
		boolean result = false;

		try {
			final MongoDatabase db = mongoClient.getDatabase("employeeportal");
			final MongoCollection<Document> collection = db.getCollection("employee");

			if (null != emp && emp.getId() != null) {
				final UpdateResult updateMany = collection.updateMany(com.mongodb.client.model.Filters.eq("id", emp.getId()),
						new Document("$set", createEmployeeDocument(emp)));
				if (null != updateMany && updateMany.getModifiedCount() > 0) result = true;
			}
		} catch (final Exception exception) {
			System.out.println("Exception thrown inside updateEmployeeInfo:MongoDBDAOImpl" + exception);
		} finally {
			mongoClient.close();
		}

		return result;
	}

	public Document createEmployeeDocument(Employee emp) {
		Document documentObj = new Document();
		documentObj.put("id", emp.getId());
		documentObj.put("name", emp.getName());
		documentObj.put("city", emp.getCity());
		documentObj.put("phoneNumber", emp.getPhoneNumber());
		return documentObj;
	}

}
