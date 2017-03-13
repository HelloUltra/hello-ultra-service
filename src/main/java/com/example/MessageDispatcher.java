package com.example;

import com.example.annotations.Command;
import com.example.dto.MessageRequest;
import com.example.dto.MessageResponse;
import com.example.functions.Function;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

@Component
public class MessageDispatcher {
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
}
