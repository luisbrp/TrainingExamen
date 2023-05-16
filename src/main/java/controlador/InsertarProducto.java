package controlador;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import modelo.Producto;
import modelo.ProductoModelo;
import modelo.Seccion;
import modelo.SeccionModelo;

/**
 * Servlet implementation class InsertarUsuario
 */
@WebServlet("/InsertarProducto")
public class InsertarProducto extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InsertarProducto() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		SeccionModelo sm = new SeccionModelo();
		ArrayList<Seccion> secciones = new ArrayList<Seccion>();
		
		sm.conectar();
		secciones = sm.secciones();
		sm.cerrar();
		
		request.setAttribute("secciones", secciones);
		request.getRequestDispatcher("InsertarProducto.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ProductoModelo pm = new ProductoModelo();
		Producto producto = new Producto();
		SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
		
		
		String codigo = request.getParameter("codigo");
		String nombre = request.getParameter("nombre");
		int cantidad = Integer.parseInt(request.getParameter("cantidad"));
		double precio = Double.parseDouble(request.getParameter("precio"));
		String caducidadP = request.getParameter("caducidad");
		String id_seccionString = request.getParameter("id_seccion");
		
		
		//Consultar codigo en la base de datos
		String codigoConsultado = null;
		
		pm.conectar();
		codigoConsultado = pm.getCodigo(codigo);
		pm.cerrar();
		
		if (codigoConsultado == codigo) {
			producto.setCodigo(codigo);
		} else {
			response.sendRedirect("InsertarProducto");
		}
		producto.setNombre(nombre);
		
		//Precio y cantidad positivos
		if (cantidad > 0) {
			producto.setCantidad(cantidad);
		} else {
			response.sendRedirect("InsertarProducto");
		}
		
		if (precio > 0) {
			producto.setPrecio(precio);
		} else {
			response.sendRedirect("InsertarProducto");
		}
		
		try {
			
			Date caducidad = formato.parse(caducidadP);
			java.util.Date fechaActual = new Date();
			
			//fecha posterior a la actual
			if (caducidad.after(fechaActual)) {
				producto.setCaducidad(caducidad);
			} else {
				response.sendRedirect("InsertarProducto");
			}
			
			//sección obligatoria
			if (id_seccionString != null && !id_seccionString.isEmpty()) {
				int id_seccion = Integer.parseInt(id_seccionString);
				Seccion seccion = new Seccion();
				seccion.setId(id_seccion);
				producto.setSeccion(seccion);
			} else {
				response.sendRedirect("InsertarProducto");
			}
			
			 
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		pm.conectar();
		pm.insertar(producto);
		pm.cerrar();
	}
}
