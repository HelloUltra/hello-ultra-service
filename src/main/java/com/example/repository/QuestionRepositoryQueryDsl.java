package com.example.repository;

import com.example.model.Question;

import java.util.List;

/**
 * Created by YG_king on 2017-03-03.
 */
public interface QuestionRepositoryQueryDsl {
    List<Question> findTop3QuestionByTagName(String tagName);

    Question getQuestionDetail(Long idx);
}
