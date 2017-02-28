package com.example.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
public class Question {
	
	@Id
	@GeneratedValue
	private Long idx;
	
	@ManyToMany
	@JoinTable(name="question_tags",
	joinColumns = @JoinColumn(name = "questions_idx"),
	inverseJoinColumns=@JoinColumn(name = "tags_idx"))
	//@JoinColumn(foreignKey=@ForeignKey(name="fk_tag_question"))
	private List<Tag> tags;
	
	@ManyToOne
	@JoinColumn(foreignKey = @ForeignKey(name = "fk_question_writer"))
	private User writer;
	
	/*@Column(name="answers")
	private List<Answer> answers;
	*/
	@Column(name="title", nullable=false)
	private String title;
	
	@Column(name="url")
	private String url;
	
	@Override
	public String toString() {
		return "[" + idx + "]" + title + "(" + url +")";
	}
}
