package com.hei_school.query_param.controller;

import com.hei_school.query_param.entity.Student;
import com.hei_school.query_param.exception.BadRequestException;
import com.hei_school.query_param.service.StudentService;
import com.hei_school.query_param.validator.StudentValidator;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StudentController {
    private final StudentService studentService;
    private final StudentValidator studentValidator;

    public StudentController(StudentService studentService, StudentValidator studentValidator) {
        this.studentService = studentService;
        this.studentValidator = studentValidator;
    }

    @PostMapping("/students")
    public ResponseEntity<String> addStudents(@RequestBody List<Student> newStudents) {
        try {
            studentValidator.validate(newStudents);
            String result = studentService.addAndFormat(newStudents);
            return ResponseEntity.ok(result);
        } catch (BadRequestException e) {
            return ResponseEntity
                    .badRequest()
                    .body(e.getMessage());
        }
    }

    @GetMapping("/students")
    public ResponseEntity<String> getStudents(@RequestHeader(value = "Accept") String acceptHeader) {
        if (!"text/plain".equals(acceptHeader)) {
            return ResponseEntity
                    .status(406)
                    .body("Format non supporté");
        }
        return ResponseEntity
                .ok(studentService.getAllFormatted());
    }
}