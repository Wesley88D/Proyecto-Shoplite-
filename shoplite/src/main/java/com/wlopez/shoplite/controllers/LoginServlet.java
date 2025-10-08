package com.wlopez.shoplite.controllers;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.IOException;
import com.wlopez.shoplite.repositories.UserRepository;
import com.wlopez.shoplite.models.User;
@WebServlet(name = "LoginServlet", urlPatterns = {"/auth/login"})
public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        if(email == null || password == null){
            resp.sendRedirect(req.getContextPath() + "/login.jsp?err=1");
            return;
        }
        User u = UserRepository.findByEmail(email);
        if(u == null || !u.getPassword().equals(password)){
            resp.sendRedirect(req.getContextPath() + "/login.jsp?err=1");
            return;
        }
        HttpSession old = req.getSession(false);
        if(old != null) old.invalidate();
        HttpSession session = req.getSession(true);
        session.setAttribute("auth", true);
        session.setAttribute("userEmail", u.getEmail());
        session.setAttribute("role", u.getRole());
        session.setMaxInactiveInterval(30*60);
        resp.sendRedirect(req.getContextPath() + "/home");
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/login.jsp").forward(req, resp);
    }
}
