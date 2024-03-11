package com.ayoub.University.Manager.Repository;

import com.ayoub.University.Manager.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findUserByLoginAndPassword(String login, String password);
    User findByLogin(String login);
}
