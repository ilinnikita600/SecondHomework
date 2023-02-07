package com.example.SecondHomework;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy
@EnableConfigurationProperties(ApplicationProps.class)
public class SecondHomeworkApplication {

	public static void main(String[] args) {
		var context = SpringApplication.run(SecondHomeworkApplication.class, args);
	}

}
