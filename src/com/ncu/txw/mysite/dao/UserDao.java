package com.ncu.txw.mysite.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import com.ncu.txw.mysite.entities.User;

public interface UserDao extends JpaRepository<User, Integer>{

	User getByUserName(String userName);
	
	User getById(Integer id);
}
