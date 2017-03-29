package com.example.repository;

import com.example.dto.Paging;
import com.example.model.Answer;

import java.util.List;

/**
 * Created by cjh on 2017. 3. 10..
 */
public interface AnswerRepositoryQueryDsl {
    List<Answer> findTop3AnswerByContent(Long question_idx);

    List<Answer> findListAnswerByContent(Long question_idx, Paging paging);

    Answer getAnswerDetail(Long idx);
}
