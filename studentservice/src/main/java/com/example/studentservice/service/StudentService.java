package com.example.studentservice.service;

import com.example.studentservice.model.Student;
import com.example.studentservice.repository.StudentRepository;
import com.example.studentservice.client.SchoolServiceClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentService {
    private final StudentRepository studentRepository;
    private final SchoolServiceClient schoolServiceClient;

    public List<Student> findAll() {
        return studentRepository.findAll();
    }

    public Mono<Student> findById(String id) {
        return Mono.fromSupplier(() -> studentRepository.findById(id))
                .flatMap(
                        optStudent -> optStudent.map(student -> schoolServiceClient.getSchoolById(student.getSchoolId())
                                .map(school -> {
                                    student.setAssociatedSchool(school);
                                    return student;
                                })).orElseGet(Mono::empty));
    }

    public Student create(Student student) {
        return studentRepository.save(student);
    }

    public Student update(String id, Student studentDetails) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        student.setFirstName(studentDetails.getFirstName());
        student.setLastName(studentDetails.getLastName());
        student.setEmail(studentDetails.getEmail());
        student.setSchoolId(studentDetails.getSchoolId());

        return studentRepository.save(student);
    }

    public void delete(String id) {
        studentRepository.deleteById(id);
    }
}