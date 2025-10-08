# ShopLite - minimal webapp
Contains implementations for:
- AuthFilter: protects private pages, redirects to login.jsp if not authenticated.
- AdminFilter: protects /admin/* routes for ADMIN role.
- LoginServlet: handles /auth/login, session creation with attributes auth, userEmail, role and 30min timeout.
- LogoutServlet: invalidates session and redirects to index.jsp bye=1.
- HomeServlet: paginated product listing with parameters page and size.
- AdminServlet: create new products from admin panel.

Default users:
- admin@example.com / admin123 (ADMIN)
- user@example.com / user123 (USER)

