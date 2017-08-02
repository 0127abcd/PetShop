package com.ncu.txw.mysite.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name="USERS")
@Entity
public class User {

	private Integer id;
	private String userName;
	private String password;
	
	private String email;
	private String address;
	private String tel;
	
	private Integer admin;
	
	@Id
	@GeneratedValue
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public Integer getAdmin() {
		return admin;
	}
	public void setAdmin(Integer admin) {
		this.admin = admin;
	}
	
	@Override
	public String toString() {
		return "User [id=" + id + ", userName=" + userName + ", password=" + password + ", email=" + email + "]";
	}
	
	public User(Integer id, String userName, String password, String email, String address, String tel) {
		super();
		this.id = id;
		this.userName = userName;
		this.password = password;
		this.email = email;
		this.address = address;
		this.tel = tel;
		this.admin = 0;
	}
	public User() {
		admin = 0;
	}
}
