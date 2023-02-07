package com.example.SecondHomework;

import com.example.SecondHomework.model.Question;
import com.example.SecondHomework.model.Result;
import com.example.SecondHomework.services.ResultBuilder;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.InvalidPropertiesFormatException;
import java.util.List;

@SpringBootTest
public class ResultBuilderTest {
    @Autowired
    private ResultBuilder resultBuilder;

    @Test
    void getResultTest() throws InvalidPropertiesFormatException {
        Result result = resultBuilder.getResult(getQuestions(), getAnswers());
        result.getQuestionResult().forEachRemaining(a -> Assertions.assertThat(a.getValue()).isIn("Test","Hello","World"));
        try {
            resultBuilder.getResult(new ArrayList<>(), new ArrayList<>() {{add("Hello");}});
            Assertions.fail("Size of questions and answers list is not equal");
        } catch (InvalidPropertiesFormatException e) {
        }
    }

    private List<Question> getQuestions() {
        ArrayList<Question> list = new ArrayList<>();
        list.add(new Question());
        list.add(new Question());
        list.add(new Question());
        return list;
    }

    private List<String> getAnswers() {
        ArrayList<String> answers = new ArrayList<>();
        answers.add("Test");
        answers.add("Hello");
        answers.add("World");
        return answers;
    }
}
