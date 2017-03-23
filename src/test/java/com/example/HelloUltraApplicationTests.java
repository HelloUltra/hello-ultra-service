package com.example;

import com.example.dto.MessageRequest;
import com.example.dto.Paging;
import com.example.model.Answer;
import com.example.model.Question;
import com.example.repository.AnswerRepository;
import com.example.repository.QuestionRepository;
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

	@Autowired
	private AnswerRepository answerRepository;

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
		assertEquals(3, questionList.size());
		questionList.stream().forEach(System.out::println);
	}

	@Test
	@Transactional
	public void QueryDSL_검색_테스트_페이징() throws Exception {
		Paging paging = new Paging(1);
		List<Question> questionList = questionRepository.findListQuestionByTagName("테스트", paging);
		assertEquals(3, questionList.size());

		for(Question question : questionList) {
			System.out.println(question.toShortString());
		}
	}

	@Test
	@Transactional
	public void QueryDSL_검색_테스트_결과_없음() throws Exception {
		List<Question> questionList = questionRepository.findTop3QuestionByTagName("없는검색어");
		assertEquals(0, questionList.size());
	}

	@Test
	public void Dispatcher_상세보기() throws Exception {
		messageRequest.setContent("#상세보기 1");
		System.out.println(messageDispatcher.dispatch(messageRequest).getText());
	}

	@Test
	@Transactional
	public void QueryDSL_게시물_상세보기_결과_있음() throws Exception {
		Question question = questionRepository.getQuestionDetail(1L);
		assertNotNull(question);
	}

	@Test
	@Transactional
	public void QueryDSL_게시물_상세보기_결과_없음() throws Exception {
		Question question = questionRepository.getQuestionDetail(100L);
		assertNull(question);
	}

	@Test
	public void Dispatcher_답변검색() throws Exception {
		messageRequest.setContent("#답변검색 1");
		System.out.println(messageDispatcher.dispatch(messageRequest).getText());
	}

	@Test
	@Transactional
	public void QueryDSL_답변_검색_상위_3개() throws Exception {
		List<Answer> answerList = answerRepository.findTop3AnswerByContent(1L);
		assertEquals(3, answerList.size());
		answerList.stream().forEach(System.out::println);
	}

	@Test
	@Transactional
	public void QueryDSL_답변_검색_결과_없음() throws Exception {
		List<Answer> answerList = answerRepository.findTop3AnswerByContent(100L);
		assertEquals(0, answerList.size());
	}

	@Test
	public void Dispatcher_답변_상세보기() throws Exception {
		messageRequest.setContent("#답변상세보기 1");
		System.out.println(messageDispatcher.dispatch(messageRequest).getText());
	}

	@Test
	public void Redis_답변등록() throws Exception {
		//이후 입력어 구조 수정
		messageRequest.setContent("#답변등록 1");
		System.out.println(messageDispatcher.dispatch(messageRequest).getText());
	}

	@Test
	@Transactional
	public void QueryDSL_답변_상세보기_결과_있음() throws Exception {
		Answer answer = answerRepository.getAnswerDetail(1L);
		assertNotNull(answer);
	}

	@Test
	@Transactional
	public void QueryDSL_답변_상세보기_결과_없음() throws Exception {
		Answer answer = answerRepository.getAnswerDetail(100L);
		assertNull(answer);
	}




	//redis test 진행.
	//redis 명령어. key * //모든 key 리스트 보기.
	//            lrange 123 0 100 //key : 123, 0 ~ 100 까지 리스트 보기.
	//            del 123 // key : 123 삭제.
	@Test
	public void Dispatcher_Redis_검색() throws Exception {
		messageRequest.setContent("검색");
		messageRequest.setUser_key("123");
		System.out.println(messageDispatcher.redisDispatch(messageRequest).getText());
//		1) "{\"function\":\"search\",\"param\":{}}"
	}

	@Test
	public void Dispatcher_Redis_단어검색() throws Exception {
		messageRequest.setContent("테스트");
		messageRequest.setUser_key("123");
		System.out.println(messageDispatcher.redisDispatch(messageRequest).getText());
//		1) "{\"function\":\"search\",\"param\":{}}"
//		2) "{\"function\":\"search\",\"param\":{\"page\":\"1\",\"content\":\"\xed\x85\x8c\xec\x8a\xa4\xed\x8a\xb8\"}}"
	}

	@Test
	public void Dispatcher_Redis_더보기() throws Exception {
		messageRequest.setContent("더보기");
		messageRequest.setUser_key("123");
		System.out.println(messageDispatcher.redisDispatch(messageRequest).getText());
//		1) "{\"function\":\"search\",\"param\":{}}"
//		2) "{\"function\":\"search\",\"param\":{\"page\":\"2\",\"content\":\"\xed\x85\x8c\xec\x8a\xa4\xed\x8a\xb8\"}}"
	}

	@Test
	public void Dispatcher_Redis_뒤로가기() throws Exception {
		messageRequest.setContent("뒤로가기");
		messageRequest.setUser_key("123");
		System.out.println(messageDispatcher.redisDispatch(messageRequest).getText());
//		1) "{\"function\":\"search\",\"param\":{}}"
//		2) "{\"function\":\"search\",\"param\":{\"page\":\"1\",\"content\":\"\xed\x85\x8c\xec\x8a\xa4\xed\x8a\xb8\"}}"
	}

	@Test
	public void Dispatcher_Redis_상세보기() throws Exception {
		messageRequest.setContent("1");
		messageRequest.setUser_key("123");
		System.out.println(messageDispatcher.redisDispatch(messageRequest).getText());
//		1) "{\"function\":\"search\",\"param\":{}}"
//		2) "{\"function\":\"search\",\"param\":{\"page\":\"1\",\"content\":\"\xed\x85\x8c\xec\x8a\xa4\xed\x8a\xb8\"}}"
//		3) "{\"function\":\"questionDetail\",\"param\":{\"questionIdx\":\"1\"}}"
	}
}

