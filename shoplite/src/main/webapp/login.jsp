<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html lang="es">
<head>
  <meta charset="UTF-8">
  <title>ShopLite | Login</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
  <link rel="stylesheet" href="css/style.css">
</head>
<body>
<div class="container d-flex justify-content-center align-items-center vh-100">
  <div class="card p-4" style="max-width:400px;">
    <h3 class="text-center mb-4">Iniciar sesión</h3>
    <form action="auth/login" method="post">
      <div class="mb-3">
        <label class="form-label">Correo electrónico</label>
        <input type="email" name="email" class="form-control" required>
      </div>
      <div class="mb-3">
        <label class="form-label">Contraseña</label>
        <input type="password" name="password" class="form-control" required>
      </div>
      <button type="submit" class="btn btn-primary w-100">Entrar</button>
    </form>
    <%
      if (request.getParameter("err") != null) {
    %>
    <div class="alert alert-danger mt-3 text-center">Credenciales inválidas</div>
    <%
      }
    %>
  </div>
</div>
</body>
</html>
