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
		//아니 findAll은 List<User>에 담아야 한다는데 list는 <User>로 초기화 할 수 없다는데  arrayList로 초기화 해보려고해도
		//nullpoint떨어지고 어쩌란말이냐.
		/*List<User> userList = new ArrayList<User>();
		userList = userRepository.findAll();
		if(userList.contains(userKey)){
			return true;
		}*/
		return false;
	}
}
