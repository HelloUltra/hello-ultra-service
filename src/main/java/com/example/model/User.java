package com.example.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class User {
	
	@Id
	@GeneratedValue
	private int id;
	
	@Column(name="userKey", nullable=false)
	private String userKey;
	@Column(name="nickName", nullable=false)
	private String nickName;
	
	public String getUserkey() {
		return userKey;
	}
	public void setUserkey(String userKey) {
		this.userKey = userKey;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	
}
