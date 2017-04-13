package com.example.dispatcher;

import com.example.annotations.Command;
import com.example.dto.MessageRequest;
import com.example.dto.MessageResponse;
import com.example.functions.Function;
import com.example.functions.impl.HelloUltraFunction;
import com.example.functions.impl.QuestionFunction;
import com.example.functions.impl.RedisFunction;
import com.example.model.ConversationInfo;
import com.example.utils.CustomUtil;
import org.apache.commons.lang3.text.WordUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

@Component
@EnableAspectJAutoProxy
public class MessageDispatcher {

	@Autowired
	private QuestionFunction questionFunction;
	@Autowired
	private RedisFunction redisFunction;

	@Autowired
	private HelloUltraFunction helloUltraFunction;

//	@Autowired Function function;

	private static final Logger log = LoggerFactory.getLogger(MessageDispatcher.class);

	private Map<String, Commander<?>> functionMap = new HashMap<>();

	/**
	 @key : 대화형 시작점 예약어
	 @value : function key
	 */
	private Map<String, String> startMap = new HashMap<>();

	/**
	 @key : function key
	 @value : Commander class
	 */
	private Map<String, Commander<?>> commanderMap = new HashMap<>();

	/**
	 @key : function key
	 @value : Conversation class
	 */
	Map<String, Conversation> conversationMap = new HashMap<>();

	@SuppressWarnings("unchecked")
	public <T extends Function> void commanderPut(T function, Method method, BeanFactory beanFactory) {
		commanderMap.put(method.getAnnotation(Command.class).function(), new Commander(function, method, beanFactory));
	}


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
		functionMap.put("#" + method.getAnnotation(Command.class).value(), new Commander(function, method, null));
	}

	private static class Commander<T extends Function> {
		private  T function;
		private  Method method;
		private BeanFactory beanFactory;

		private String name;
		private Object obj;

		Commander(T function, Method method, BeanFactory beanFactory){
			this.function = function;
			this.method = method;
			this.beanFactory = beanFactory;
		}


		public String execute(MessageRequest messageRequest) throws InvocationTargetException, IllegalAccessException {
			this.name = WordUtils.uncapitalize(function.getClass().getSimpleName());
			obj = beanFactory.getBean(name);
			return (String) this.method.invoke(obj, messageRequest);
		}
	}

	private static class Conversation {
		String function;
		//search1

		Map<String, String> afters;
		//#다음, search2
		//#이전, search3

		String query;
		//detail

		String getFunction(){
			return function;
		}

		String findMethod(String command){
			return afters.containsKey(command)?afters.get(command):query;
		}
	}

	public MessageResponse redisDispatch(MessageRequest message) {
		log.debug("redisDispatch start user_key : {} , message : {}", message.getUser_key() ,message.getContent());




		log.debug("-------------------");
		questionFunction.search(null);	//aop 정상작동
		log.debug("-------------------");

		//test start
		//questionFunction.search(null);	//aop 정상작동

		try {
			log.debug("-------------------");
			commanderMap.get("search").execute(null);
			log.debug("-------------------");
		}
		catch (Exception e) {
			e.printStackTrace();
			return MessageResponse.FAILED;
		}

//		catch (InvocationTargetException | IllegalAccessException e) {
//			return MessageResponse.FAILED;
//		}
		//test end



		startMap.put("검색","search");	//test

		ConversationInfo conversationInfo = CustomUtil.stringToObject(redisFunction.getLastValue(message.getUser_key()));

		if(conversationInfo == null && !startMap.containsKey(message.getContent()) ||
				conversationInfo != null && !conversationMap.containsKey(conversationInfo.getFunction())){
			return MessageResponse.FAILED;
		}

		Commander command = commanderMap.get(
				conversationInfo==null?
						startMap.get(message.getContent()):
						conversationMap.get(conversationInfo.getFunction()).findMethod(message.getContent())
		);

		try {
			return new MessageResponse(command.execute(message), null, null);
		} catch (InvocationTargetException | IllegalAccessException e) {
			return MessageResponse.FAILED;
		}


		/*

		//최초 redis pop
		String value = redisFunction.getLastValue(message.getUser_key());
		log.debug("value : {}", value);

		if(value == null && message.getContent().equals("검색")) {
			//최초 검색입력시 redis push
			Redis redis = new Redis();
			redis.setFunction("search");
			redisFunction.push(message.getUser_key(), CustomUtil.objectToString(redis));
			return new MessageResponse("검색어를 입력하세요.", null, null);
		}

		//redis 마지막 상태값에 따라 다음 명령어가 결정된다.
		Redis redis = CustomUtil.stringToObject(value);
		String resultMessage = null;
		switch (redis.getFunction()) {
			case "search" :
				resultMessage = helloUltraFunction.search(message, redis);
				break;
			case "questionDetail" :
				resultMessage = helloUltraFunction.questionDetail(message, redis);
				break;
			case "searchAnswer" :
				resultMessage = helloUltraFunction.searchAnswer(message, redis);
				break;
			case "answerDetail" :
				break;
			case "registerAnswer" :
				resultMessage = helloUltraFunction.registerAnswer(message, redis);
				break;
			default :

				break;
		}
		log.debug("redis function : {}, param : {}", redis.getFunction(), redis.getParam().toString());

		return new MessageResponse(resultMessage, null, null);*/
	}
}
