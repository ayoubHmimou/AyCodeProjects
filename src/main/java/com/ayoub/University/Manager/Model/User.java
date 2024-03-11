package com.ayoub.University.Manager.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "users")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {
    @Id
    @Column(name = "user_id")
    @SequenceGenerator
            (
                    name = "user_sequence",
                    sequenceName = "user_sequence",
                    allocationSize = 1
            )
    @GeneratedValue
            (
                    generator = "user_sequence",
                    strategy = GenerationType.SEQUENCE
            )
    private Long id;

    private String login;
    private String password;
}
