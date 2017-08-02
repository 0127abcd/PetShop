package com.ncu.txw.mysite.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ncu.txw.mysite.dao.ShopCarDao;
import com.ncu.txw.mysite.entities.ShopCar;

@Service
public class ShopCarService{
	
	@Autowired
	private ShopCarDao dao;
	
	@Transactional
	public ShopCar getById(Integer id){
		return dao.getById(id);
	}
	
	@Transactional
	public List<ShopCar> findAllCar(){
		return dao.findAll();
	}
	
	
	@Transactional
	public void save(ShopCar shopCar){
		dao.saveAndFlush(shopCar);
	}
	@Transactional
	public List<ShopCar> getShopcars(Integer pageNo){
		return dao.getShopcars(pageNo);
	}
	
}