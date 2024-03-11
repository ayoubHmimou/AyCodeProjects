package com.ayoub.University.Manager.Controller;

import com.ayoub.University.Manager.Dto.StudentDTO;
import com.ayoub.University.Manager.Response.HttpResponse;
import com.ayoub.University.Manager.Service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/Uni")
@CrossOrigin("*")
public class StudentController {

    @Autowired
    StudentService studentService;

    @GetMapping("/students")
    public ResponseEntity<HttpResponse> getAllStudents(){
        return studentService.getAllStudents();
    }

    @PostMapping("/students")
    public ResponseEntity<HttpResponse> addStudent(@RequestBody StudentDTO studentDTO){
        return studentService.addStudent(studentDTO);
    }

    @GetMapping("/students/{id}")
    public ResponseEntity<HttpResponse> getStudentById(@PathVariable Long id){
        return studentService.getStudentById(id);
    }

    @PutMapping("/students/{id}")
    public ResponseEntity<HttpResponse> updateStudent(@PathVariable Long id, @RequestBody StudentDTO studentDTO){
        return studentService.updateStudent(id, studentDTO);
    }

    @DeleteMapping("/students/{id}")
    public ResponseEntity<HttpResponse> deleteStudent(@PathVariable Long id){
        return studentService.deleteStudent(id);
    }

    @GetMapping("students/student-details/{id}")
    public ResponseEntity<HttpResponse> viewStudentDetails(@PathVariable Long id){
        return studentService.viewStudentDetails(id);
    }
}
