package com.ncu.txw.mysite.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ncu.txw.mysite.entities.Comment;

public interface CommentDao extends JpaRepository<Comment, Integer> {
	
	@Query(value="select * from COMMENTS where pet_id = ?1", nativeQuery=true)
	List<Comment> getAllComments(Integer petId);

	@Query(value="select * from COMMENTS where user_id = ?1 and pet_id = ?2", nativeQuery=true)
	Comment getByUserId(Integer userId, Integer petId);
}
