package com.example.SecondHomework.services;

import com.example.SecondHomework.model.Question;

import java.io.IOException;
import java.util.List;

public interface AnswersReceiver {
    boolean askQuestion() throws IOException;
    default List<Question> getQuestionsAsked() { throw new UnsupportedOperationException();}
    default List<String> getAnswers() {throw new UnsupportedOperationException();}
}
