package com.ayoub.University.Manager.Controller;

import com.ayoub.University.Manager.Dto.UserDTO;
import com.ayoub.University.Manager.Response.HttpResponse;
import com.ayoub.University.Manager.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/Uni")
@CrossOrigin("*")
public class UserController {
    @Autowired
    UserService userService;
    @PostMapping("/register")
    public HttpResponse addUser(@RequestBody UserDTO userDTO) {
        return userService.addUser(userDTO);
    }
    @PostMapping("/login")
    public ResponseEntity<HttpResponse> loginUser(@RequestBody UserDTO userDTO){
        HttpResponse httpResponse = userService.loginUser(userDTO);
        return ResponseEntity.ok().body(httpResponse);
    }
}
