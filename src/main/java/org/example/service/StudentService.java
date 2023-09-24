package org.example.service;

import org.example.dto.StudentRequest;
import org.example.entity.Student;
import org.example.exception.StudentNotFoundException;
import org.example.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    public Student saveStudent(StudentRequest studentRequest) {
        Student student = new Student();
        student.setIdNo(studentRequest.getIdNo());
        student.setName(studentRequest.getName());
        student.setEmail(studentRequest.getEmail());
        student.setMobileNumber(studentRequest.getMobileNumber());
        return studentRepository.save(student);
    }

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Student getStudent(int id) throws StudentNotFoundException {
        Student student = studentRepository.findByStudentId(id);
        if (student != null) {
            return student;
        } else {
            throw new StudentNotFoundException("user not found with id : "+id);
        }
    }

    public Student updateStudent(int id, StudentRequest request) {
        try {
            Student existingStudent = getStudent(id);
            existingStudent.setIdNo(request.getIdNo());
            existingStudent.setName(request.getName());
            existingStudent.setEmail(request.getEmail());
            existingStudent.setMobileNumber(request.getMobileNumber());
            return studentRepository.save(existingStudent);
        } catch (StudentNotFoundException ex) {
            return null;
        }
    }

    public void deleteStudent(int id) throws StudentNotFoundException {
        try {
            studentRepository.deleteById(id);
        } catch (Exception exception) {
            throw new StudentNotFoundException("user id not found"+exception.getMessage());
        }
    }
}
