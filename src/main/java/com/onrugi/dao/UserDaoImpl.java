package com.onrugi.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserDaoImpl implements UserDao {
	@Autowired
	SessionFactory sessionFactory;

	@Transactional
	public Integer loginUser(String username, String pass) {
		Integer role = null;
		System.out.println(username +" "+ pass);
		Session session = sessionFactory.getCurrentSession();
		try {
			System.out.println("day");
			String getRoleQuery = "SELECT v.role FROM User as v WHERE v.name = :username AND v.password = :pass";
			Query<String> query = session.createQuery(getRoleQuery, String.class).setParameter("username", username)
					.setParameter("pass", pass);
			List<String> results = query.getResultList();
			if (!results.isEmpty())
				role = Integer.parseInt(results.get(0));
			System.out.println("nay");
			return role;
		}catch (Exception e) {
			// TODO: handle exception
		}
		return role;
	}

}
