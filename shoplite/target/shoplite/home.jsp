<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="com.wlopez.shoplite.models.Product" %>
<!DOCTYPE html>
<html lang="es">
<head>
  <meta charset="UTF-8">
  <title>ShopLite | Home</title>
  <!-- Bootstrap -->
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
  <!-- Tu style.css -->
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
<nav class="navbar navbar-dark bg-dark">
  <div class="container">
    <a class="navbar-brand" href="${pageContext.request.contextPath}/home">ShopLite</a>
    <% if(session.getAttribute("userEmail") != null){ %>
    <span class="text-white">
                Usuario: <%= session.getAttribute("userEmail") %> - Rol: <%= session.getAttribute("role") %>
            </span>
    <a href="<%=request.getContextPath()%>/auth/logout" class="btn btn-outline-light btn-sm">Cerrar sesión</a>
    <% } %>
  </div>
</nav>

<div class="container mt-4">
  <h2 class="mb-3">Productos disponibles (total: <%= request.getAttribute("total") %>)</h2>

  <div class="row">
    <%
      List<Product> items = (List<Product>) request.getAttribute("items");
      if(items != null && !items.isEmpty()){
        for(Product p : items){
    %>
    <div class="col-md-4 mb-3">
      <div class="card p-3 shadow-sm">
        <h5><%= p.getName() %></h5>
        <p class="text-muted">$<%= p.getPrice() %></p>
      </div>
    </div>
    <%
      }
    } else {
    %>
    <div class="col-12">
      <div class="alert alert-info">No hay productos disponibles.</div>
    </div>
    <%
      }
    %>
  </div>

  <div class="d-flex justify-content-between align-items-center mt-4">
    <%
      int currentPage = 1;
      int pageSize = 6;
      int totalItems = 0;

      if(request.getAttribute("page") != null) currentPage = (Integer) request.getAttribute("page");
      if(request.getAttribute("size") != null) pageSize = (Integer) request.getAttribute("size");
      if(request.getAttribute("total") != null) totalItems = (Integer) request.getAttribute("total");

      int totalPages = (int) Math.ceil((double) totalItems / pageSize);
      if(totalPages <= 0) totalPages = 1;

      int prevPage = Math.max(1, currentPage - 1);
      int nextPage = Math.min(totalPages, currentPage + 1);
    %>

    <a href="<%=request.getContextPath()%>/home?page=<%= prevPage %>&size=<%= pageSize %>" class="btn btn-secondary">Anterior</a>
    <span>Página <%= currentPage %> de <%= totalPages %></span>
    <a href="<%=request.getContextPath()%>/home?page=<%= nextPage %>&size=<%= pageSize %>" class="btn btn-secondary">Siguiente</a>

  </div>

  <% if("ADMIN".equalsIgnoreCase((String)session.getAttribute("role"))){ %>
  <div class="mt-3">
    <a href="<%=request.getContextPath()%>/admin" class="btn btn-primary">Panel Admin</a>
  </div>
  <% } %>
</div>

</body>
</html>

