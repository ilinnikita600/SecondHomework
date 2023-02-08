package com.example.SecondHomework.services;

import com.example.SecondHomework.annotations.Loggable;
import com.example.SecondHomework.model.Question;
import com.example.SecondHomework.model.Result;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.InvalidPropertiesFormatException;
import java.util.Iterator;
import java.util.List;

@Component
public class ResultBuilderImpl implements ResultBuilder {
    @Loggable
    @Override
    public Result getResult(List<Question> questions, List<String> answers) throws InvalidPropertiesFormatException {
        if (questions.size() != answers.size()) {
            throw new InvalidPropertiesFormatException("Size of questions and answers list is not equal");
        }

        HashMap<Question, String> questionsResult = new HashMap<>();
        Iterator<Question> questionsIterator = questions.iterator();
        Iterator<String> answersIterator = answers.iterator();
        while(questionsIterator.hasNext()) {
            questionsResult.put(questionsIterator.next(), answersIterator.next().trim());
        }
        return new Result(questionsResult);
    }
}
