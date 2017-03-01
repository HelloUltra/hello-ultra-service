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
	@JoinTable(name="question_tag",
	joinColumns = @JoinColumn(name = "question_idx", referencedColumnName = "idx"),
	inverseJoinColumns=@JoinColumn(name = "tag_idx", referencedColumnName = "idx"))
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

	public List<Tag> getTags() {
		return tags;
	}

	public void setTags(List<Tag> tags) {
		this.tags = tags;
	}

	public User getWriter() {
		return writer;
	}

	public void setWriter(User writer) {
		this.writer = writer;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Override
	public String toString() {
		return "[" + idx + "]" + title + "(" + url +")";
	}
}
