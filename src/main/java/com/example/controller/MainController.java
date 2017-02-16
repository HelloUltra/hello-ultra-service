package com.example.controller;

import java.util.HashMap;
import java.util.Map;

import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.Message;
import com.example.model.User;
import com.example.repository.UserRepository;
import com.example.service.JsonObject;

@RestController
public class MainController {

	@Autowired
	private UserRepository userRepository;

	private static final Logger log = LoggerFactory.getLogger(MainController.class);

	@GetMapping("/keyboard")
	public Map<String, Object> keyboard() {
		Map<String, Object> jsonObject = new HashMap<String, Object>();
		jsonObject.put("type", "text");
		return jsonObject;
	}

	@PostMapping("/message")
	public Object message(@RequestBody Message message) throws ParseException {

		System.out.println("메세지받음");

		log.debug(message.toString());

		JsonObject jsonObject = new JsonObject();
		Object result = jsonObject.sendObject(message);
		
		//회원가입
		if (message.getContent().startsWith("@")) {

			User user = new User();
			user.setUser_key(message.getUser_key());
			user.setNickName(message.getContent().substring(1));
			userRepository.save(user);
		}
		return result;
	}
}
