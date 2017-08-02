package com.ncu.txw.mysite.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ncu.txw.mysite.dao.PetDao;
import com.ncu.txw.mysite.entities.Pet;


@Service
public class PetService {

	@Autowired
	private PetDao petDao;
	
	@Transactional
	public Pet getByPetName(String petName){
		return petDao.getByPetName(petName);
	}
	
	@Transactional(readOnly=true)
	public List<Pet> getByPetCategory(String petCategory, int pageNo, int pageSize){
		return petDao.getByPetCategory(petCategory, (pageNo-1)*pageSize);
	}
	
	@Transactional
	public List<Pet> getByPetCategory(String petCategory){
		return petDao.getByPetCategory(petCategory);
	}
	
	@Transactional
	public void save(Pet pet){
		petDao.saveAndFlush(pet);
	}
	
	@Transactional
	public Pet getById(Integer id){
		return petDao.getById(id);
	}
	
}
