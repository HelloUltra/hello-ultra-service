package com.example.repository;

import com.example.dto.Paging;
import com.example.model.Question;

import java.util.List;

/**
 * Created by YG_king on 2017-03-03.
 */
public interface QuestionRepositoryQueryDsl {
    List<Question> findTop3QuestionByTagName(String tagName);

    List<Question> findListQuestionByTagName(String tagName, Paging paging);

    Question getQuestionDetail(Long idx);
}
