package com.restws.java.main.dao;

import com.restws.java.main.dto.Employee;

public interface MongoDBDAO {
	
	public boolean updateEmployeeInfo(final Employee emp);

}
