package com.example.studentservice.model;

import lombok.Data;
import java.io.Serializable;

@Data
public class School implements Serializable {
    private Long schoolid;
    private String schoolname;
}