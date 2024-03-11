package com.ayoub.University.Manager.ServiceImpl;

import com.ayoub.University.Manager.Dto.UserDTO;
import com.ayoub.University.Manager.Model.User;
import com.ayoub.University.Manager.Repository.UserRepository;
import com.ayoub.University.Manager.Response.HttpResponse;
import com.ayoub.University.Manager.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.Repository;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Optional;
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Override
    public HttpResponse addUser(UserDTO userDTO) {
        User user = userRepository.findByLogin(userDTO.getLogin());
        if(user != null){
            return HttpResponse.builder()
                    .timeStamp(LocalDateTime.now().toString())
                    .data(Map.of("No Data Available", Boolean.FALSE))
                    .message("User with this Login already exists")
                    .status(HttpStatus.CONFLICT)
                    .statusCode(HttpStatus.CONFLICT.value())
                    .build();
        }else {
            User user2 = User.builder()
                    .id(userDTO.getId())
                    .login(userDTO.getLogin())
                    .password(this.passwordEncoder.encode(userDTO.getPassword()))
                    .build();

            userRepository.save(user2);
            return HttpResponse.builder()
                    .timeStamp(LocalDateTime.now().toString())
                    .data(Map.of("User Registered: ", user2))
                    .message("User Successfully Registered")
                    .status(HttpStatus.OK)
                    .statusCode(HttpStatus.OK.value())
                    .build();
        }
    }

    @Override
    public HttpResponse loginUser(UserDTO userDTO) {
        User user = userRepository.findByLogin(userDTO.getLogin());
        if(user != null){
            String password = userDTO.getPassword();
            String encodedPassword = user.getPassword();
            boolean doesPasswordMatch = passwordEncoder.matches(password, encodedPassword);
            if(doesPasswordMatch){
                Optional<User> user2 = userRepository.findUserByLoginAndPassword(userDTO.getLogin(), encodedPassword);
                if(user2.isPresent()){
                    return HttpResponse.builder()
                            .timeStamp(LocalDateTime.now().toString())
                            .data(Map.of("User Logged in: ", user2))
                            .message("login Successful")
                            .status(HttpStatus.OK)
                            .statusCode(HttpStatus.OK.value())
                            .build();
                }else{
                    return HttpResponse.builder()
                            .timeStamp(LocalDateTime.now().toString())
                            .data(Map.of("No Data", Boolean.FALSE))
                            .message("Login Failed")
                            .status(HttpStatus.INTERNAL_SERVER_ERROR)
                            .statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
                            .build();
                }
            }else{
                return HttpResponse.builder()
                        .timeStamp(LocalDateTime.now().toString())
                        .data(Map.of("No Data", Boolean.FALSE))
                        .message("Incorrect Password")
                        .status(HttpStatus.UNAUTHORIZED)
                        .statusCode(HttpStatus.UNAUTHORIZED.value())
                        .build();
            }
            }else{
            return HttpResponse.builder()
                    .timeStamp(LocalDateTime.now().toString())
                    .data(Map.of("No Data", Boolean.FALSE))
                    .message("Incorrect Login")
                    .status(HttpStatus.UNAUTHORIZED)
                    .statusCode(HttpStatus.UNAUTHORIZED.value())
                    .build();
        }
    }
}
