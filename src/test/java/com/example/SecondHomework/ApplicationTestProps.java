package com.example.SecondHomework;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "application")
@Data
public class ApplicationTestProps {
    private String testAnswersPath;
}
