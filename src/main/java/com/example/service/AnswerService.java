
/**
 * Created by YG-MAC on 2017. 3. 11..
 */


package com.example.service;

import com.example.annotations.Command;
import com.example.dto.Paging;
import com.example.functions.impl.AnswerFunction;
import com.example.model.Answer;
import com.example.repository.AnswerRepository;
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
public class AnswerService {

    private static final Logger log = LoggerFactory.getLogger(AnswerFunction.class);

    @Autowired
    private AnswerRepository answerRepository;

    public String searchAnswer(String questionIdx, Integer pageNum) {
        if(!IndexUtils.verifyIndex(questionIdx)){
            return "번호가 올바르지 않습니다.";
        }
        List<Answer> answers;
        if((answers = answerRepository.findListAnswerByContent(Long.valueOf(questionIdx), new Paging(pageNum))) == null || answers.size() == 0) {
            return "검색 결과가 없습니다.";
        }
        return ContentUtils.convertListToMessage(answers);
    }


    public String answerDetail(String answerIdx) {
        if(!IndexUtils.verifyIndex(answerIdx)){
            return "번호가 올바르지 않습니다.";
        }
        Answer answer;
        if((answer = answerRepository.getAnswerDetail(Long.valueOf(answerIdx))) == null ) {
            return "검색 결과가 없습니다.";
        }
        return ContentUtils.convertToMessage(answer);
    }


    public String registerAnswer(String questionIdx){
        if(!IndexUtils.verifyIndex(questionIdx)){
            return "번호가 올바르지 않습니다.";
        }
        Answer answer;

        return null;
    }
}
