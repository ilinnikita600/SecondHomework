package com.example.SecondHomework;

import com.example.SecondHomework.Exception.ObjectAppendException;
import com.example.SecondHomework.model.Question;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;

@SpringBootTest
public class QuestionTests {
    private Question question;
    QuestionTests() {
        question = new Question();
    }

    @Test
    void addVariationOfAnswersMethodTest() throws Exception {
        ArrayList<String> testData = new ArrayList<>() {{
            add("Hello");
            add(null);
            add("");
        }};
        testData.forEach(question::addVariationOfAnswers);
        for (String s : testData) {
            if (!testData.contains(s)) throw new ObjectAppendException();
        }
    }
}
