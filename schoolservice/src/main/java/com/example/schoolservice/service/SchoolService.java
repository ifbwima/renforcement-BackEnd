package com.example.schoolservice.service;

import com.example.schoolservice.model.School;
import com.example.schoolservice.repository.SchoolRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SchoolService {
    private final SchoolRepository schoolRepository;

    public List<School> findAll() {
        return schoolRepository.findAll();
    }

    public Optional<School> findById(Long id) {
        return schoolRepository.findById(id);
    }

    public School create(School school) {
        return schoolRepository.save(school);
    }

    public School update(Long id, School schoolDetails) {
        School school = schoolRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("School not found"));

        school.setSchoolname(schoolDetails.getSchoolname());

        return schoolRepository.save(school);
    }

    public void delete(Long id) {
        schoolRepository.deleteById(id);
    }
}