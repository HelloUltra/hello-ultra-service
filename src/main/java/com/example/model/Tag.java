package com.example.model;

import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
public class Tag {
	
	@Id
	@GeneratedValue
	private Long idx;
	
	@Column
	private String name;
	
	@ManyToMany(mappedBy="tags")
	private List<Question> questions;

	public String getSearchResult(){
		StringBuilder stringBuilder = new StringBuilder();
		questions.stream().forEach(question -> {
			stringBuilder.append(question);
			stringBuilder.append("\n");
		});
		return stringBuilder.toString();
	}

	@Override
	public String toString() {
		return "Tag [idx=" + idx + ", name=" + name + ", questions=" + questions + "]";
	}
	
}
