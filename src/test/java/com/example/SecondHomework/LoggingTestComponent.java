package com.example.SecondHomework;

import com.example.SecondHomework.annotations.Loggable;
import org.springframework.stereotype.Component;

@Component
public class LoggingTestComponent {

    @Loggable
    public String getTestString() {
        return "test";
    }
}
