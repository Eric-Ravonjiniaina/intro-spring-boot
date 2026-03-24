package com.hei_school.query_param.Controller;

import com.hei_school.query_param.Repository.Student;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class StudentController {
    private static final List<Student> studentList = new ArrayList<>();

    @PostMapping("/students")
    public String addStudents(@RequestBody List<Student> newStudents) {
        studentList.addAll(newStudents);

        return studentList.stream()
                .map(s -> s.getFirstName() + " " + s.getLastName())
                .collect(Collectors.joining(", "));
    }

    @GetMapping("/students")
    public String getStudents(@RequestHeader(value = "Accept") String acceptHeader) {
        if (!acceptHeader.equals("text/plain")) {
            return "Format non supporté";
        }
        return studentList.stream()
                .map(s -> s.getFirstName() + " " + s.getLastName())
                .toList()
                .toString();
    }
}