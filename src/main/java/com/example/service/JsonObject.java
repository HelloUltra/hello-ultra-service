package com.example.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.example.model.Message;

@Service
public class JsonObject {
	public ArrayList<String> btList = new ArrayList<String>();

	public Map <String, Object> msMap = new HashMap<String, Object>();
	public Map <String, Object> upperMsMap = new HashMap<String, Object>();
	public Map <String, Object> keyboardMap = new HashMap<String, Object>();
	public Map <String, Object> upperKeyboardMap = new HashMap<String, Object>();
	public Map <String, Object> sendObject = new HashMap<String, Object>();
	
	//버튼처리 오브젝트
	public Map<String,Object> buttonObject(String makedMessage, Message ms){
		
		//회원가입버튼
		if(ms.getContent().equals("#회원가입")){
			btList.add("확인　");
			btList.add("취소　");
		}
		
		keyboardMap.put("type","buttons");
		keyboardMap.put("buttons",btList);	

		msMap.put("text",makedMessage);
		
		sendObject.put("message", msMap);
		sendObject.put("keyboard", keyboardMap);
		
		return sendObject;
	}
	
	//단순 텍스트메시지 오브젝트
	public Map <String, Object> textObject(String makedMessage, Message ms){
		msMap.put("text", makedMessage);
		sendObject.put("message", msMap);
		return sendObject;
	}
}
