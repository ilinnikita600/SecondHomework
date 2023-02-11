package com.example.SecondHomework;

import com.example.SecondHomework.model.Question;
import com.example.SecondHomework.model.Result;
import com.example.SecondHomework.services.AnswersReceiver;
import com.example.SecondHomework.services.AnswersReceiverImpl;
import com.example.SecondHomework.services.ResultBuilder;

import jakarta.annotation.PostConstruct;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import java.io.IOException;
import java.io.InputStream;

import java.util.ArrayList;

@SpringBootTest
public class AnswersReceiverTests {
    private AnswersReceiver answersReceiver;
    private ApplicationTestProps applicationTestProps;
    private ApplicationContext applicationContext;
    private ResultBuilder resultBuilder;
    @Autowired
    AnswersReceiverTests(ApplicationTestProps applicationTestProps, ApplicationContext applicationContext, ResultBuilder resultBuilder) {
        this.applicationTestProps = applicationTestProps;
        this.applicationContext = applicationContext;
        this.resultBuilder = resultBuilder;
    }
    @PostConstruct
    private void initial() {
        ArrayList<Question> questions = new ArrayList<>();
        Question question = new Question();
        question.setQuestionId(1);
        question.setQuestion("Что такое AspectJ?");
        questions.add(question);

        Question question1 = new Question();
        question1.setQuestionId(2);
        question1.setQuestion("В каком году Байден стал президентом?");
        question1.setVariationsOfAnswers(new ArrayList<>() {{add("2020"); add("2021"); add("2002"); add("2019");}});
        question1.setCorrectAnswer("2021");
        questions.add(question1);


        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(applicationTestProps.getTestAnswersPath());
        AnswersReceiverImpl answersReceiverImpl = (AnswersReceiverImpl) applicationContext.getBean(AnswersReceiver.class);
        answersReceiverImpl.setQuestionsIterator(questions.iterator());
        answersReceiverImpl.setInput(inputStream);

        answersReceiver = answersReceiverImpl;
    }
    @Test
    void askQuestionTest() {
        String message;
        try {
            while(answersReceiver.askQuestion());
            AnswersReceiverImpl ari = (AnswersReceiverImpl) answersReceiver;
            Result result = resultBuilder.getResult(ari.getQuestionsAsked(), ari.getAnswers());
            message =  result.getCorrectAnswers() == 2 ? "Successfully" : "Not correct read from file";
        } catch (IOException e) {
            message = e.getMessage();
        }

        Assertions.assertEquals("Successfully",message, message);
    }
}
