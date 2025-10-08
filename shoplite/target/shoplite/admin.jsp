<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html lang="es">
<head>
  <meta charset="UTF-8">
  <title>ShopLite | Admin</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
  <link rel="stylesheet" href="css/style.css">
</head>
<body>
<nav class="navbar navbar-dark">
  <div class="container">
    <a class="navbar-brand" href="home">ShopLite Admin</a>
    <a href="auth/logout" class="btn btn-outline-light">Cerrar sesión</a>
  </div>
</nav>

<div class="container mt-5">
  <h3 class="mb-4">Crear nuevo producto</h3>
  <form action="${pageContext.request.contextPath}/admin" method="post">
    <div class="mb-3">
      <label class="form-label">Nombre del producto</label>
      <input type="text" name="name" class="form-control" required>
    </div>
    <div class="mb-3">
      <label class="form-label">Precio</label>
      <input type="number" name="price" class="form-control" step="0.01" required>
    </div>
    <button type="submit" class="btn btn-primary">Guardar producto</button>
  </form>

  <%
    if (request.getParameter("err") != null) {
  %>
  <div class="alert alert-danger mt-3">Datos inválidos. Intenta nuevamente.</div>
  <%
    }
  %>
</div>
</body>
</html>

