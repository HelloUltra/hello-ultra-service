package com.example.model;

public class Message {
	private String userKey;
	private String type;
	private String content;
	private boolean isButton = false;
	
	public String getUserkey() {
		return userKey;
	}
	
	public void setUserkey(String userKey) {
		this.userKey = userKey;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	public boolean isButton() {
		return isButton;
	}

	public void setButton(boolean isButton) {
		this.isButton = isButton;
	}

	@Override
	public String toString() {
		return "Message [userKey=" + userKey + ", type=" + type + ", content=" + content + "]";
	}
	

}
