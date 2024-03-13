package com.lus.dawm.atelier1.controller;

import com.lus.dawm.atelier1.Service.UserService;
import com.lus.dawm.atelier1.model.Utilisateur;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/login.php")
public class LoginController extends HttpServlet {
    UserService userService;
    @Override
    public void init() throws ServletException{
        super.init();
        try{
            userService = new UserService();
        }catch(SQLException | ClassNotFoundException e){
            throw new RuntimeException("Error initializing UserService", e);
        }
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");

        Utilisateur user;
        try {
            user = userService.findUserByEmailAndPassword(email, password);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        if (user != null){
            req.getSession().setAttribute("userLoggedIn", true);
            req.getSession().setAttribute("email", user.getRole());
            req.getSession().setAttribute("role", user.getRole());
            resp.sendRedirect("home.jsp");
        }else{
            resp.sendRedirect("login.html");
        }
    }


}

