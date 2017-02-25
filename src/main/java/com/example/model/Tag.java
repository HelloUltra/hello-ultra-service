package com.example.model;

import java.util.List;

public class Tag {
	private Long idx;
	private String tagName;
	private List<Question> questions;
	public List<Question> getQuestions() {
		return questions;
	}
}
