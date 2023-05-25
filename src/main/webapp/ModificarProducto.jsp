<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

	<form method="post" action="ModificarProducto">
    		<div class="form-group">
			    <label>Id:</label>
			    	<input type="text" name="id" required 
			           class="form-control" 
			           oninvalid="this.setCustomValidity('Por favor, ingresa una fecha válida')" 
			           onchange="this.setCustomValidity('')"
			           value="${producto.id}" /><br/>
  			</div>
    		
    		<div class="form-group">
			    <label>Codigo:</label>
			    	<input type="text" name="codigo" required 
			           class="form-control" 
			           oninvalid="this.setCustomValidity('Por favor, ingresa una fecha válida')" 
			           onchange="this.setCustomValidity('')"
			           value="${producto.codigo}" /><br/>
  			</div>
  		
  		 	
    		<div class="form-group">
		    	<label>Nombre:</label>
				    	<input type="text" name="nombre" required 
				           class="form-control" 
				           oninvalid="this.setCustomValidity('Por favor, ingresa una fecha válida')" 
				           onchange="this.setCustomValidity('')"
				           value="${producto.nombre}" /><br/>
  			</div>
  		
  	
    		<div class="form-group">
		    	<label>cantidad:</label>
				    <input type="text" name="cantidad" required 
				           class="form-control" 
				           oninvalid="this.setCustomValidity('Por favor, ingresa una fecha válida')" 
				           onchange="this.setCustomValidity('')"
				           value="${producto.cantidad}" /><br/>
  			</div>
  		
  		
    		<div class="form-group">
		    	<label>precio:</label>
				    <input type="text" name="precio" required 
				           class="form-control" 
				           oninvalid="this.setCustomValidity('Por favor, ingresa una fecha válida')" 
				           onchange="this.setCustomValidity('')" 
				           value="${producto.precio}"/><br/>
  			</div>
  		
  		
    		<div class="form-group">
		    	<label>caducidad:</label>
				    <input type="Date" name="caducidad" required 
				           class="form-control" 
				           oninvalid="this.setCustomValidity('Por favor, ingresa una fecha válida')" 
				           onchange="this.setCustomValidity('')"
				           value="${producto.caducidad}"/><br/>
  			</div>
  		
	  		<div class="form-group">
  				<label for="seccion">Selecciona una seccion:</label>
					  <select class="form-control" id="seccion" name="id_seccion"  required>
					    <option value="">--Selecciona una seccion</option>
					    <c:forEach items="${secciones}" var="seccion">
					      <option value="${seccion.id}">${seccion.nombre}</option>
					    </c:forEach>
					 </select>
  			</div>
  		<input type="submit" value="Guardar" class="btn btn-primary mt-4"/>
  		</form>
</body>
</html>