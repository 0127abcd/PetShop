package com.ncu.txw.mysite.entities;

import java.util.LinkedList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Table(name="PETS")
@Entity
public class Pet {
	
	private Integer id;
	private String petName;
	private String petCategory;
	private Integer petCount;
	private int petPrice;
	private String petImg;
	
	private String petDisc;
	
	private List<ShopCar> shopcars = new LinkedList<ShopCar>(); 
	private List<Comment> comments = new LinkedList<Comment>();
	
	
	@GeneratedValue
	@Id
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getPetName() {
		return petName;
	}
	public void setPetName(String petName) {
		this.petName = petName;
	}
	public String getPetCategory() {
		return petCategory;
	}
	public void setPetCategory(String petCategory) {
		this.petCategory = petCategory;
	}
	public Integer getPetCount() {
		return petCount;
	}
	public void setPetCount(Integer petCount) {
		this.petCount = petCount;
	}
	public int getPetPrice() {
		return petPrice;
	}
	public void setPetPrice(int petPrice) {
		this.petPrice = petPrice;
	}
	public String getPetImg() {
		return petImg;
	}
	public void setPetImg(String petImg) {
		this.petImg = petImg;
	}
	public String getPetDisc() {
		return petDisc;
	}
	public void setPetDisc(String petDisc) {
		this.petDisc = petDisc;
	}
	@ManyToMany(mappedBy="pets")
	public List<ShopCar> getShopcars() {
		return shopcars;
	}
	public void setShopcars(List<ShopCar> shopcars) {
		this.shopcars = shopcars;
	}
	
	@OneToMany(mappedBy="pet")
	public List<Comment> getComments() {
		return comments;
	}
	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}
	
	@Override
	public String toString() {
		return "Pet [id=" + id + ", petName=" + petName + ", petCategory=" + petCategory + ", petCount=" + petCount
				+ ", petPrice=" + petPrice + ", petImg=" + petImg + "]";
	}
}
