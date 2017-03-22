package com.example.service;

import com.example.dto.Paging;
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
public class QuestionServiceTest {

    @Autowired
    private QuestionService questionService;

    @Test
    public void search_내용있음() throws Exception {
        String msg = questionService.search("테스트", 1);
        System.out.println("msg : " + msg);
    }
    @Test
    public void search_내용없음() throws Exception {
        String msg = questionService.search("없는tag명입니다", 1);
        assertEquals(msg, "검색 결과가 없습니다.");
    }
    @Test
    public void search_페이지없음() throws Exception {
        String msg = questionService.search("테스트", 99);
        assertEquals(msg, "검색 결과가 없습니다.");
    }

    @Test
    public void questionDetail_내용있음() throws Exception {
        String msg = questionService.questionDetail("1");
        System.out.println("msg : " + msg);
    }

    @Test
    public void questionDetail_내용없음() throws Exception {
        String msg = questionService.questionDetail("9999");
        assertEquals(msg, "검색 결과가 없습니다.");
    }

    @Test
    public void questionDetail_잘못된값입력() throws Exception {
        String msg = questionService.questionDetail("ㅁㅁ");
        assertEquals(msg, "번호가 올바르지 않습니다.");
    }

}