package com.wlopez.shoplite.filters;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.Set;
public class AuthFilter implements Filter {
    private static final Set<String> PUBLIC_URIS = Set.of(
        "/", "/index.jsp", "/login.jsp", "/auth/login", "/auth/logout", "/css/", "/js/"
    );
    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest r = (HttpServletRequest) req;
        HttpServletResponse s = (HttpServletResponse) res;
        String uri = r.getRequestURI();
        String context = r.getContextPath();
        String path = uri.substring(context.length());
        boolean isPublic = path.equals("/") || path.equals("") || path.endsWith("index.jsp") ||
                path.endsWith("login.jsp") || path.equals("/auth/login") || path.startsWith("/public") ;
        if(isPublic){
            chain.doFilter(req, res);
            return;
        }
        HttpSession session = r.getSession(false);
        if(session != null && Boolean.TRUE.equals(session.getAttribute("auth"))){
            chain.doFilter(req, res);
        } else {
            s.sendRedirect(r.getContextPath() + "/login.jsp");
        }
    }
}
