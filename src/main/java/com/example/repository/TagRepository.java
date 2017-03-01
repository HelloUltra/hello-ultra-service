package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.model.Tag;

public interface TagRepository extends JpaRepository<Tag, Long>{
	Tag findByName(String name);
}
