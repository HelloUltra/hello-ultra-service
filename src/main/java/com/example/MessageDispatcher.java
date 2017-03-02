package com.example;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.dto.KeyBoardResponse;
import com.example.dto.MessageRequest;
import com.example.dto.MessageResponse;
import com.example.model.Tag;
import com.example.repository.TagRepository;
import com.example.utils.DataParser;

@Component
public class MessageDispatcher {
	public static final KeyBoardResponse DEFAULT_KEYBOARD = new KeyBoardResponse("text", null);

	private static final Logger log = LoggerFactory.getLogger(MessageDispatcher.class);

	@Autowired
	private TagRepository tagRepository;

	private Map<String, Method> functionMap = new HashMap<String, Method>();

	@PostConstruct
	private void init() throws NoSuchMethodException, SecurityException {
		//차후 프로젝트 분리.
		DataParser.hashTagParser();
		for(int i = 0; i<DataParser.hashTags.size(); i++){
			tagRepository.save(DataParser.hashTags.get(i));
			log.debug(DataParser.hashTags.get(i).getTagName());
		}
		functionMap.put("#검색", this.getClass().getDeclaredMethod("search", String.class));
		
	}

	public MessageResponse dispatch(MessageRequest message)
			throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		String result = (String) functionMap.get(message.command()).invoke(this, message.keyword());

		return new MessageResponse(result, null, null);
	}

	private String search(String tag) {
		log.debug("#검색:{}", tag);
		StringBuilder stringBuilder = new StringBuilder();
		
		Tag findByTag = tagRepository.findByTagName(tag);
		
		if (isNull(findByTag)) {
			return "해당 태그에 대한 검색결과가 없습니다.";
		}
		findByTag.getQuestions().stream().forEach(question -> {
			stringBuilder.append(question);
			stringBuilder.append("\n");
		});
		
		log.debug("answerForSearch:{}", stringBuilder.toString());
		return stringBuilder.toString();
	}


	private boolean isNull(Tag tag) {
		if (tag == null) {
			log.debug("결과값이 {} 입니다.", tag);
			return true;
		}
		log.debug("{} 에 해당하는 결과값이 있습니다.", tag);
		return false;
	}
}
