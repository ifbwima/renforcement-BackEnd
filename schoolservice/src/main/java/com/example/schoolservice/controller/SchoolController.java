package com.example.schoolservice.controller;

import com.example.schoolservice.model.School;
import com.example.schoolservice.service.SchoolService;
import lombok.RequiredArgsConstructor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/schools")
@RequiredArgsConstructor
public class SchoolController {
    private final SchoolService schoolService;

    @GetMapping
    public List<School> getAllSchools() {
        return schoolService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<School> getSchoolById(@PathVariable Long id) {
        return schoolService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public School createSchool(@RequestBody School school) {
        return schoolService.create(school);
    }

    @PutMapping("/{id}")
    public ResponseEntity<School> updateSchool(
            @PathVariable Long id,
            @RequestBody School schoolDetails) {
        School updatedSchool = schoolService.update(id, schoolDetails);
        return ResponseEntity.ok(updatedSchool);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSchool(@PathVariable Long id) {
        schoolService.delete(id);
        return ResponseEntity.ok().build();
    }

    @SpringBootApplication
    public class SchoolServiceApplication {
        public static void main(String[] args) {
            SpringApplication.run(SchoolServiceApplication.class, args);
        }
    }
}