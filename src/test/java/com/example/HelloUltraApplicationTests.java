package com.example;

import com.example.dto.MessageRequest;
import com.example.model.Tag;
import com.example.repository.TagRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class HelloUltraApplicationTests {

	@Autowired
	private MessageDispatcher messageDispatcher;

	@Autowired
	private TagRepository tagRepository;

	private MessageRequest messageRequest;

	@Before
	public void setUp() throws Exception {
		messageRequest = new MessageRequest();
	}

	@Test
	@Transactional
	public void 검색_기능_테스트() throws Exception {
		Tag tag = tagRepository.findByName("테스트");
		assertNotEquals(tag, null);
		System.out.println(tag.getSearchResult());
	}

	@Test
	public void Dispatcher_테스트() throws Exception {
		messageRequest.setContent("#검색 테스트");
		System.out.println(messageDispatcher.dispatch(messageRequest).getText());
	}
}
