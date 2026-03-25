package com.hei_school.query_param.service;

import com.hei_school.query_param.entity.Student;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentService {
    private final List<Student> studentList = new ArrayList<>();

    public String addAndFormat(List<Student> newStudents) {
        studentList.addAll(newStudents);
        return studentList.stream()
                .map(s -> s.getFirstName() + " " + s.getLastName())
                .collect(Collectors.joining(", "));
    }

    public String getAllFormatted() {
        return studentList.stream()
                .map(s -> s.getFirstName() + " " + s.getLastName())
                .toList()
                .toString();
    }
}