package com.example.SecondHomework;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix="application")
public class ApplicationProps {
    private String correctAnswersPath;
    private String questionsPath;
    private int minCorrectAnswers;
}
