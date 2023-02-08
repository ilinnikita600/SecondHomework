package com.example.SecondHomework;

import com.example.SecondHomework.services.AnswersReceiver;
import com.example.SecondHomework.services.AnswersReceiverImpl;
import com.example.SecondHomework.services.AnswersReceiverWrapper;
import com.example.SecondHomework.services.QuestionsReceiver;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import java.io.IOException;

@SpringBootApplication
@EnableAspectJAutoProxy
@EnableConfigurationProperties(QuestionsProps.class)
public class SecondHomeworkApplication {
	public static void main(String[] args) {
		var context = SpringApplication.run(SecondHomeworkApplication.class, args);
		QuestionsReceiver questionsReceiver = context.getBean(QuestionsReceiver.class);
		try {
			AnswersReceiverWrapper beanAnswersReceiver = (AnswersReceiverWrapper) context.getBean(AnswersReceiver.class);
			beanAnswersReceiver.setAnswersReceiver(new AnswersReceiverImpl(questionsReceiver.getQuestions(), System.in, System.out));
			AnswersReceiver answersReceiver = context.getBean(AnswersReceiver.class);
			while(answersReceiver.askQuestion());
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
