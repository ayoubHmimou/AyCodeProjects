package com.ayoub.University.Manager.Repository;

import com.ayoub.University.Manager.Model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.Repository;
public interface StudentRepository extends JpaRepository<Student, Long> {


}
