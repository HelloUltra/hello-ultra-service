
/**
 * Created by YG-MAC on 2017. 3. 11..
 */


package com.example.service;

import com.example.annotations.Command;
import com.example.dto.MessageRequest;
import com.example.dto.Paging;
import com.example.functions.impl.AnswerFunction;
import com.example.message.Message;
import com.example.model.Answer;
import com.example.repository.AnswerRepository;
import com.example.repository.QuestionRepository;
import com.example.repository.UserRepository;
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

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private QuestionRepository questionRepository;

    public String searchAnswer(String questionIdx, Integer pageNum) {
        if(!IndexUtils.verifyIndex(questionIdx)){
            return Message.WRONG_NUMBER;
        }
        List<Answer> answers;
        if((answers = answerRepository.findListAnswerByContent(Long.valueOf(questionIdx), new Paging(pageNum))) == null || answers.size() == 0) {
            return Message.NO_DB_DATA;
        }
        return ContentUtils.convertListToMessage(answers);
    }


    public String answerDetail(String answerIdx) {
        if(!IndexUtils.verifyIndex(answerIdx)){
            return Message.WRONG_NUMBER;
        }
        Answer answer;
        if((answer = answerRepository.getAnswerDetail(Long.valueOf(answerIdx))) == null ) {
            return Message.NO_DB_DATA;
        }
        return ContentUtils.convertToMessage(answer);
    }


    public String registerAnswer(String questionIdx, MessageRequest message){
        if(!IndexUtils.verifyIndex(questionIdx)){
            return Message.WRONG_NUMBER;
        }
        Answer answer = new Answer();
        answer.setWriter(userRepository.findByUserKey(message.getUser_key()));
        answer.setContent(message.getContent());
        answer.setQuestion(questionRepository.findOne(Long.valueOf(questionIdx)));
        log.debug(answer.toString());
        answerRepository.save(answer);

        return "답변이 정상적으로 등록되었습니다";
    }
}
