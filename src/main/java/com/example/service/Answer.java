package com.example.service;

import org.springframework.stereotype.Service;

import com.example.dao.UserDao;
import com.example.model.Message;
import com.example.model.User;


@Service
public class Answer{
	private Boolean isButton = false;
	
	//버튼으로 받은 텍스트인지 관련 이슈
	//일단 메시지에 ㄱ한자1 붙여서 처리해봄.
	//private Boolean isButtonMessage = false;
	
	public String message;

	
	public Boolean getIsButton() {
		return isButton;
	}
	public Boolean getIsButtonMessage() {
		return isButton;
	}

	public String makeMessage(Message ms) {

		if(ms.getContent().equals("안녕")){
			message = "안녕하세욤";
			return message;
		}
		if(ms.getContent().equals("#회원가입")){
			isButton = true;
			message ="본 서비스와 관련된 정보가 저장됩니다. 동의하십니까?"; 
			return message; 
		}
		
		//회원가입 동의 버튼
		if(ms.getContent().equals("확인　")){
			message ="감사합니다! 먼저 '@닉네임' 형태로 사용하실 닉네임을 입력해주세요. ex)#ultra"; 
			return message; 
		}
		if(ms.getContent().equals("취소　")){
			message ="다음엔 꼭 함께해주세요"; 
			return message; 
		}
		
		//회원가입닉네임받기
		if(ms.getContent().startsWith("@")){
			UserDao userDao = new UserDao();
			User user = new User();
			user.setUserkey(ms.getUserkey());
			user.setNickName(ms.getContent().substring(1));
			
			if(userDao.checkMember(user.getUserkey())){
				return "이미 회원이세요!";
			}
			
			userDao.memberInsert(user);
			message = "감사합니다! 이제 hello-utlra의 서비스를 이용하실 수 있습니다.";
			return message;
		}
		
		return ms.getContent();
	}

}
