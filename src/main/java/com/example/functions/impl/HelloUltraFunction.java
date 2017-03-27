package com.example.functions.impl;

import com.example.dto.MessageRequest;
import com.example.dto.Param;
import com.example.model.Redis;
import com.example.service.AnswerService;
import com.example.service.QuestionService;
import com.example.utils.CustomUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by cjh on 2017. 3. 23..
 */
@Component
public class HelloUltraFunction {

    private static final Logger log = LoggerFactory.getLogger(HelloUltraFunction.class);

    @Autowired
    private RedisFunction redisFunction;

    @Autowired
    private QuestionService questionService;

    @Autowired
    private AnswerService answerService;

    //검색
    public String search(MessageRequest message, Redis redis) {
        log.debug("HelloUltraFunction search start");
        log.debug("message : {}, redisFunction : {}, redisParam : {}", message.getContent(), redis.getFunction(), redis.getParam().toString());
        String resultMsg = null;
        //param 이 비어있을경우 검색 준비상태
        if(redis.getParam().isEmpty()) {
            resultMsg = questionService.search(message.getContent(), 1);
            if(!resultMsg.equals("검색 결과가 없습니다.")) {
                //검색결과가 있으면 redis push 후 종료.
                Redis rs = CustomUtil.paramToObject("search"
                        , new Param("content", message.getContent())
                        , new Param("page", "1"));
                redisFunction.push(message.getUser_key(), CustomUtil.objectToString(rs));
            }
            return resultMsg;
        }

        //search 파라미터가 있을경우. 다음 액션이 상세보기, 더보기, 뒤로가기가 있다.
        switch (message.getContent()) {
            case "더보기" :
                log.debug("더보기 진행");
                //페이지 +1 진행 후 검색, 레디스 적재.
                int addPage = Integer.valueOf(redis.getParam().get("page")) + 1;
                resultMsg = questionService.search(redis.getParam().get("content"), addPage);

                if(resultMsg.equals("검색 결과가 없습니다.")) {
                    break;
                }

                //검색결과 있을경우 +1 page 다시 적재.
                redis.getParam().put("page", String.valueOf(addPage));
                redisFunction.pop(message.getUser_key());
                redisFunction.push(message.getUser_key(), CustomUtil.objectToString(redis));
                //정상일경우
                //key : userKey, value : [0] { "function": "search", "param": {  } }
                //                       [1] { "function": "search", "param": { "content": "스프링부트", "page" : "2" } }

                break;
            case "뒤로가기" :
                log.debug("뒤로가기 진행");
                //페이지 +1 진행 후 검색, 레디스 적재.
                if(Integer.valueOf(redis.getParam().get("page")) < 2) {
                    resultMsg ="뒤로이동 할 수 없습니다.";
                    break;
                }

                int delPage = Integer.valueOf(redis.getParam().get("page")) - 1;
                resultMsg = questionService.search(redis.getParam().get("content"), delPage);

                if(resultMsg.equals("검색 결과가 없습니다.")) {
                    break;
                }
                //검색결과 있을경우 -1 page 적재.
                redis.getParam().put("page", String.valueOf(delPage));
                redisFunction.pop(message.getUser_key());
                redisFunction.push(message.getUser_key(), CustomUtil.objectToString(redis));
                //정상일경우
                //key : userKey, value : [0] { "function": "search", "param": {  } }
                //                       [1] { "function": "search", "param": { "content": "스프링부트", "page" : "1" } }

                break;
            default :
                //질문 상세보기 진행.
                log.debug("상세보기 진행");
                resultMsg = questionService.questionDetail(message.getContent());
               if("검색 결과가 없습니다.".equals(resultMsg) || "번호가 올바르지 않습니다.".equals(resultMsg)) {
                    break;
                }
                //검색결과가 있을경우 redis push 기존 value 와 함께
                Redis rs = CustomUtil.paramToObject("questionDetail", new Param("questionIdx", message.getContent()));
                redisFunction.push(message.getUser_key(), CustomUtil.objectToString(rs));
                //정상일경우
                //key : userKey, value : [0] { "function": "search", "param": {  } }
                //                       [1] { "function": "search", "param": { "content": "스프링부트", "page" : "1" } }
                //                       [2] { "function": "questionDetail", "param": { "questionIdx": "1" } }

        }
        return resultMsg;
    }

