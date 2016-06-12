package org.nick.applications;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.nick.entities.User;
/**
 * www.tutorialspoint.com/hibernate/hibernate_examples
 * 
 * @author Nicholas
 *
 */
public class DatabaseGenerator {

	private static SessionFactory factory;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try{
			factory = new Configuration().configure().buildSessionFactory();
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		DatabaseGenerator dg = new DatabaseGenerator();
		
		dg.addUser("dad", "test");
	}
	
	public Integer addUser(String userName, String password) {
		Session session = factory.openSession();
		
		Transaction tx = null;
		
		Integer userId = null;
		try {
			tx = session.beginTransaction();
			User user = new User(userName, password);
			userId = (Integer)session.save(user);
			tx.commit();
			
		} catch(HibernateException he) {
			he.printStackTrace();
		} finally {
			session.close();
		}
		return userId;
	}

}
