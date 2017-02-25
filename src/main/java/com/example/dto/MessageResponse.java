package com.example.dto;

public class MessageResponse {
	private String text;
	private Photo photo;
	private MessageButton messageButton;
	
	public MessageResponse(String text, Photo photo, MessageButton messageButton){
		this.text = text;
		this.photo = photo;
		this.messageButton = messageButton;
	}
	
	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Photo getPhoto() {
		return photo;
	}

	public void setPhoto(Photo photo) {
		this.photo = photo;
	}

	public MessageButton getMessageButton() {
		return messageButton;
	}

	public void setMessageButton(MessageButton messageButton) {
		this.messageButton = messageButton;
	}

	static class Photo {
		private String url;
		private Integer width;
		private Integer height;
		public String getUrl() {
			return url;
		}
		public void setUrl(String url) {
			this.url = url;
		}
		public Integer getWidth() {
			return width;
		}
		public void setWidth(Integer width) {
			this.width = width;
		}
		public Integer getHeight() {
			return height;
		}
		public void setHeight(Integer height) {
			this.height = height;
		}
	}
	
	static class MessageButton {
		private String label;
		private String url;
		public String getLabel() {
			return label;
		}
		public void setLabel(String label) {
			this.label = label;
		}
		public String getUrl() {
			return url;
		}
		public void setUrl(String url) {
			this.url = url;
		}
	}
}
