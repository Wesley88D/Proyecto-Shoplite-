package com.wlopez.shoplite.controllers;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import com.wlopez.shoplite.repositories.ProductRepository;
import com.wlopez.shoplite.models.Product;
@WebServlet(name = "HomeServlet", urlPatterns = {"/home"})
public class HomeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Product> all = ProductRepository.findAll();
        int total = all.size();
        int page = 1;
        int size = 5;
        try {
            String p = req.getParameter("page");
            String s = req.getParameter("size");
            if(p != null) page = Integer.parseInt(p);
            if(s != null) size = Integer.parseInt(s);
            if(page < 1) page = 1;
            if(size < 1) size = 5;
        } catch(Exception e){}
        int from = (page-1)*size;
        int to = Math.min(from + size, total);
        List<Product> items = all.stream().skip(from).limit(size).collect(Collectors.toList());
        req.setAttribute("items", items);
        req.setAttribute("page", page);
        req.setAttribute("size", size);
        req.setAttribute("total", total);
        req.getRequestDispatcher("/home.jsp").forward(req, resp);
    }
}
