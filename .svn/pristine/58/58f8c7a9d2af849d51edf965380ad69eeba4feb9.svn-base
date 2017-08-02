package com.ncu.txw.mysite.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ncu.txw.mysite.dao.CommentDao;
import com.ncu.txw.mysite.entities.Comment;

@Service
public class CommentService {
	
	@Autowired
	private CommentDao commentDao;
	
	@Transactional
	public List<Comment> getAll(Integer petId){
		return commentDao.getAllComments(petId);
	}
	
	@Transactional
	public Comment getByUserId(Integer userId, Integer petId){
		return commentDao.getByUserId(userId, petId);
	}
	
	@Transactional
	public void save(Comment comment){
		commentDao.saveAndFlush(comment);
	}
}
