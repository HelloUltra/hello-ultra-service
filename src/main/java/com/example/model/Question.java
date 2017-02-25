package com.example.model;

import java.util.List;

public class Question {
	private Long idx;
	private User user;
	private List<Answer> answers;
	private String title;
	private String url;
	
	@Override
	public String toString() {
		return "[" + idx + "]" + title + "(" + url +")";
	}
}
