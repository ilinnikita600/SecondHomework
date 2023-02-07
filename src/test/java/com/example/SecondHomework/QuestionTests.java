package com.example.SecondHomework;

import com.example.SecondHomework.model.Question;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;

@SpringBootTest
public class QuestionTests {
    private final Question question;
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
        Assertions.assertThat(question.getVariationsOfAnswers()).containsAll(testData);
    }
}
