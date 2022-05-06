package com.usermanagement.DAO;


import javax.sql.DataSource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.usermanagement.model.User;
import com.usermanagement.utility.HibernateUtil;

@Repository
public class UserDAOImpl implements UserDAO{

//	@Autowired
//    private SessionFactory sessionFactory;
//	
//	@Autowired
//	DataSource datasource;
//	
	@Override
	public void userRegister(User user) {
		Transaction tx=null;
		
		try(Session session=HibernateUtil.getSessionFactory().openSession()){
			tx=session.beginTransaction();
			session.save(user);
			System.out.println("impl object"+user);
			tx.commit();
		}catch(Exception e) {
			
			e.printStackTrace();
		}
	}

}
