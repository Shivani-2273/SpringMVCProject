package com.usermanagement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.usermanagement.DAO.UserDAO;
import com.usermanagement.model.User;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserDAO userDao;
	
	
	@Override
	public void userRegister(User user) {
		userDao.userRegister(user);
	}


	

}
