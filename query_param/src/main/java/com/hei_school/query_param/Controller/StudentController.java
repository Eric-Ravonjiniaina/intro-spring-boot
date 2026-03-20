package com.hei_school.query_param.Controller;

import com.hei_school.query_param.Repository.Student;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class StudentController {
    private static List<Student> studentList = new ArrayList<>();

    @PostMapping("/students")
    public ResponseEntity<String> addStudents(@RequestBody List<Student> newStudents) {
        studentList.addAll(newStudents);
        String allNames = studentList.stream()
                .map(s -> s.getFirstName() + " " + s.getLastName())
                .collect(Collectors.joining(", "));

        return ResponseEntity.status(201).body(allNames);
    }

    @GetMapping("/students")
    public ResponseEntity<String> getStudentsByHeader(
            @RequestHeader(value = "Accept", defaultValue = "text/plain") String acceptHeader) {

        if (acceptHeader.contains("text/plain")) {
            String result = studentList.stream()
                    .map(s -> s.getFirstName() + " " + s.getLastName())
                    .collect(Collectors.joining(", "));

            System.out.println(result);

            return ResponseEntity
                    .status(200)
                    .body(result);
        } else {
            return ResponseEntity
                    .status(406)
                    .body("Format non supporté");
        }
    }
}