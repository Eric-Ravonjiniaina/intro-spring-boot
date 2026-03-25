package com.hei_school.query_param.validator;

import com.hei_school.query_param.entity.Student;
import com.hei_school.query_param.exception.BadRequestException;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class StudentValidator {
    public void validate(List<Student> students) {
        for (Student student : students) {
            if (student.getReference() == null || student.getReference().isBlank()) {
                throw new BadRequestException("NewStudent reference cannot be null");
            }
        }
    }
}