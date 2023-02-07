package com.example.SecondHomework;

import com.example.SecondHomework.model.Question;
import com.example.SecondHomework.model.Result;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@SpringBootTest
public class ResultTests {
    private final Result result;
    {
        result = new Result(getQuestionAndAnswerMap());
    }
    private Map<Question, String> getQuestionAndAnswerMap() {
        Map<Question, String> questionAndAnswerMap = new HashMap<>();

        Question question = new Question();
        question.setQuestionId(1);
        question.setQuestion("Сколько будет 2 - 1?");
        question.setVariationsOfAnswers(new ArrayList<>() {{add("1"); add("2"); add("3"); add("4");}});
        question.setCorrectAnswer("1");
        questionAndAnswerMap.put(question, "1");

        question = new Question();
        question.setQuestionId(2);
        question.setQuestion("В чем заключается суть карточный игры \"Дурак\"?");
        questionAndAnswerMap.put(question,"Побеждает тот, у кого достоинство карты больше");

        return questionAndAnswerMap;
    }
    @Test
    void getCorrectAnswersTest() throws Exception {
        int correctAnswers = result.getCorrectAnswers();
        Assertions.assertEquals(correctAnswers, 2);
    }
}
