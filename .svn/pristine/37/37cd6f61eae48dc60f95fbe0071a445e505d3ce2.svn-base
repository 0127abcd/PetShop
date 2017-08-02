package com.ncu.txw.mysite.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ncu.txw.mysite.entities.ShopCar;


public interface ShopCarDao extends JpaRepository<ShopCar, Integer>{
	
	@Query(value="select * from SHOPCAR", nativeQuery=true)
	List<ShopCar> findAll();
	
	@Query(value="select * from SHOPCAR order by id desc limit ?1, 5", nativeQuery=true)
	List<ShopCar> getShopcars(Integer pageNo);
	
	ShopCar getById(Integer id);
	
}

