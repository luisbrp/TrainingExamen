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
  <form class="d-flex search-form" method="GET" action="VerProductos">
    <input class="form-control me-2 ms-2" type="number" step=0.1 placeholder="Precio mínimo" aria-label="Precio mínimo" name="precioMin">
    <input class="form-control me-2 ms-2" type="number" step=0.1 placeholder="Precio máximo" aria-label="Precio máximo" name="precioMax">
    <button class="btn btn-outline-success me-2 ms-2" type="submit">Filtrar</button>
    <button class="btn btn-outline-dark" name="recargar">Recargar</button>
  </form>
  
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
        <th><form class="d-flex search-form" method="GET" action="VerProductos">
			    <input class="form-control me-2 ms-2" type="search" placeholder="Buscar (Nombre o código)" aria-label="Buscar" name="cadena">
			    <button class="btn btn-outline-success" type="submit">Buscar</button>
			 </form></th>
      </tr>
    </thead>
    <c:if test="${productosEncontrados != null}">
      <tbody>
        <c:forEach items="${productosEncontrados}" var="producto">
          <tr>
            <td>${producto.id}</td>
            <td>${producto.codigo}</td>
            <td>${producto.nombre}</td>
            <td>${producto.cantidad}</td>
            <td>${producto.precio}</td>
            <td>${producto.caducidad}</td>
            <td>${producto.nombreSeccion}</td>
            <td><a href="ModificarProducto?id=${producto.id}" class="btn btn-primary">Editar</a></td>
          </tr>
        </c:forEach>
      </tbody>
    </c:if>
    <c:if test="${productosFiltrados != null}">
      <tbody>
        <c:forEach items="${productosFiltrados}" var="producto">
          <tr>
            <td>${producto.id}</td>
            <td>${producto.codigo}</td>
            <td>${producto.nombre}</td>
            <td>${producto.cantidad}</td>
            <td>${producto.precio}</td>
            <td>${producto.caducidad}</td>
            <td>${producto.nombreSeccion}</td>
            <td><a href="ModificarProducto?id=${producto.id}" class="btn btn-primary">Editar</a></td>
          </tr>
        </c:forEach>
      </tbody>
    </c:if>
    <tbody>
      <c:forEach items="${TodosLosProductos}" var="producto">
        <tr>
          <td>${producto.id}</td>
          <td>${producto.codigo}</td>
          <td>${producto.nombre}</td>
          <td>${producto.cantidad}</td>
          <td>${producto.precio}</td>
          <td>${producto.caducidad}</td>
          <td>${producto.nombreSeccion}</td>
          <td><a href="ModificarProducto?id=${producto.id}" class="btn btn-primary">Editar</a></td>
        </tr>
      </c:forEach>
    </tbody>
  </table>
</body>
</html>