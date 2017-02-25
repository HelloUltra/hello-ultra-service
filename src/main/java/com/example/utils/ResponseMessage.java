package com.example.utils;

import java.util.Map;

import com.example.model.Message;

public class ResponseMessage {
	public static Map<String, Object> response(Message message, String answer) {
		return JsonConverter.makeObject(message, Answers.answer(message));
	}
}
