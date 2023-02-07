package com.example.SecondHomework;

import com.example.SecondHomework.model.Question;
import com.example.SecondHomework.services.AnswersReceiver;
import com.example.SecondHomework.services.AnswersReceiverImpl;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTestContextBootstrapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;

@SpringBootTest
public class AnswersReceiverTests {
    private AnswersReceiver answersReceiver;
    private ApplicationTestProps applicationTestProps;
    @Autowired
    AnswersReceiverTests(ApplicationTestProps applicationTestProps) {
        this.applicationTestProps = applicationTestProps;
        initial();
    }

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
        answersReceiver = new AnswersReceiverImpl(questions.iterator(), inputStream, System.out);
    }

    @Test
    void askQuestionTest() {
        String res;
        try {
            while(answersReceiver.askQuestion());
            res = "Successfully";
        } catch (IOException e) {
            res = e.getMessage();
        }

        Assertions.assertEquals("Successfully",res, res);
    }
}
