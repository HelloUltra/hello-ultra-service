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
	
	@Column(name="tagName")
	private String tagName;
	
	@ManyToMany//(mappedBy="tags")
	@JoinTable(name = "question_tags",
	joinColumns = @JoinColumn(name="tags_idx"),
	inverseJoinColumns=@JoinColumn(name="questions_idx"))
	//@JoinColumn(foreignKey=@ForeignKey(name="fk_tag_question"))
	private List<Question> questions;

	public List<Question> getQuestions() {
		return questions;
	}

	public String getTagName() {
		return tagName;
	}

	public void setTagName(String tagName) {
		this.tagName = tagName;
	}

	public void setQuestions(List<Question> questions) {
		this.questions = questions;
	}

	@Override
	public String toString() {
		return "Tag [idx=" + idx + ", tagName=" + tagName + ", questions=" + questions + "]";
	}
	
}
