package com.example.utility;

import java.util.HashMap;
import java.util.Map;

import com.example.model.Message;


public class Answers {
	private static Map<String, Object> answers = new HashMap<String, Object>();
	
	static{
		answers.put("안녕", "안뇽하세요");
		answers.put("#회원가입", "본 서비스와 관련된 정보가 저장됩니다. 동의하십니까?");
		answers.put("확인　", "감사합니다! 먼저 '@닉네임' 형태로 사용하실 닉네임을 입력해주세요. ex)@ultra");
		answers.put("취소　", "다음엔 꼭 함께해주세요");
		answers.put("안녕", "안뇽하세요");
		answers.put("#네이버", DataParser.test());
		
		
	}
	public static String answer(Message message){
		return (String) answers.get(message.getContent());
	}
}
