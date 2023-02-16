package com.example.SecondHomework.model;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class User {
    @NotNull
    @NotEmpty
    private String name;
    @Min(0)
    @Max(50)
    private int age;
}
