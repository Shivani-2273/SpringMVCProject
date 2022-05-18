package com.usermanagement.service;

import java.io.FileNotFoundException;
import java.sql.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.usermanagement.DAO.UserDAO;
import com.usermanagement.model.User;

@Service
public class UserServiceImpl extends GenericServiceImpl<User> implements UserService {

	@Autowired
	private UserDAO userDao;

	@Override
	public void deleteUser(int id) {
		userDao.deleteUser(id);
	}

	@Override
	public boolean checkEmail(String email) {
		boolean flag = userDao.checkEmail(email);
		return flag;
	}

	@Override
	public User displayUser(int id) {
		User userDetails = userDao.displayUser(id);
		return userDetails;
	}

	@Override
	public void generateCSV(Date startDate, Date endDate) throws FileNotFoundException {
		userDao.generateCSV(startDate, endDate);

	}


}
