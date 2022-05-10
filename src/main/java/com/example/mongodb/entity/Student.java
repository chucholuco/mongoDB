package com.example.mongodb.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@Data
@Document(collection = "student")
public class Student {

    @Id
    private String id;

    //@Field(name = "name")
    private String name;

    @Field(name = "mail")
    private String email;

    @DBRef
    private Department department;

    @DBRef //(lazy = true)
    private List<Subject> subjects;

    @Transient
    private double percentage;

    public double getPercentage() {
        if (subjects != null && subjects.size() > 0) {
            int total = 0;
            for (Subject subject : subjects) {
                total += subject.getMarksObtained();
            }
            return total / subjects.size();
        }
        return 0.00;
    }
}
