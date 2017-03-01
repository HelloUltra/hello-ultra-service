package com.example.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

@Entity
public class Tag {
	
	@Id
	@GeneratedValue
	private Long idx;
	
	@Column
	private String name;
	
	@ManyToMany(mappedBy="tags")
	private List<Question> questions;

	public List<Question> getQuestions() {
		return questions;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setQuestions(List<Question> questions) {
		this.questions = questions;
	}

	@Override
	public String toString() {
		return "Tag [idx=" + idx + ", name=" + name + ", questions=" + questions + "]";
	}
	
}
