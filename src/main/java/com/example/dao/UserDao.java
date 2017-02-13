package com.example.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.model.User;
import com.example.repository.UserRepository;

public class UserDao {
	
	@Autowired
	private UserRepository userRepository;
	
	public void memberInsert(User user){
		userRepository.save(user);
	}
	
	public boolean checkMember(String userKey){
		List <User> memberList = new ArrayList<User>();
		memberList = userRepository.findAll();
		if(memberList.contains(userKey)){
			return true;
		}
		return false;
	}
}
