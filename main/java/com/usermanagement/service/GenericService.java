package com.usermanagement.service;

import java.util.List;

public interface GenericService<T> {

	/**
	 * This method will store data of user into database
	 * 
	 * @param user
	 */
	void register(T user);

	/**
	 * This method will get all data of user from database
	 * 
	 * @param user
	 * @return
	 */
	List<T> getAllUser(T user);

	/**
	 * This method will display details of admin
	 * 
	 * @param user
	 * @return
	 */
	T displayAdmin(T user);

	/**
	 * This method compare login details of user
	 * 
	 * @param user
	 * @return
	 */
	boolean userLogin(T user);

	/**
	 * This method will update password of user
	 * 
	 * @param user
	 */
	void resetPassword(T user);

	/**
	 * This method will display profile of user
	 * 
	 * @param user
	 * @return
	 */
	T getProfile(T user);

	/**
	 * This method will update details of user
	 * 
	 * @param user
	 */
	void updateUser(T user);

}
