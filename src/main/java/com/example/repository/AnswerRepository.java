package com.example.repository;

import com.example.model.Answer;
import com.example.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by user on 2017-03-08.
 */
public interface AnswerRepository extends JpaRepository<Answer, Long>{
    List<Answer> findByQuestionIdx(Question question);
}
