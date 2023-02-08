package com.example.SecondHomework;

import com.example.SecondHomework.aspects.LoggingAspect;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import org.springframework.aop.aspectj.annotation.AnnotationAwareAspectJAutoProxyCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@Import(AnnotationAwareAspectJAutoProxyCreator.class)
@SpringBootTest(classes = {LoggingTestComponent.class, LoggingAspect.class})
public class LoggingAspectTests {
    @Autowired
    private LoggingTestComponent loggingTestComponent;

    @Test
    void logBeforeTest() {
        Assertions.assertEquals(loggingTestComponent.getTestString(), "test");
    }
}
