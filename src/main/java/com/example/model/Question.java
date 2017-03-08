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

	@Column(name="content")
	private String content;

	public Long getIdx() {
		return idx;
	}

	@Override
	public String toString() {
		return "[" + idx + "]" + title + "(" + url +")";
	}

	//게시물 상세정보 내용.
	public String getQuestionDetailInfo() {
		return "title : " + title + "\n" + "내용 : " + content;
	}
}
