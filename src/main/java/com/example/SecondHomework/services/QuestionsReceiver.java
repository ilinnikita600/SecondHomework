package com.example.SecondHomework.services;


import com.example.SecondHomework.model.Question;

import java.io.IOException;
import java.util.Iterator;

public interface QuestionsReceiver {
    public Iterator<Question> getQuestions() throws IOException;
}
