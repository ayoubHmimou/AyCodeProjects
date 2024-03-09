package com.lus.dawm.atelier1.controller;

import com.lus.dawm.atelier1.model.Utilisateur;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/login.php")
public class LoginController extends HttpServlet {

    Utilisateur user = new Utilisateur(1L, "admin@gmail.com", "root", "ayoub", "Hmimou", "admin");

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");

        if(authenticateUser(email, password)){

            req.getSession().setAttribute("userLoggedIn", true);
            req.getSession().setAttribute("email", email);
            req.getSession().setAttribute("userRole", user.getRole());
            resp.sendRedirect("home.jsp");
        }else{
            resp.sendRedirect("login.html");
        }
    }

    private boolean authenticateUser(String email, String password){
        return email.equals(user.getEmail()) && password.equals(user.getPassword());
    }
}

