package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.model.Message;
import com.example.model.User;
import com.example.repository.UserRepository;



@Repository
public class Answer{

	//버튼으로 받은 텍스트인지 관련 이슈
	//일단 메시지에 ㄱ한자1 붙여서 처리해봄.
	//private Boolean isButtonMessage = false;
	

	@Autowired
	private UserRepository userRepository;
	
	
	public String makeMessage(Message ms) {
		String message;
		
		if("안녕".equals(ms.getContent())){
			message = "안녕하세욤";
			return message;
		}
		if("#회원가입".equals(ms.getContent())){
			//버튼으로 리턴하는 경우에는 메시지에 따라 지정할 수 밖에 없는가.
			ms.setButton(true);
			message ="본 서비스와 관련된 정보가 저장됩니다. 동의하십니까?"; 
			return message; 
		}
		
		//회원가입 동의 버튼
		if("확인　".equals(ms.getContent())){
			message ="감사합니다! 먼저 '@닉네임' 형태로 사용하실 닉네임을 입력해주세요. ex)@ultra"; 
			return message; 
		}
		if("취소　".equals(ms.getContent())){
			message ="다음엔 꼭 함께해주세요"; 
			return message; 
		}
		
		//회원가입닉네임받기
		if(ms.getContent().startsWith("@")){
			
			User user = new User();
			user.setUser_key(ms.getUser_key());
			user.setNickName(ms.getContent().substring(1));
			userRepository.save(user);
			message = "감사합니다! 이제 hello-utlra의 서비스를 이용하실 수 있습니다.";
			return message;
		}
		
		return ms.getContent();
	}

}
