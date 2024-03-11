package com.ayoub.University.Manager.Service;

import com.ayoub.University.Manager.Dto.UserDTO;
import com.ayoub.University.Manager.Response.HttpResponse;

public interface UserService {

    public HttpResponse addUser(UserDTO userDTO);
    public HttpResponse loginUser(UserDTO userDTO);
}
