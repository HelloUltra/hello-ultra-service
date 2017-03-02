package com.example.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

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
