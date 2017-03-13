package com.example.service.impl;

import com.example.annotations.Command;
import com.example.model.Answer;
import com.example.model.Question;
import com.example.repository.AnswerRepository;
import com.example.repository.QuestionRepository;
import com.example.repository.UserRepository;
import com.example.service.Function;
import com.example.utils.AnswerUtils;
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
    @Autowired
    private AnswerRepository answerRepository;
    @Autowired
    private UserRepository userRepository;

    @Command("검색")
    public String search(String tag){
        log.debug("#검색:{}",tag);
        List<Question> questions;
        if((questions=questionRepository.findTop3QuestionByTagName(tag)) == null || questions.size() == 0){
            return "검색 결과가 없습니다.";
        }
        return QuestionUtils.convertListToMessage(questions);
    }

    @Command("상세보기")
    public String questionDetail(String question_idx){
        log.debug("#상세보기:{}",question_idx);
        Question question;
        if((question=questionRepository.getQuestionDetail(Long.valueOf(question_idx))) == null) {
            return "검색 결과가 없습니다.";
        }
        return question.getQuestionDetailInfo();
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

    @Command("답변검색")
    public String searchAnswer(String question_idx) {
        log.debug("#답변검색:{}",question_idx);
        List<Answer> answers;
        if((answers = answerRepository.findTop3AnswerByContent(Long.valueOf(question_idx))) == null || answers.size() == 0) {
            return "검색 결과가 없습니다.";
        }
        return AnswerUtils.convertListToMessage(answers);
    }

    @Command("답변상세보기")
    public String answerDetail(String answer_idx) {
        log.debug("#답변상세보기:{}",answer_idx);
        Answer answer;
        if((answer = answerRepository.getAnswerDetail(Long.valueOf(answer_idx))) == null ) {
            return "검색 결과가 없습니다.";
        }
        return answer.getAnswerDetailInfo();
    }
}
