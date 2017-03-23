package com.example;

import com.example.annotations.Command;
import com.example.dto.MessageRequest;
import com.example.dto.MessageResponse;
import com.example.functions.Function;
import com.example.functions.impl.HelloUltraFunction;
import com.example.functions.impl.RedisFunction;
import com.example.model.Redis;
import com.example.service.QuestionService;
import com.example.utils.CustomUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

@Component
public class MessageDispatcher {

	@Autowired
	private RedisFunction redisFunction;

	@Autowired
	private QuestionService questionService;

	@Autowired
	private HelloUltraFunction helloUltraFunction;

	private static final Logger log = LoggerFactory.getLogger(MessageDispatcher.class);

	private Map<String, Commander<?>> functionMap = new HashMap<>();

	public MessageResponse dispatch(MessageRequest message) {
		try {
			Commander commander = functionMap.get(message.command());
			if(commander == null){
				return MessageResponse.FAILED;
			}
			return  new MessageResponse((String) commander.method.invoke(commander.function, message.keyword()), null, null);
		} catch(Exception e){
			log.error("dispatch - {} : {}", message.command(), e);
			return MessageResponse.FAILED;
		}
	}

	@SuppressWarnings("unchecked")
	public <T extends Function> void put(T function, Method method) {
		functionMap.put("#" + method.getAnnotation(Command.class).value(), new Commander(function, method));
	}

	private static class Commander<T extends Function> {
		private T function;
		private Method method;

		Commander(T function, Method method){
			this.function = function;
			this.method = method;
		}
	}


	public MessageResponse redisDispatch(MessageRequest message) {
		log.debug("redisDispatch start user_key : {} , message : {}", message.getUser_key() ,message.getContent());

		//최초 redis pop
		String value = redisFunction.pop(message.getUser_key());
		log.debug("value : {}", value);

		if(value == null && message.getContent().equals("검색")) {
			//최초 검색입력시 redis push
			Redis redis = new Redis();
			redis.setFunction("search");
			redisFunction.push(message.getUser_key(), CustomUtil.objectToString(redis));
			return new MessageResponse("검색어를 입력하세요.", null, null);
		}

		//일반단어가 들어왔을경우
		Redis redis = CustomUtil.stringToObject(value);
		String resultMessage = null;
		switch (redis.getFunction()) {
			case "search" :
				resultMessage = helloUltraFunction.search(message, redis);
				break;
			case "questionDetail" :	//질문상세보기 일경우.

				break;
			case "searchAnswer" :

				break;
			case "answerDetail" :

				break;
			default :

				break;
		}
		log.debug("redis function : {}, param : {}", redis.getFunction(), redis.getParam().toString());

		return new MessageResponse(resultMessage, null, null);
	}
}
