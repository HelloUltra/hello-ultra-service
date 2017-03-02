package com.example.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
public class User {
	
	@Id
	@GeneratedValue
	@Column(name="user_idx")
	private Long idx;
	
	@Column(name = "nickName", nullable=false, unique=true)
	@JsonProperty
	private String nickName;
	

	@Column(name = "user_key", nullable=false, unique=true)
	private String userKey;
	
	@OneToMany(mappedBy="writer")
	private List<Question> question;

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getUserKey() {
		return userKey;
	}

	public void setUserKey(String userKey) {
		this.userKey = userKey;
	}
}
