<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ENjdO4Dr2bkBIFxQpeoTz1HIcje39Wm4jDKdf19U8gI4ddQ3GYNS7NTKfAdVQSZe" crossorigin="anonymous"></script>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-KK94CHFLLe+nY2dmCWGMq91rCGa5gtU4mk92HdvYe+M/SXH301p5ILy+dN9+nJOZ" crossorigin="anonymous">
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
		
		<c:if test="${error == true}">
			<div class="alert alert-danger" role="alert">
  				ERROR
			</div>
		</c:if>
		
		<form method="post" action="InsertarProducto" class="mt-5">
  		<div class="form-group">
    		<div class="form-group">
		    <label>Codigo:</label>
		    <input type="text" name="codigo" required 
		           class="form-control" 
		           oninvalid="this.setCustomValidity('Por favor, ingresa una fecha válida')" 
		           onchange="this.setCustomValidity('')" /><br/>
  		</div>
  		
  		<div class="form-group">
    		<div class="form-group">
		    <label>Nombre:</label>
		    <input type="text" name="nombre" required 
		           class="form-control" 
		           oninvalid="this.setCustomValidity('Por favor, ingresa una fecha válida')" 
		           onchange="this.setCustomValidity('')" /><br/>
  		</div>
  		
  		<div class="form-group">
    		<div class="form-group">
		    <label>cantidad:</label>
		    <input type="text" name="cantidad" required 
		           class="form-control" 
		           oninvalid="this.setCustomValidity('Por favor, ingresa una fecha válida')" 
		           onchange="this.setCustomValidity('')" /><br/>
  		</div>
  		
  		<div class="form-group">
    		<div class="form-group">
		    <label>precio:</label>
		    <input type="text" name="precio" required 
		           class="form-control" 
		           oninvalid="this.setCustomValidity('Por favor, ingresa una fecha válida')" 
		           onchange="this.setCustomValidity('')" /><br/>
  		</div>
  		
  		<div class="form-group">
    		<div class="form-group">
		    <label>caducidad:</label>
		    <input type="Date" name="caducidad" required 
		           class="form-control" 
		           oninvalid="this.setCustomValidity('Por favor, ingresa una fecha válida')" 
		           onchange="this.setCustomValidity('')" /><br/>
  		</div>
  		
  		<div class="form-group">
  		<label for="seccion">Selecciona una seccion:</label>
		  <select class="form-control" id="seccion" name="id_seccion" >
		    <option value="">--Selecciona una seccion</option>
		    <c:forEach items="${secciones}" var="seccion">
		      <option value="${seccion.id}">${seccion.nombre}</option>
		    </c:forEach>
		  </select>
	</div>
  		</div>
  		</div>
  		<input type="submit" value="Guardar" class="btn btn-primary mt-4"/>
  		</form>
</body>
</html>