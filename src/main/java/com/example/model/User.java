package com.example.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

@Entity
public class User {
	
	@Id
	@GeneratedValue
	private Long idx;
	
	@Column(name = "nickName", nullable=false, unique=true )
	private String nickName;
	
	@Column(name = "user_key", nullable=false, unique=true)
	private String userKey;
	
/*	@OneToMany(mappedBy="writer")
	//@JoinColumn(foreignKey = @ForeignKey(name = "fk_question_writer"))
	private List<Question> question;
*/
}
