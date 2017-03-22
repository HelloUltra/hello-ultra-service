package com.example.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * Created by cjh on 2017. 3. 21..
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class AnswerServiceTest {
    @Autowired
    private AnswerService answerService;

    @Test
    public void searchAnswer_내용있음() throws Exception {
        String msg = answerService.searchAnswer("1", 1);
        System.out.println("msg : " + msg );
    }

    @Test
    public void searchAnswer_내용없음() throws Exception {
        String msg = answerService.searchAnswer("99999", 1);
        assertEquals(msg, "검색 결과가 없습니다.");
    }

    @Test
    public void searchAnswer_잘못된값입력() throws Exception {
        String msg = answerService.searchAnswer("ㅁㅁ", 1);
        assertEquals(msg, "번호가 올바르지 않습니다.");
    }

    @Test
    public void answerDetail_내용있음() throws Exception {
        String msg = answerService.answerDetail("1");
        System.out.println("msg : " + msg);
    }

    @Test
    public void answerDetail_내용없음() throws Exception {
        String msg = answerService.answerDetail("99999");
        assertEquals(msg, "검색 결과가 없습니다.");
    }

    @Test
    public void answerDetail_잘못된값입력() throws Exception {
        String msg = answerService.answerDetail("ㅁㅁ");
        assertEquals(msg, "번호가 올바르지 않습니다.");
    }

    @Test
    public void registerAnswer() throws Exception {

    }

}