<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ENjdO4Dr2bkBIFxQpeoTz1HIcje39Wm4jDKdf19U8gI4ddQ3GYNS7NTKfAdVQSZe" crossorigin="anonymous"></script>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-KK94CHFLLe+nY2dmCWGMq91rCGa5gtU4mk92HdvYe+M/SXH301p5ILy+dN9+nJOZ" crossorigin="anonymous">
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<table class="table">
  <thead>
    <tr>
      <th scope="col">id</th>
      <th scope="col">codigo</th>
      <th scope="col">nombre</th>
      <th scope="col">cantidad</th>
      <th scope="col">precio</th>
      <th scope="col">caducidad</th>
      <th scope="col">Nombre de Seccion</th>
    </tr>
  </thead>
  <tbody>
  <c:forEach items="${productos}" var="producto">
  	<tr>
      <td>${producto.id}</td>
      <td>${producto.codigo}</td>
      <td>${producto.nombre}</td>
      <td>${producto.cantidad}</td>
      <td>${producto.precio}</td>
      <td>${producto.caducidad}</td>
      <td>${producto.nombreSeccion}</td>
    </tr>
  </c:forEach>
  </tbody>
</table>
</body>
</html>