package com.example.utils;

import com.example.model.Answer;

import java.util.List;

/**
 * Created by user on 2017-03-08.
 */
public class AnswerUtils {
    public static String convertListToMessage(List<Answer> answers){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(answers.get(0).getQuestionTitleInfo()).append("\n");
        answers.stream().forEach(answer -> stringBuilder.append(answer.getListAnswerInfo()).append("\n"));
        return stringBuilder.toString();
    }
}
