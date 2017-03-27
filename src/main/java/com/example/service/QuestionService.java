package com.example.service;

import com.example.annotations.Command;
import com.example.dto.Paging;
import com.example.message.Message;
import com.example.model.Question;
import com.example.repository.QuestionRepository;
import com.example.utils.ContentUtils;
import com.example.utils.IndexUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by YG-MAC on 2017. 3. 11..
 */
@Service
public class QuestionService {

    private static final Logger log = LoggerFactory.getLogger(QuestionService.class);

    @Autowired
    private QuestionRepository questionRepository;

    public String search(){
        return Message.EMPTY_SEARCH_MESSAGE;
    }

    public String search(String content, Integer pageNum){
        log.debug("검색:{}, page:{}",content, pageNum);
        List<Question> questions;
        if((questions = questionRepository.findListQuestionByTagName(content, new Paging(pageNum))) == null || questions.size() == 0){
            return Message.NO_DB_DATA;
        }
        return ContentUtils.convertListToMessage(questions);
    }

    public String questionDetail(String questionIdx){
        if(!IndexUtils.verifyIndex(questionIdx)){
            return Message.WRONG_NUMBER;
        }
        Question question;
        if((question=questionRepository.getQuestionDetail(Long.valueOf(questionIdx))) == null) {
            return Message.NO_DB_DATA;
        }
        return ContentUtils.convertToMessage(question);
    }
}
