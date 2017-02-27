package com.example.dto;

public class MessageRequest {
	private String user_key;
	private String type;
	private String content;
	public String getUser_key() {
		return user_key;
	}
	public void setUser_key(String user_key) {
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
	
	public String command(){
		return this.content.split(" ")[0];
	}
	
	public String keyword(){
		return this.content.substring(command().length(), this.content.length());
	}
}
