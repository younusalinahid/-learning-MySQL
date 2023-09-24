package org.example.controller;

import org.example.dto.StudentRequest;
import org.example.entity.Student;
import org.example.exception.StudentNotFoundException;
import org.example.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @PostMapping("/singup")
    public ResponseEntity<Student> saveStudent(@RequestBody @Valid StudentRequest studentRequest) {
        return new ResponseEntity<>(studentService.saveStudent(studentRequest), HttpStatus.CREATED);
    }

    @GetMapping("/fetchAll")
    public ResponseEntity<List<Student>> getAllStudents() {
        return ResponseEntity.ok(studentService.getAllStudents());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudent(@PathVariable int id) throws StudentNotFoundException {
        return ResponseEntity.ok(studentService.getStudent(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity updateStudent(@PathVariable int id, @RequestBody @Valid StudentRequest studentRequest) throws StudentNotFoundException {
        Student updateStudent = studentService.updateStudent(id, studentRequest);
        return ResponseEntity.ok(updateStudent);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable int id) throws StudentNotFoundException {
        studentService.deleteStudent(id);
        return ResponseEntity.ok("Successfully deleted : "+id);
    }


}
