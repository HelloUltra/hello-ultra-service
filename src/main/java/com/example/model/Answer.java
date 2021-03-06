package com.example.model;

import javax.persistence.*;

@Entity
public class Answer extends Content {

    @Id
    @GeneratedValue
    private Long idx;

    @ManyToOne
    @JoinColumn(foreignKey=@ForeignKey(name="fk_answer_writer"))
    private User writer;

    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(name="fk_answer_question"))
    private Question question;

    private String content;
/*
    public String getQuestionTitleInfo() {
        return question.getQuestionDetailInfo().split("\n")[0];
    }*/

    public void setContent(String content) {
        this.content = content;
    }

    public void setWriter(User writer) {
        this.writer = writer;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    @Override
    public String toString() {
        return "Answer{" +
                "idx=" + idx +
                ", writer=" + writer +
                ", question=" + question +
                ", content='" + content + '\'' +
                '}';
    }

    @Override
    public String toShortString() {
        return "[" + idx + "] " + content;
    }

    @Override
    public String toDetailString() {
        return "[" + idx + "]" + " : "  + content;
    }
}
