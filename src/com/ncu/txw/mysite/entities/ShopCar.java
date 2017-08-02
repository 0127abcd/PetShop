package com.ncu.txw.mysite.entities;

import java.util.LinkedList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Table(name="SHOPCAR")
@Entity
public class ShopCar {
	
	private Integer id;
	
	private Integer status;
	
	private Integer complete;
	
	private List<Pet> pets = new LinkedList<Pet>();
	
	private User user;
	
	@GeneratedValue
	@Id
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
	public Integer getComplete() {
		return complete;
	}

	public void setComplete(Integer complete) {
		this.complete = complete;
	}

	@ManyToMany
	@JoinTable(name="ShopCar_Pet",
			joinColumns={@JoinColumn(name="ShopCar_ID", referencedColumnName="id")}, 
			inverseJoinColumns={@JoinColumn(name="Pet_ID", referencedColumnName="id")})
	public List<Pet> getPets() {
		return pets;
	}

	public void setPets(List<Pet> pets) {
		this.pets = pets;
	}

	@JoinColumn(name="user_id")
	@ManyToOne
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "ShopCar [id=" + id + ", status=" + status + ", complete=" + complete +", pets=" + pets + ", user=" + user.getUserName() + "]";
	}
	
	public ShopCar() {
		status = 0;
		complete = 0;
	}
}
