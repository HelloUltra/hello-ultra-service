package com.example;

import com.example.dto.MessageRequest;
import com.example.model.Question;
import com.example.model.Tag;
import com.example.repository.QuestionRepository;
import com.example.repository.TagRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class HelloUltraApplicationTests {

	@Autowired
	private MessageDispatcher messageDispatcher;

	@Autowired
	private QuestionRepository questionRepository;

	private MessageRequest messageRequest;

	@Before
	public void setUp() throws Exception {
		messageRequest = new MessageRequest();
	}

	@Test
	public void Dispatcher_테스트() throws Exception {
		messageRequest.setContent("#검색 테스트");
		System.out.println(messageDispatcher.dispatch(messageRequest).getText());
	}

	@Test
	@Transactional
	public void QueryDSL_검색_테스트_상위_3개() throws Exception {
		List<Question> questionList = questionRepository.findTop3QuestionByTagName("테스트");
		assertNotEquals(questionList.size(), 0);
		questionList.stream().forEach(System.out::println);
	}

	@Test
	@Transactional
	public void QueryDSL_검색_테스트_결과_없음() throws Exception {
		List<Question> questionList = questionRepository.findTop3QuestionByTagName("없는검색어");
		assertEquals(questionList.size(), 1);
		assertEquals(questionList.get(0), null);
	}
}
