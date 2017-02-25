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
	
	@PostConstruct
	private void init() throws NoSuchMethodException, SecurityException{
		functionMap.put("#검색", this.getClass().getMethod("search", String.class));
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
	
/*	log.debug("메세지받음");
	// 버튼으로 돌려줘야 하는리
	if ("#회원가입".equals(message.getContent())) {
		log.debug("버튼메시지 입력받음");
		message.setButton(true);
	}

	log.debug(message.toString());

	// 회원가입.. 꼭 컨트롤러에서밖에 못하냐!!!
	if (message.isNickName()) {
		User user = new User(message);
		// user.toSave(message.getUser_key(),
		// message.getContent().substring(1));
		userRepository.save(user);
		return ResponseMessage.response(message, "감사합니다! 이제 hello-utlra의 서비스를 이용하실 수 있습니다.");
	}

	log.debug(ResponseMessage.response(message, Answers.answer(message)).toString());
	if (Answers.answer(message) == null) {
		return JsonConverter.makeObject(message, "무슨말인지 잘 모르겠어요 ㅠㅠ");
	}
	return ResponseMessage.response(message, Answers.answer(message));*/
}