    //질문상세보기
    public String questionDetail(MessageRequest message, Redis redis) {
        log.debug("HelloUltraFunction questionDetail start");
        log.debug("message : {}, redisFunction : {}, redisParam : {}", message.getContent(), redis.getFunction(), redis.getParam().toString());

        String resultMsg = null;

        //전 상태값이 questionDetail 일경우 답변검색 입력가능
        switch (message.getContent()) {
            case "답변검색" :
                log.debug("답변검색 진행");

                resultMsg = answerService.searchAnswer(redis.getParam().get("questionIdx"), 1);

                if(resultMsg.equals("검색 결과가 없습니다.") || resultMsg.equals("번호가 올바르지 않습니다.")) {
                    break;
                }

                //검색결과가 있을경우 redis push
                Redis rs = CustomUtil.paramToObject("searchAnswer"
                        , new Param("questionIdx", redis.getParam().get("questionIdx"))
                        , new Param("page", "1"));
                redisFunction.push(message.getUser_key(), CustomUtil.objectToString(rs));

                break;

            default :
                //resultMsg = null;
        }
        return resultMsg;
    }

    //답변검색
    public String searchAnswer(MessageRequest message, Redis redis) {
        log.debug("HelloUltraFunction searchAnswer start");
        log.debug("message : {}, redisFunction : {}, redisParam : {}", message.getContent(), redis.getFunction(), redis.getParam().toString());
        String resultMsg = null;

        //다음 액션이 상세보기, 더보기, 뒤로가기가 있다.
        switch (message.getContent()) {
            case "더보기" :
                log.debug("더보기 진행");
                //페이지 +1 진행 후 검색, 레디스 적재.
                int addPage = Integer.valueOf(redis.getParam().get("page")) + 1;
                resultMsg = answerService.searchAnswer(redis.getParam().get("content"), addPage);

                if(resultMsg.equals("검색 결과가 없습니다.")) {
                    break;
                }

                //검색결과 있을경우 +1 page 다시 적재.
                redis.getParam().put("page", String.valueOf(addPage));
                redisFunction.pop(message.getUser_key());
                redisFunction.push(message.getUser_key(), CustomUtil.objectToString(redis));

                break;
            case "뒤로가기" :
                log.debug("뒤로가기 진행");
                //페이지 +1 진행 후 검색, 레디스 적재.
                if(Integer.valueOf(redis.getParam().get("page")) < 2) {
                    resultMsg ="뒤로이동 할 수 없습니다.";
                    break;
                }

                int delPage = Integer.valueOf(redis.getParam().get("page")) - 1;
                resultMsg = answerService.searchAnswer(redis.getParam().get("content"), delPage);

                if(resultMsg.equals("검색 결과가 없습니다.")) {
                    break;
                }
                //검색결과 있을경우 -1 page 적재.
                redis.getParam().put("page", String.valueOf(delPage));
                redisFunction.pop(message.getUser_key());
                redisFunction.push(message.getUser_key(), CustomUtil.objectToString(redis));
                break;
            default :
                //질문 상세보기 진행.
                log.debug("상세보기 진행");
                resultMsg = answerService.answerDetail(message.getContent());
                if("검색 결과가 없습니다.".equals(resultMsg) || "번호가 올바르지 않습니다.".equals(resultMsg)) {
                    break;
                }
                //검색결과가 있을경우 redis push 기존 value 와 함께
                Redis rs = CustomUtil.paramToObject("answerDetail", new Param("answerIdx", message.getContent()));
                redisFunction.push(message.getUser_key(), CustomUtil.objectToString(rs));
        }
        return resultMsg;
    }

    //답변상세보기
//    public String answerDetail() {
//        return null;
//    }

}
