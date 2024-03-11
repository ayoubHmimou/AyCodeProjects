package com.ayoub.University.Manager.Model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
@Entity
@Table(name = "students")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class Student {
    @Id
    @Column(name = "student_id")
    @SequenceGenerator
            (
                    name = "student_sequence",
                    sequenceName = "student_sequence",
                    allocationSize = 1
            )
    @GeneratedValue
            (
                    strategy = GenerationType.SEQUENCE,
                    generator = "student_sequence"
            )
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column
            (
                    name = "birthdate",
                    columnDefinition = "DATE"
            )
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    @JsonFormat(pattern = "dd-MM-yyyy")
    private Date birthdate;

    @Column(name = "gender")
    private String gender;

    @Column(name = "major")
    private String major;

    @Column(name = "year")
    private String year;



}
