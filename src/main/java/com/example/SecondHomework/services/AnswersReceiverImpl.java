package com.example.SecondHomework.services;


import com.example.SecondHomework.annotations.Loggable;
import com.example.SecondHomework.model.Question;

import jakarta.annotation.PostConstruct;
import lombok.Getter;
import lombok.Setter;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
@Component
@Scope("prototype")
public class AnswersReceiverImpl implements AnswersReceiver {
    @Getter
    private ArrayList<Question> questionsAsked;
    @Getter
    private ArrayList<String> answers;
    private BufferedReader bufferedReader;

    private BufferedWriter bufferedWriter;
    @Setter
    private Iterator<Question> questionsIterator;

    @PostConstruct
    public void init() {
        this.questionsAsked = new ArrayList<>();
        this.answers = new ArrayList<>();
        bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));
        questionsIterator = Collections.emptyIterator();
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

    public void setInput(InputStream inputStream) {
        this.bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
    }

    public void setOutput(OutputStream outputStream) {
        this.bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream));
    }
}
