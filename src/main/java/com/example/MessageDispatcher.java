package com.example;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.example.dto.KeyBoardResponse;
import com.example.dto.MessageRequest;
import com.example.dto.MessageResponse;
import com.example.repository.TagRepository;

@Component
public class MessageDispatcher {
	private static final Logger log = LoggerFactory.getLogger(MessageDispatcher.class);

	private Map<String, Method> functionMap;

	@Autowired
	private TagRepository tagRepository;

	public MessageDispatcher(){
		functionMap= new HashMap<>();
		try {
			functionMap.put("#검색", this.getClass().getDeclaredMethod("search", String.class));
		} catch (NoSuchMethodException e) {
			log.error("Init Error #검색 - {}", e);
		}
	}

	
	public MessageResponse dispatch(MessageRequest message) {
		try {
			return  new MessageResponse((String) functionMap.get(message.command()).invoke(this, message.keyword()), null, null);
		} catch(Exception e){
			log.error("dispatch - {} : {}", message.command(), e);
			return MessageResponse.FAILED;
		}
	}
	
	private String search(String tag){
		log.debug("#검색:{}",tag);
		StringBuilder stringBuilder = new StringBuilder();
		/*
		이런 코드는 테스트케이스 작성해서 확인해주세요.
		Tag tagg = new Tag();
		tagg = tagRepository.findByTagName(tag);
		System.out.println(tagg);
		*/
		tagRepository.findByName(tag).getQuestions()
		.stream().forEach(question -> {
			stringBuilder.append(question);
			stringBuilder.append("\n");
		});
		return stringBuilder.toString();
	}
}
