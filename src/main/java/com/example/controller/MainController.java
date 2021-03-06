package com.example.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.dispatcher.MessageDispatcher;
import com.example.dto.KeyBoardResponse;
import com.example.dto.MessageRequest;
import com.example.dto.MessageResponse;

@RestController
public class MainController {
	@Autowired
	private MessageDispatcher messageDispatcher;
	
	private static final Logger log = LoggerFactory.getLogger(MainController.class);

	@GetMapping("/keyboard")
	public KeyBoardResponse keyboard() {
		return KeyBoardResponse.DEFAULT;
	}

	@PostMapping("/message")
	public MessageResponse message(@RequestBody MessageRequest message)  {
		return messageDispatcher.dispatch(message);
	}


	//TODO 미구현.
	@PostMapping("/talkmessage")
	public MessageResponse talkmessage(@RequestBody MessageRequest message)  {
		return messageDispatcher.dispatch(message);
	}
}
