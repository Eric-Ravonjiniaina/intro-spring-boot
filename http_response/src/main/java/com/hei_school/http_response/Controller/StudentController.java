package com.hei_school.http_response.Controller;

import com.hei_school.http_response.Repository.Student;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@RestController
public class StudentController {

    private static final List<Student> studentList = new ArrayList<>();

    @PostMapping("/students")
    public ResponseEntity<?> addStudents(@RequestBody List<Student> newStudents) {
        try {
            studentList.addAll(newStudents);
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(studentList);
        } catch (RuntimeException e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .build();
        }
    }

    @GetMapping("/students")
    public ResponseEntity<?> getStudents(@RequestHeader(value = "Accept") String accept) {
        if (accept == null) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("Header manquant");
        }

        if (accept.contains("text/plain") || accept.contains("application/json")) {
            return ResponseEntity.ok(studentList);
        } else {
            return ResponseEntity
                    .status(HttpStatus.NOT_IMPLEMENTED)
                    .body("Format non supporté");
        }
    }
}