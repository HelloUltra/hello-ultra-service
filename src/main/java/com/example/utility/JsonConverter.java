package com.example.utility;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.example.model.Message;


public class JsonConverter {
	private static ArrayList<String> btList = new ArrayList<String>();
	private static Map <String, Object> msMap = new HashMap<String, Object>();
	private static Map <String, Object> keyboardMap = new HashMap<String, Object>();
	private static Map <String, Object> sendObject = new HashMap<String, Object>();
	
	//버튼처리 오브젝트	
	public static Map <String, Object> makeObject(Message ms, String message){
		if(ms.isButton()){
			//어떤버튼기능제공할지
			if("#회원가입".equals(ms.getContent())){
				btList.clear();
				btList.add("확인　");
				btList.add("취소　");
			}
			
			keyboardMap.put("type","buttons");
			keyboardMap.put("buttons",btList);	
			
			msMap.put("text",message);
			
			sendObject.put("message", msMap);
			sendObject.put("keyboard", keyboardMap);
		
			return sendObject;
		}
		msMap.put("text", message);
		sendObject.put("message", msMap);
		return sendObject;
	}
}
