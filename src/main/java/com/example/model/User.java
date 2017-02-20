package com.example.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class User {
	
	@Id
	@GeneratedValue
	private Long id;
	
	@Column(name="user_key", nullable=false)
	private String userKey;
	@Column(name="nick_name", nullable=false)
	private String nickName;
	
	public String getUserKey() {
		return userKey;
	}
	public void setUser_key(String userKey) {
		this.userKey = userKey;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public void toSave(String user_key, String nickName){
		this.userKey = user_key;
		this.nickName = nickName;
	}
	
}
