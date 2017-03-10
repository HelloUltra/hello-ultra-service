package com.example.repository.impl;

import com.example.model.Answer;
import com.example.model.QAnswer;
import com.example.model.QQuestion;
import com.example.repository.AnswerRepositoryQueryDsl;
import org.springframework.data.jpa.repository.support.QueryDslRepositorySupport;

import java.util.List;

/**
 * Created by cjh on 2017. 3. 10..
 */
public class AnswerRepositoryImpl extends QueryDslRepositorySupport implements AnswerRepositoryQueryDsl{
    public AnswerRepositoryImpl() {
        super(Answer.class);
    }

    //최상위 3개의 답변 리스트.
    @Override
    public List<Answer> findTop3AnswerByContent(Long question_idx) {
        QAnswer answer = QAnswer.answer;
        return from(answer)
                .where(answer.question.idx.eq(question_idx))
                .orderBy(answer.idx.desc())
                .limit(3).fetch();
    }

    //상세답변내용.
    @Override
    public Answer getAnswerDetail(Long idx) {
        QAnswer answer = QAnswer.answer;
        return from(answer).where(answer.idx.eq(idx)).fetchOne();
    }
}
