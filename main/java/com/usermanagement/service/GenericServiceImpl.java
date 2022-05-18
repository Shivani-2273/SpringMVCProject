package com.usermanagement.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.usermanagement.DAO.GenericDAO;

@Service
public class GenericServiceImpl<T> implements GenericService<T> {

	@Autowired
	GenericDAO<T> genericDao;

	@Override
	public void register(T user) {
		System.out.println("in dao"+user);
		genericDao.register(user);
	}

	@Override
	public List<T> getAllUser(T user) {
		List<T> userList = genericDao.getAllUser(user);
		return userList;
	}

	@Override
	public boolean userLogin(T user) {
		boolean flag = genericDao.userLogin(user);
		return flag;
	}

	@Override
	public void resetPassword(T user) {
		genericDao.resetPassword(user);
	}

	@Override
	public T getProfile(T user) {
		T profileData = genericDao.getProfile(user);
		return profileData;
	}

	@Override
	public void updateUser(T user) {
		genericDao.updateUser(user);

	}

	@Override
	public T displayAdmin(T user) {
		T adminDetails= genericDao.displayAdmin(user);
		return adminDetails;
	}

}
