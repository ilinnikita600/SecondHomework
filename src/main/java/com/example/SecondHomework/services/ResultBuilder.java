package com.example.SecondHomework.services;

import com.example.SecondHomework.model.Question;
import com.example.SecondHomework.model.Result;

import java.util.InvalidPropertiesFormatException;
import java.util.List;

public interface ResultBuilder {
    Result getResult(List<Question> questions, List<String> answers) throws InvalidPropertiesFormatException;
}
