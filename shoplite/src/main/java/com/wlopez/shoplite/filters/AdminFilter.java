package com.wlopez.shoplite.filters;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.IOException;
public class AdminFilter implements Filter {
    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest r = (HttpServletRequest) req;
        HttpServletResponse s = (HttpServletResponse) res;
        HttpSession session = r.getSession(false);
        if(session == null){
            r.getRequestDispatcher("/login.jsp").forward(req, res);
            return;
        }
        Object role = session.getAttribute("role");
        if(role == null || !"ADMIN".equals(role.toString())){
            r.getRequestDispatcher("/403.jsp").forward(req, res);
            return;
        }
        chain.doFilter(req, res);
    }
}
