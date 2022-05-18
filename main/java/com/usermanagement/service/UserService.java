package com.usermanagement.service;

import java.io.FileNotFoundException;
import java.sql.Date;
import com.usermanagement.model.User;

public interface UserService extends GenericService<User> {

	/**
	 * This method will check userEmail with existing email
	 * 
	 * @param email
	 * @return
	 */
	boolean checkEmail(String email);

	/**
	 * This method will delete user details from database
	 * 
	 * @param id
	 */
	void deleteUser(int id);

	/**
	 * This method will display user details
	 * 
	 * @param id
	 * @return
	 */
	User displayUser(int id);

	/**
	 * This method will generate CSV file of logged in user
	 * 
	 * @param startDate
	 * @param endDate
	 * @throws FileNotFoundException
	 */
	void generateCSV(Date startDate, Date endDate) throws FileNotFoundException;
}
