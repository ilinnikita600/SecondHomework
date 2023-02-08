package com.example.SecondHomework.services;

import com.example.SecondHomework.QuestionsProps;
import com.example.SecondHomework.annotations.Loggable;
import com.example.SecondHomework.model.Question;

import org.springframework.stereotype.Service;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;

@Service
public class QuestionsReceiverFromFile implements QuestionsReceiver {
    private final String questionsPath;
    private final String correctAnswersPath;

    public QuestionsReceiverFromFile(QuestionsProps questionsProps) {
        this.questionsPath = questionsProps.getQuestionsPath();
        this.correctAnswersPath = questionsProps.getCorrectAnswersPath();
    }
    private ArrayList<Question> readQuestions() throws IOException {
        ArrayList<Question> questions = new ArrayList<>();

        try(InputStream inputStreamQuestions = getClass().getClassLoader().getResourceAsStream(questionsPath);
        InputStream inputStreamCorrectAnswers = getClass().getClassLoader().getResourceAsStream(correctAnswersPath)) {
            if (inputStreamQuestions == null) {
                throw new FileNotFoundException("File " + questionsPath + " not found.");
            } else if (inputStreamCorrectAnswers == null) {
                throw new FileNotFoundException("File " + correctAnswersPath + " not found.");
            }

            BufferedReader questionsReader = new BufferedReader(new InputStreamReader(inputStreamQuestions));
            BufferedReader correctAnswersReader = new BufferedReader(new InputStreamReader(inputStreamCorrectAnswers));

            while(questionsReader.ready()) {
                questions.add(readQuestion(questionsReader, correctAnswersReader));
            }
        }

        return questions;
    }
    private Question readQuestion(BufferedReader questionsReader, BufferedReader correctAnswersReader) throws IOException {
        Question question = new Question();

        String[] questionInfo = questionsReader.readLine().split(" ",3);
        question.setQuestion(questionInfo[0]);
        question.setQuestion(questionInfo[2]);

        int countOfAnswersVariation = Integer.parseInt(questionInfo[1]);
        for(int i = 0; i < countOfAnswersVariation; i++) {
            question.addVariationOfAnswers(questionsReader.readLine());
        }

        if (countOfAnswersVariation > 0) {
            question.setCorrectAnswer(correctAnswersReader.readLine().split(" ")[1]);
        }

        return question;
    }
    @Loggable
    public Iterator<Question> getQuestions() throws IOException {
        return readQuestions().iterator();
    }
}
