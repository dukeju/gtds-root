package com.brother.gtds.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.brother.gtds.dao.UserDao;
import com.brother.gtds.model.User;

@Service("testService")
public class TestService {
	
	@Resource
	private UserDao userDao;
	
	public void testService() {
		System.out.println("testService..");
		userDao.saveUser(new User());
	}

}
