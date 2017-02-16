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
	
	@Column(name="user_key", nullable=false)
	private String user_key;
	@Column(name="nickName", nullable=false)
	private String nickName;
	
	public String getUser_key() {
		return user_key;
	}
	public void setUser_key(String userKey) {
		this.user_key = userKey;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	
}
