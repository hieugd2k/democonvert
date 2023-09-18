package com.example.demo.Domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class QuestionAnswer {
    @Id
    private String question;
    private String answer;

    public QuestionAnswer() {}

    public QuestionAnswer(String question, String answer) {
        this.question = question;
        this.answer = answer;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
