package com.lus.dawm.atelier1.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebFilter("/admin/*")
public class AuthenticationFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;

        boolean isLoggedIn = httpServletRequest.getSession().getAttribute("userLoggedIn") != null;

        if(isLoggedIn){
            String role = (String) httpServletRequest.getSession().getAttribute("role");
            boolean isAdmin = "admin".equals(role);
            if(isAdmin){
                chain.doFilter(request, response);
            }else{
//            System.out.println("Redirecting to login page... Requested URL: " + httpServletRequest.getRequestURI());
//            System.out.println("Redirected from: " + httpServletRequest.getHeader("referer"));
//            System.out.println("AuthenticationFilter - Original URL: " + httpServletRequest.getRequestURL());
//
//            new Exception("Stack trace for redirection").printStackTrace();
                System.out.println("Redirecting to unauthorized page... User is not an admin.");
                httpServletResponse.sendRedirect("unauthorized.html");
            }
        }else{
            System.out.println("Redirecting to login page... User is not authenticated.");
            httpServletResponse.sendRedirect("login.html");
        }
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
