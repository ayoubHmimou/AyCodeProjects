package com.ayoub.University.Manager.Service;

import com.ayoub.University.Manager.Dto.StudentDTO;
import com.ayoub.University.Manager.Model.Student;
import com.ayoub.University.Manager.Response.HttpResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface StudentService {

    public ResponseEntity<HttpResponse> getAllStudents();
    public ResponseEntity<HttpResponse>  addStudent(@RequestBody StudentDTO studentDTO);
    public ResponseEntity<HttpResponse> getStudentById(@PathVariable Long id);
    public ResponseEntity<HttpResponse> updateStudent(@PathVariable Long id, @RequestBody StudentDTO studentDTO);
    public ResponseEntity<HttpResponse> deleteStudent(@PathVariable Long id);
    public ResponseEntity<HttpResponse> viewStudentDetails(@PathVariable Long id);

}
