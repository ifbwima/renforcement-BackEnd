package com.example.studentservice.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "students")
public class Student {
    @Id
    private String id;

    private String firstName;
    private String lastName;
    private String email;
    private Long schoolId;

    @Transient
    private School associatedSchool;

    public Student(String firstName, String lastName, String email, Long schoolId) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.schoolId = schoolId;
    }
}