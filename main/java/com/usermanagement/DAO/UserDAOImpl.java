package com.usermanagement.DAO;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Writer;
import java.nio.charset.Charset;
import java.sql.Date;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.usermanagement.model.Address;
import com.usermanagement.model.User;

@Repository
public class UserDAOImpl implements UserDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void deleteUser(int id) {
		Transaction tx = null;
		try (Session session = sessionFactory.openSession()) {
			tx = session.beginTransaction();
			User user = session.get(User.class, id);
			if (user != null) {
				session.delete(user);
				tx.commit();
			}
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
		}

	}

	@SuppressWarnings("rawtypes")
	@Override
	public boolean checkEmail(String email) {
		try (Session session = sessionFactory.openSession()) {
			session.beginTransaction();
			Query query = session.createQuery("SELECT u.email FROM User u WHERE u.email =:email");
			query.setParameter("email", email);
			List listOfEmail = query.list();
			if (!listOfEmail.isEmpty()) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public User displayUser(int id) {
		List<User> userData = null;
		User user=null;
		try (Session session = sessionFactory.openSession()) {
			Query query = session.createQuery("FROM User u WHERE u.userId =:id");
			query.setParameter("id", id);
			userData = query.list();
			user=userData.get(0);
		} catch (Exception e) {
			e.printStackTrace();
		}
		//return userData;
		return user;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public void generateCSV(Date startDate, Date endDate) throws FileNotFoundException {
		//String desktopPath = System.getProperty("user.home") + "/Desktop";
		//System.out.println("\npath\n"+desktopPath);
		//desktopPath.replace("\\", "/");
		File file = new File("/home/root-tr-013/Desktop/CSV_FILE/Login.csv");
		Writer w = new OutputStreamWriter(new FileOutputStream(file), Charset.forName("UTF-8").newEncoder());
		PrintWriter fw = new PrintWriter(w);
		fw.append("First Name");
		fw.append(',');
		fw.append("Last Name");
		fw.append(',');
		fw.append("Date/Time");
		fw.append('\n');
		try (Session session = sessionFactory.openSession()) {
			session.beginTransaction();
			Query user = session.createQuery(
					"FROM User u WHERE u.isAdmin=0 AND DATE(u.updatedAt) BETWEEN  :startDate and  :endDate");
			user.setParameter("startDate", startDate);
			user.setParameter("endDate", endDate);
			List<User> listUser = user.list();
			for (User userData : listUser) {
				fw.append(userData.getFirstName());
				fw.append(',');
				fw.append(userData.getLastName());
				fw.append(',');
				LocalDateTime date = userData.getUpdatedAt();
				fw.append(date.toString());
				fw.append('\n');

			}
			fw.flush();
			fw.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public List<User> getAllUser(User user) {
		Transaction tx = null;
		List<User> listOfUser = null;
		try (Session session = sessionFactory.openSession()) {
			tx = session.beginTransaction();
			Query query = session.createQuery("FROM User u WHERE u.isAdmin=0");
			listOfUser = query.list();

		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
		}
		return listOfUser;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public boolean userLogin(User user) {
		try (Session session = sessionFactory.openSession()) {
			session.beginTransaction();
			Query query = session.createQuery("FROM User u WHERE u.email = :email and u.password = :password");
			query.setParameter("email", user.getEmail());
			query.setParameter("password", user.getPassword());
			List<User> userData = query.list();
			user.setAdmin(userData.get(0).isAdmin());
			if (userData.isEmpty()) {
				return false;
			} else {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public void resetPassword(User user) {
		Transaction tx = null;
		try (Session session = sessionFactory.openSession()) {
			tx = session.beginTransaction();
			Query query = session.createQuery("UPDATE User u SET u.password= :password WHERE u.email =:email");
			query.setParameter("password", user.getPassword());
			query.setParameter("email", user.getEmail());
			query.executeUpdate();
			tx.commit();
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
		}

	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public User getProfile(User user) {
		List<User> UserData = null;
		try (Session session = sessionFactory.openSession()) {
			session.beginTransaction();
			Query query = session.createQuery("FROM User u where u.email =:email AND u.password =:password");
			query.setParameter("email", user.getEmail());
			query.setParameter("password", user.getPassword());
			UserData = query.list();
			user=UserData.get(0);

		}
		return user;
	}

	@SuppressWarnings({"unchecked", "rawtypes" })
	@Override
	public void updateUser(User user) {
		Transaction tx = null;
		try (Session session = sessionFactory.openSession()) {
			tx=session.beginTransaction();
			//get address id from user object
			List<Integer> addressIdList = new ArrayList<Integer>();
			for (int counter = 0; counter < user.getAddress().size(); counter++) {
				addressIdList.add(user.getAddress().get(counter).getAddressId());
			}
			// get address id from database
			List<Integer> databaseIdList = getAddressId(session, user.getUserId());
			for (int counter = 0; counter < databaseIdList.size(); counter++) {
				if (!addressIdList.contains(databaseIdList.get(counter))) {
					int deleteId = databaseIdList.get(counter);
					Query query = session.createQuery("DELETE FROM Address WHERE addressId = :addressId");
					query.setParameter("addressId", deleteId);
					query.executeUpdate();
				}
			}
			for (Address address : user.getAddress()) {
				address.setUser(user);
			}
			session.update(user);
			//session.saveOrUpdate(user);
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@SuppressWarnings("rawtypes")
	private List getAddressId(Session session, int userId) {
		Query query = session.createQuery("SELECT addressId FROM Address WHERE  user.userId = :userId");
		query.setParameter("userId", userId);
		List list = query.list();
		return list;
	}

	@Override
	public void register(User user) {
		Transaction tx = null;
		try (Session session = sessionFactory.openSession()) {
			tx = session.beginTransaction();
			for (Address address : user.getAddress()) {
				address.setUser(user);
			}
			session.save(user);
			tx.commit();
		} catch (Exception e) {

			e.printStackTrace();
		}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public User displayAdmin(User user) {
		List<User> userData = null;
		try (Session session = sessionFactory.openSession()) {
			Query query = session.createQuery("FROM User u WHERE u.userId =1");
			userData = query.list();
			user=userData.get(0);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return user;
	}
}
