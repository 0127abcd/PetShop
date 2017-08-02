package com.ncu.txw.mysite.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ncu.txw.mysite.dao.UserDao;
import com.ncu.txw.mysite.entities.User;

@Service
public class UserService {

	@Autowired
	private UserDao userDao;
	
	@Transactional
	public User getByUserName(String userName){
		return userDao.getByUserName(userName);
	}
	
	@Transactional
	public void save(User user){
		userDao.saveAndFlush(user);
	}
	
}
