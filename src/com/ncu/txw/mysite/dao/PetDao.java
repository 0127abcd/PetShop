package com.ncu.txw.mysite.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ncu.txw.mysite.entities.Pet;

public interface PetDao extends JpaRepository<Pet, Integer>{

	Pet getByPetName(String petName);
	
	Pet getById(Integer id);
	
	@Query(value="select * from PETS p where p.pet_category = ?1 limit ?2, 6", nativeQuery=true)
	List<Pet> getByPetCategory(String petCategory, int pageNo);
	
	List<Pet> getByPetCategory(String petCategory);
	
}
