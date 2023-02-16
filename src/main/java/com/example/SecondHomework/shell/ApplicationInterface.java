package com.example.SecondHomework.shell;

import com.example.SecondHomework.model.Result;
import com.example.SecondHomework.model.User;
import com.example.SecondHomework.services.AnswersReceiver;
import com.example.SecondHomework.services.AnswersReceiverImpl;
import com.example.SecondHomework.services.QuestionsReceiver;
import com.example.SecondHomework.services.ResultBuilder;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Valid;
import jakarta.validation.Validation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.shell.Availability;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellMethodAvailability;
import org.springframework.shell.standard.ShellOption;

import java.io.IOException;
import java.util.Set;

import static jakarta.validation.Validation.buildDefaultValidatorFactory;

@ShellComponent
public class ApplicationInterface {

    private User user;
    private ApplicationContext context;

    public ApplicationInterface(User user, ApplicationContext context) {
        this.user = user;
        this.context = context;
    }

    @ShellMethod(value = "Set user information", key="set-inf",prefix="--")
    public String setUserInfo(@ShellOption("--name") String name,@ShellOption("--age") int age) {
        user.setName(name);
        user.setAge(age);
        return "Data: " + name + " " + age;
    }

    @ShellMethod(value="start")
    @ShellMethodAvailability(value="checkStartAvailability")
    public void start() throws IOException {
        AnswersReceiver answersReceiver = context.getBean(AnswersReceiver.class);
        QuestionsReceiver questionsReceiver = context.getBean(QuestionsReceiver.class);

        ((AnswersReceiverImpl) answersReceiver).setQuestionsIterator(questionsReceiver.getQuestions());

        while (answersReceiver.askQuestion());

        ResultBuilder resultBuilder = context.getBean(ResultBuilder.class);
        Result result = resultBuilder.getResult(answersReceiver.getQuestionsAsked(), answersReceiver.getAnswers());
        System.out.println(result.getCorrectAnswers());
    }

    private Availability checkStartAvailability() {
        Set<ConstraintViolation<User>> validates = buildDefaultValidatorFactory().getValidator().validate(user);
        return validates.size() == 0 ? Availability.available() : Availability.unavailable(validates.toString());
    }
}
