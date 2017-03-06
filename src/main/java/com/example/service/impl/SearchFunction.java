package com.example.service.impl;

import com.example.annotations.Command;
import com.example.model.Question;
import com.example.repository.QuestionRepository;
import com.example.service.Function;
import com.example.utils.QuestionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by YG_king on 2017-03-06.
 */
@Service
@Transactional
public class SearchFunction extends Function{
    private static final Logger log = LoggerFactory.getLogger(SearchFunction.class);

    @Autowired
    private QuestionRepository questionRepository;

    @Command("검색")
    public String search(String tag){
        log.debug("#검색:{}",tag);
        List<Question> questions;
        if((questions=questionRepository.findTop3QuestionByTagName(tag)) == null || questions.size() == 0){
            return "검색 결과가 없습니다.";
        }
        return QuestionUtils.convertListToMessage(questions);
    }
}
