package com.example.SecondHomework;

import com.example.SecondHomework.services.QuestionsReceiver;
import com.example.SecondHomework.services.QuestionsReceiverFromFile;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;

@SpringBootTest
public class QuestionsReceiverFromFileTests {

    private QuestionsReceiver questionsReceiver;
    private QuestionsProps questionsProps;

    @Autowired
    QuestionsReceiverFromFileTests(QuestionsProps questionsProps) {
        this.questionsProps = questionsProps;
        this.questionsReceiver = new QuestionsReceiverFromFile(questionsProps);
    }

    @Test
    void getQuestionsTest() throws IOException {
        AtomicInteger count = new AtomicInteger();
        questionsReceiver.getQuestions().forEachRemaining(a -> count.addAndGet(1));
        Assertions.assertThat(count.get()).isEqualTo(5);
    }
}
