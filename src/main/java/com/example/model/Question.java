package com.example.model;

import java.util.List;

import javax.persistence.*;

@Entity
public class Question extends Content {
	
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

	@Column(name="title", nullable=false)
	private String title;
	
	@Column(name="url")
	private String url;

	@Column(name="content")
	private String content;

	@OneToMany(mappedBy = "question")
	private List<Answer> answers;

	@Override
	public String toShortString() {
		return "[" + idx + "]" + title + "(" + url +")";
	}

	@Override
	public String toDetailString() {
		return "title : " + title + "\n" + "내용 : " + content;
	}
}
