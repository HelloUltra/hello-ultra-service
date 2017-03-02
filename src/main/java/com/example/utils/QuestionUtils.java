package com.example.utils;

import com.example.model.Question;

import java.util.List;

/**
 * Created by YG_king on 2017-03-03.
 */
public class QuestionUtils {
    public static String convertListToMessage(List<Question> questions){
        StringBuilder stringBuilder = new StringBuilder();
        questions.stream().forEach(question -> stringBuilder.append(question).append("\n"));
        return stringBuilder.toString();
    }

    public static Boolean isEmpty(List<Question> questions){
        return questions.iterator().next() == null;
    }
}
