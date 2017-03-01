package com.example;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import com.example.model.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.example.dto.MessageRequest;
import com.example.dto.MessageResponse;
import com.example.repository.TagRepository;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
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
		Tag tagObject;
		if((tagObject=tagRepository.findByName(tag)) == null){
			return "검색 결과가 없습니다.";
		}
		return tagObject.getSearchResult();
	}
}
