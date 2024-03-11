package com.ayoub.University.Manager.ServiceImpl;

import com.ayoub.University.Manager.Dto.StudentDTO;
import com.ayoub.University.Manager.Model.Student;
import com.ayoub.University.Manager.Repository.StudentRepository;
import com.ayoub.University.Manager.Response.HttpResponse;
import com.ayoub.University.Manager.Response.ResourceNotFoundException;
import com.ayoub.University.Manager.Service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    StudentRepository studentRepository;
    @Override
    public ResponseEntity<HttpResponse> getAllStudents() {
        List<Student> studentList = studentRepository.findAll();

        return ResponseEntity.ok().body
                (
                        HttpResponse.builder()
                                .timeStamp(LocalDateTime.now().toString())
                                .data(Map.of("List of all Students :", studentList))
                                .message("Student List Retrieved")
                                .status(HttpStatus.OK)
                                .statusCode(HttpStatus.OK.value())
                                .build()
                );
    }
    @Override
    public ResponseEntity<HttpResponse> addStudent(StudentDTO studentDTO) {
        Student student = Student.builder()
                .id(studentDTO.getId())
                .firstName(studentDTO.getFirstName())
                .lastName(studentDTO.getLastName())
                .birthdate(studentDTO.getBirthdate())
                .gender(studentDTO.getGender())
                .year(studentDTO.getYear())
                .build();

         studentRepository.save(student);

        return ResponseEntity.ok().body
                (
                        HttpResponse.builder()
                                .timeStamp(LocalDateTime.now().toString())
                                .data(Map.of("Student: ", student))
                                .message("Student Added")
                                .status(HttpStatus.OK)
                                .statusCode(HttpStatus.OK.value())
                                .build()
                );
    }

    @Override
    public ResponseEntity<HttpResponse> getStudentById(Long id) {
        Student student = studentRepository.findById(id).
                orElseThrow(()-> new ResourceNotFoundException("Student with id: "+id+" does not exist"));
        return ResponseEntity.ok().body
                (
                        HttpResponse.builder()
                                .timeStamp(LocalDateTime.now().toString())
                                .data(Map.of("Student", student))
                                .message("Student Retrieved")
                                .status(HttpStatus.OK)
                                .statusCode(HttpStatus.OK.value())
                                .build()
                );
    }
    @Override
    public ResponseEntity<HttpResponse> updateStudent(Long id, StudentDTO studentDTO) {
        Student student = studentRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Student with id: "+id+" does Not exist"));

        Student updatedStudent = student.toBuilder()
                .firstName(studentDTO.getFirstName())
                .lastName(studentDTO.getLastName())
                .birthdate(studentDTO.getBirthdate())
                .major(studentDTO.getMajor())
                .year(studentDTO.getYear())
                .build();

        studentRepository.save(updatedStudent);

        return ResponseEntity.ok().body
                (
                        HttpResponse.builder()
                                .timeStamp(LocalDateTime.now().toString())
                                .data(Map.of("Updated Student: ", updatedStudent))
                                .message("Student Updated")
                                .status(HttpStatus.OK)
                                .statusCode(HttpStatus.OK.value())
                                .build()
                );
    }
    @Override
    public ResponseEntity<HttpResponse> deleteStudent(Long id) {
        Student student = studentRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Student with id: "+id+" does Not exist"));
        studentRepository.delete(student);

        return ResponseEntity.ok().body
                (
                        HttpResponse.builder()
                                .timeStamp(LocalDateTime.now().toString())
                                .data(Map.of("Student Deleted :", student))
                                .message("Student Deleted Succesfully")
                                .status(HttpStatus.OK)
                                .statusCode(HttpStatus.OK.value())
                                .build()
                );
    }
    @Override
    public ResponseEntity<HttpResponse> viewStudentDetails(Long id) {
        Student student = studentRepository.findById(id).
                orElseThrow(()-> new ResourceNotFoundException("Student with id: "+id+" does not exist"));
        return ResponseEntity.ok().body
                (
                        HttpResponse.builder()
                                .timeStamp(LocalDateTime.now().toString())
                                .data(Map.of("Student", student))
                                .message("Student Rerieved")
                                .status(HttpStatus.OK)
                                .statusCode(HttpStatus.OK.value())
                                .build()
                );
    }
}
