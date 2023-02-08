package com.example.SecondHomework.services;

import java.io.IOException;

public interface AnswersReceiver {
    boolean askQuestion() throws IOException;
    AnswersReceiver EMPTY_ANSWERS_RECEIVER = () -> false;
}
