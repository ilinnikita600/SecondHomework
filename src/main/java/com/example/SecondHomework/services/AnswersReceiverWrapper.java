package com.example.SecondHomework.services;

import com.example.SecondHomework.annotations.Loggable;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class AnswersReceiverWrapper implements AnswersReceiver {
    @Setter
    private AnswersReceiver answersReceiver = AnswersReceiver.EMPTY_ANSWERS_RECEIVER;

    @Override
    @Loggable
    public boolean askQuestion() throws IOException {
        return answersReceiver.askQuestion();
    }
}
