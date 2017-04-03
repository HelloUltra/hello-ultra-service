package com.example.functions.impl;

import com.example.annotations.Command;
import com.example.functions.Function;
import com.example.model.Question;
import com.example.repository.QuestionRepository;
import com.example.utils.ContentUtils;
import com.example.utils.IndexUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by YG-MAC on 2017. 3. 11..
 */
@Component
@Transactional
public class QuestionFunction extends Function {

    private static final Logger log = LoggerFactory.getLogger(QuestionFunction.class);

    @Autowired
    private QuestionRepository questionRepository;

    @Command(value="#검색", function="search")
    public String search(String tag) {
        log.debug("#검색:{}",tag);
        List<Question> questions;
        if((questions=questionRepository.findTop3QuestionByTagName(tag)) == null || questions.size() == 0){
            return "검색 결과가 없습니다.";
        }
        return ContentUtils.convertListToMessage(questions);
    }

    @Command(value="#다음", function="nextSearch")
    public String nextSearch(String tag) {
        log.debug("#다음:{}",tag);
        return search(tag);
    }

    @Command(value="#이전", function="preSearch")
    public String preSearch(String tag) {
        log.debug("#이전:{}",tag);
        return search(tag);
    }

    @Command(parent="search", function="questionDetail")
    public String questionDetail(String questionIdx) {
        log.debug("#상세보기:{}",questionIdx);
        if(!IndexUtils.verifyIndex(questionIdx)){
            return "번호가 올바르지 않습니다.";
        }
        Question question;
        if((question=questionRepository.getQuestionDetail(Long.valueOf(questionIdx))) == null) {
            return "검색 결과가 없습니다.";
        }
        return ContentUtils.convertToMessage(question);
    }



    /*
    @Command("검색")
    public String search(String tag){
        log.debug("#검색:{}",tag);
        List<Question> questions;
        if((questions=questionRepository.findTop3QuestionByTagName(tag)) == null || questions.size() == 0){
            return "검색 결과가 없습니다.";
        }
        return ContentUtils.convertListToMessage(questions);
    }

    @Command("상세보기")
    public String questionDetail(String questionIdx){
        log.debug("#상세보기:{}",questionIdx);
        if(!IndexUtils.verifyIndex(questionIdx)){
            return "번호가 올바르지 않습니다.";
        }
        Question question;
        if((question=questionRepository.getQuestionDetail(Long.valueOf(questionIdx))) == null) {
            return "검색 결과가 없습니다.";
        }
        return ContentUtils.convertToMessage(question);
    }
    */

}
