package com.example.SecondHomework;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@ConfigurationProperties(prefix = "application.questions")
@Component
public class QuestionsProps {
    private String correctAnswersPath;
    private String questionsPath;
}
