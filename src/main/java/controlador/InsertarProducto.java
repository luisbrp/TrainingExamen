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
		boolean error = false;
		
		String codigo = request.getParameter("codigo");
		String nombre = request.getParameter("nombre");
		int cantidad = Integer.parseInt(request.getParameter("cantidad"));
		double precio = Double.parseDouble(request.getParameter("precio"));
		String caducidadP = request.getParameter("caducidad");
		int id_seccion = Integer.parseInt(request.getParameter("id_seccion"));
		
		
		//Consultar codigo en la base de datos
		String codigoConsultado = null;
		
		pm.conectar();
		codigoConsultado = pm.getCodigo(codigo);
		pm.cerrar();
		
		Date caducidad;
		try {
			caducidad = formato.parse(caducidadP);
			java.util.Date fechaActual = new Date();
			System.out.println(id_seccion);
			if (codigoConsultado == null || codigoConsultado.equals(codigo) && cantidad >= 0 && precio >= 0 && caducidad.after(fechaActual) && id_seccion > 0) {
				
				producto.setCodigo(codigo);
				producto.setNombre(nombre);
				producto.setCantidad(cantidad);
				producto.setPrecio(precio);
				producto.setCaducidad(caducidad);
				Seccion seccion = new Seccion();
				seccion.setId(id_seccion);
				producto.setSeccion(seccion);
				pm.conectar();
				pm.insertar(producto);
				pm.cerrar();
			} else {
				error = true;
				request.setAttribute("error", error);
				doGet(request, response);
			}
			
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
	}

}
