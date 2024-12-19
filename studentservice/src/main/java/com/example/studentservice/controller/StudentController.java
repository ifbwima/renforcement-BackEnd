package com.example.studentservice.controller;

import com.example.studentservice.model.Student;
import com.example.studentservice.service.StudentService;
import lombok.RequiredArgsConstructor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping("/students")
@RequiredArgsConstructor
public class StudentController {
    private final StudentService studentService;

    @GetMapping
    public List<Student> getAllStudents() {
        return studentService.findAll();
    }

    @GetMapping("/{id}")
    public Mono<ResponseEntity<Student>> getStudentById(@PathVariable String id) {
        return studentService.findById(id)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Student createStudent(@RequestBody Student student) {
        return studentService.create(student);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Student> updateStudent(
            @PathVariable String id,
            @RequestBody Student studentDetails) {
        Student updatedStudent = studentService.update(id, studentDetails);
        return ResponseEntity.ok(updatedStudent);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable String id) {
        studentService.delete(id);
        return ResponseEntity.ok().build();
    }

    @SpringBootApplication
    public class StudentServiceApplication {
        public static void main(String[] args) {
            SpringApplication.run(StudentServiceApplication.class, args);
        }
    }
}