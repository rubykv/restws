package com.restws.java.main.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.restws.java.main.dao.MongoDBDAO;
import com.restws.java.main.dto.Employee;

@Service
public class CustomerServiceImpl implements CustomerService{
	
	@Autowired
	MongoDBDAO mongoDao;

	@Override
	public boolean updateEmployeeInfo(final Employee emp) {
		return mongoDao.updateEmployeeInfo(emp);
	}

}
