package com.wlopez.shoplite.controllers;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.IOException;
import com.wlopez.shoplite.repositories.ProductRepository;
import com.wlopez.shoplite.models.Product;
@WebServlet(name = "AdminServlet", urlPatterns = {"/admin"})
public class AdminServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/admin.jsp").forward(req, resp);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String priceS = req.getParameter("price");
        double price = 0;
        try{ price = Double.parseDouble(priceS); } catch(Exception e){}
        if(name == null || name.trim().isEmpty() || price <= 0){
            resp.sendRedirect(req.getContextPath() + "/admin?err=1");
            return;
        }
        int id = ProductRepository.nextId();
        ProductRepository.save(new Product(id, name.trim(), price));
        resp.sendRedirect(req.getContextPath() + "/home");
    }
}
