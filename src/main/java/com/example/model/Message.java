package com.example.model;


public class Message {
	private String user_key;
	private String type;
	private String content;
	private static String tagName;

	private boolean button = false;
	private boolean nickName = false;

	public static String getTagName() {
		return tagName;
	}

	public boolean isNickName() {
		if(content.startsWith("@")){
			nickName = true;
		}
		return nickName;
	}
	

	public String getUser_key() {
		return user_key;
	}

	public String getType() {
		return type;
	}

	public String getContent() {
		return content;
	}

	public boolean isButton() {
		return button;
	}

	public void setButton(boolean button) {
		this.button = button;
	}

	@Override
	public String toString() {
		return "Message [user_key=" + user_key + ", type=" + type + ", content=" + content + "]";
	}

}
