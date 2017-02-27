package com.example.dto;

public class KeyBoardResponse {

	private String type;
	private String[] buttons;

	public KeyBoardResponse(String type, String[] buttons) {
		this.type = type;
		this.buttons = buttons;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String[] getButtons() {
		return buttons;
	}

	public void setButtons(String[] buttons) {
		this.buttons = buttons;
	}
}
