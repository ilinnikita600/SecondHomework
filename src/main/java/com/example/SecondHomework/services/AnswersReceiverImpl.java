package com.example.SecondHomework.services;


import com.example.SecondHomework.annotations.Loggable;
import com.example.SecondHomework.model.Question;
import com.example.SecondHomework.model.Result;
import lombok.Getter;

import java.io.*;
import java.util.ArrayList;
import java.util.InvalidPropertiesFormatException;
import java.util.Iterator;

public class AnswersReceiverImpl implements AnswersReceiver {
    @Getter
    private final ArrayList<Question> questionsAsked;
    @Getter
    private final ArrayList<String> answers;
    private final BufferedReader bufferedReader;
    private final BufferedWriter bufferedWriter;
    private final Iterator<Question> questionsIterator;

    public AnswersReceiverImpl(Iterator<Question> questionsIterator, InputStream is, OutputStream os) {
        bufferedReader = new BufferedReader(new InputStreamReader(is));
        bufferedWriter = new BufferedWriter(new OutputStreamWriter(os));
        this.questionsIterator = questionsIterator;
        this.questionsAsked = new ArrayList<>();
        this.answers = new ArrayList<>();
    }
    @Override
    @Loggable
    public boolean askQuestion() throws IOException {
        if (!questionsIterator.hasNext()) return false;
        Question current = questionsIterator.next();
        bufferedWriter.write("Question:" + current.getQuestion() + "\n");

        ArrayList<String> variationsOfAnswers = current.getVariationsOfAnswers();
        if (variationsOfAnswers.size() > 0) {
            bufferedWriter.write("Variations of answer:\n");
            for(String variationOfAnswer: variationsOfAnswers) {
                bufferedWriter.write(variationOfAnswer + "\n");
            }
        }
        bufferedWriter.write("Enter answer:");
        bufferedWriter.flush();
        answers.add(bufferedReader.readLine());
        questionsAsked.add(current);
        return true;
    }
}
