package com.example.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.example.model.User;
import com.example.repository.UserRepository;

//둘다 테스트해보자.
@Repository
public class UserDao {
	
	//userRepository 인터페이스에서 npe발생 이슈.
	@Autowired
	private UserRepository userRepository;
	
	public void memberInsert(User user){
		userRepository.save(user);
	}
	
	public boolean checkMember(String userKey){
		List<User> userList = new ArrayList<User>();
		userList = userRepository.findAll();
		if(userList.contains(userKey)){
			return true;
		}
		return false;
	}
}
