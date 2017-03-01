package com.example;

import com.example.dto.MessageRequest;
import com.example.repository.TagRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class HelloUltraApplicationTests {

	@Autowired
	private MessageDispatcher dispatcher;

	private MessageRequest messageRequest;

	@Before
	public void setUp() throws Exception {
		messageRequest = new MessageRequest();
	}

	@Test
	public void Dispatcher_검색기능_테스트() throws Exception {
		messageRequest.setContent("#검색 spring");
		System.out.println(dispatcher.dispatch(messageRequest).getText());
	}
}
