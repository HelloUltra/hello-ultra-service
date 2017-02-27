package com.example;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.dto.KeyBoardResponse;
import com.example.dto.MessageRequest;
import com.example.dto.MessageResponse;
import com.example.repository.TagRepository;

@Component
public class MessageDispatcher {
	public static final KeyBoardResponse DEFAULT_KEYBOARD = new KeyBoardResponse("text", null); 
	
	@Autowired
	private TagRepository tagRepository;
	
	private Map<String, Method> functionMap= new HashMap();
	
	//@PostConstruct
	private void init() throws NoSuchMethodException, SecurityException{
		functionMap.put("#검색", this.getClass().getMethod("search", new String().getClass()));
	}
	
	public MessageResponse dispatch(MessageRequest message) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		String result = (String) functionMap.get(message.command()).invoke(this, message.keyword());
		return new MessageResponse(result, null, null);
	}
	
	private String search(String tag){
		StringBuilder stringBuilder = new StringBuilder();
		tagRepository.findByTagName(tag).getQuestions()
		.stream().forEach(question -> {
			stringBuilder.append(question);
			stringBuilder.append("\n");
		});;
		return stringBuilder.toString();
	}
}
