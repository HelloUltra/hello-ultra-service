package com.example.repository.impl;

import com.example.dto.Paging;
import com.example.model.QQuestion;
import com.example.model.QTag;
import com.example.model.Question;
import com.example.repository.QuestionRepositoryQueryDsl;
import org.springframework.data.jpa.repository.support.QueryDslRepositorySupport;

import java.util.List;

/**
 * Created by YG_king on 2017-03-03.
 */
public class QuestionRepositoryImpl extends QueryDslRepositorySupport implements QuestionRepositoryQueryDsl {
    public QuestionRepositoryImpl() {
        super(Question.class);
    }

    @Override
    public List<Question> findTop3QuestionByTagName(String name) {
        QQuestion question = QQuestion.question;
        QTag tag = QTag.tag;
        return from(question).innerJoin(question.tags, tag)
                .on(tag.eq(from(tag).where(tag.name.eq(name))))
                .limit(3)
                .orderBy(question.idx.desc())
                .fetch();
    }

    @Override
    public List<Question> findListQuestionByTagName(String name, Paging paging) {
        QQuestion question = QQuestion.question;
        QTag tag = QTag.tag;
        return from(question).innerJoin(question.tags, tag)
                .on(tag.eq(from(tag).where(tag.name.eq(name))))
                .orderBy(question.idx.desc())
                .limit(paging.getLimit())
                .offset(paging.getOffset())
                .fetch();
    }


    @Override
    public Question getQuestionDetail(Long idx) {
        QQuestion question = QQuestion.question;
        return from(question).where(question.idx.eq(idx)).fetchOne();

    }
}
