package com.example.functions.impl;

import com.example.annotations.Command;
import com.example.functions.Function;
import com.example.model.Answer;
import com.example.repository.AnswerRepository;
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
public class AnswerFunction extends Function {

    private static final Logger log = LoggerFactory.getLogger(AnswerFunction.class);

    @Autowired
    private AnswerRepository answerRepository;

    @Command("답변검색")
    public String searchAnswer(String questionIdx) {
        log.debug("#답변검색:{}",questionIdx);
        if(!IndexUtils.verifyIndex(questionIdx)){
            return "번호가 올바르지 않습니다.";
        }
        List<Answer> answers;
        if((answers = answerRepository.findTop3AnswerByContent(Long.valueOf(questionIdx))) == null || answers.size() == 0) {
            return "검색 결과가 없습니다.";
        }
        return ContentUtils.convertListToMessage(answers);
    }

    @Command("답변상세보기")
    public String answerDetail(String answerIdx) {
        log.debug("#답변상세보기:{}",answerIdx);
        if(!IndexUtils.verifyIndex(answerIdx)){
            return "번호가 올바르지 않습니다.";
        }
        Answer answer;
        if((answer = answerRepository.getAnswerDetail(Long.valueOf(answerIdx))) == null ) {
            return "검색 결과가 없습니다.";
        }
        return ContentUtils.convertToMessage(answer);
    }

    //    @Command("답변검색")
    //    public String searchAnswer(String id){
    //        List <Answer> answers;
    //        Long questionId;
    //        questionId = Long.parseLong(id);
    //        Question question = questionRepository.findOne(questionId);
    //        log.debug("#답변검색:{}",question.getIdx());
    //        answers = answerRepository.findByQuestionIdx(question.getIdx());
    //        if(answers==null || answers.size()==0){
    //            return "검색 결과가 없습니다.";
    //        }
    //        return AnswerUtils.convertListToMessage(answers);
    //    }
}
