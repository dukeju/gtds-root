package com.brother.gtds.dao;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.brother.gtds.model.User;

@Repository("userDao")
public class UserDao {
	
	@Resource
	private SessionFactory sf;

	public void saveUser(User u) {
		System.out.println("userdao run..");
		sf.getCurrentSession().save(u);
	}
}
