package com.example.model;

public class Message {
	private String user_key;
	private String type;
	private String content;
	private boolean isButton = false;
	
	public String getUser_key() {
		return user_key;
	}
	
	public void setUserkey(String user_key) {
		this.user_key = user_key;
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
		return "Message [user_key=" + user_key + ", type=" + type + ", content=" + content + "]";
	}
	

}
