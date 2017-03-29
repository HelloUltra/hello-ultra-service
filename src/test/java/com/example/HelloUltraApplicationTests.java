
package com.example;

import com.example.dto.MessageRequest;
import com.example.model.Answer;
import com.example.model.Question;
import com.example.repository.AnswerRepository;
import com.example.repository.QuestionRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.*;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.Set;

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

	/*@Test
	@Transactional
	public void 답변등록() throws Exception {
		messageRequest.setContent("#답변등록 1");
		System.out.println(messageDispatcher.dispatch(messageRequest).getText());
	}*/

	@Before
	public void init() {
		//list put
		listOperations.rightPush("test:task", "자기소개");
		listOperations.rightPush("test:task", "취미소개");
		listOperations.rightPush("test:task", "소망소개");
		listOperations.rightPush("test:task", "선임이직");
		//hash put
		hashOperations.put("test:user:kingbbode", "name", "권뽀대");
		hashOperations.put("test:user:kingbbode", "age", "28");
		//set put
		setOperations.add("test:user:kingbbode:hobby", "개발");
		setOperations.add("test:user:kingbbode:hobby", "잠");
		setOperations.add("test:user:kingbbode:hobby", "옷 구경");
		//zset
		zSetOperations.add("test:user:kingbbode:wish", "배포한 것에 장애없길", 1);
		zSetOperations.add("test:user:kingbbode:wish", "배포한거 아니여도 장애없길", 2);
		zSetOperations.add("test:user:kingbbode:wish", "경력직 채용", 3);
		zSetOperations.add("test:user:kingbbode:wish", "잘자기", 4);
	}

	@Resource(name="redisTemplate")
	private ValueOperations<String, String> valueOperations;

	@Resource(name = "redisTemplate")
	private ListOperations<String, String> listOperations;

	@Resource(name = "redisTemplate")
	private HashOperations<String, String, String> hashOperations;

	@Resource(name = "redisTemplate")
	private SetOperations<String, String> setOperations;

	@Resource(name="redisTemplate")
	private ZSetOperations<String, String> zSetOperations;

	@Test
	public void redisTest1() {
		String task = listOperations.leftPop("test:task");
		StringBuilder stringBuilder = new StringBuilder();
		while (task != null) {
			switch (task) {
				case "자기소개":
					Map<String, String> intro = hashOperations.entries("test:user:kingbbode");
					stringBuilder.append("\n******자기소개********");
					stringBuilder.append("\n이름은 ");
					stringBuilder.append(intro.get("name"));
					stringBuilder.append("\n나이는 ");
					stringBuilder.append(intro.get("age"));
					break;
				case "취미소개":
					Set<String> hobbys = setOperations.members("test:user:kingbbode:hobby");
					stringBuilder.append("\n******취미소개******");
					stringBuilder.append("취미는");
					for (String hobby : hobbys) {
						stringBuilder.append("\n");
						stringBuilder.append(hobby);
					}
					break;
				case "소망소개":
					Set<String> wishes = zSetOperations.range("test:user:kingbbode:wish", 0, 2);
					stringBuilder.append("\n******소망소개******");
					int rank = 1;
					for (String wish : wishes){
						stringBuilder.append("\n");
						stringBuilder.append(rank);
						stringBuilder.append("등 ");
						stringBuilder.append(wish);
						rank++;
					}
					break;
				case "선임이직":
					stringBuilder.append("\n!!! 믿었던 선임 이직");
					zSetOperations.incrementScore("test:user:kingbbode:wish", "경력직 채용", -1);
					listOperations.rightPush("test:task", "소망소개");
					break;
				default:
					stringBuilder.append("nonone");

			}
			task = listOperations.leftPop("test:task");
		}
		System.out.println(stringBuilder.toString());
	}
}

