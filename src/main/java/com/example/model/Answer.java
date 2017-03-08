package com.example.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Answer {

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

}
