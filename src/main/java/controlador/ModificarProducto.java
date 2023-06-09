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
 * Servlet implementation class ModificarProducto
 */
@WebServlet("/ModificarProducto")
public class ModificarProducto extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ModificarProducto() {
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
		
		ProductoModelo pm = new ProductoModelo();
		Producto producto = new Producto();
		
		int id = Integer.parseInt(request.getParameter("id"));
		
		pm.conectar();
		producto = pm.get(id);

		pm.cerrar();

		request.setAttribute("producto", producto);
		request.setAttribute("secciones", secciones);
		request.getRequestDispatcher("ModificarProducto.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ProductoModelo pm = new ProductoModelo();
		Producto producto = new Producto();
		SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
		
		int id = Integer.parseInt(request.getParameter("id"));
		String codigo = request.getParameter("codigo");
		String nombre = request.getParameter("nombre");
		int cantidad = Integer.parseInt(request.getParameter("cantidad"));
		double precio = Double.parseDouble(request.getParameter("precio"));
		String caducidadP = request.getParameter("caducidad");
		int  id_seccion = Integer.parseInt(request.getParameter("id_seccion"));
		
		try {
			producto.setId(id);
			producto.setCodigo(codigo);
			producto.setNombre(nombre);
			producto.setCantidad(cantidad);
			producto.setPrecio(precio);
			Date caducidad = formato.parse(caducidadP);
			producto.setCaducidad(caducidad);
			Seccion seccion = new Seccion();
			seccion.setId(id_seccion);
			producto.setSeccion(seccion);
			
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		pm.conectar();
		pm.modificar(producto);
		pm.cerrar();
		
		response.sendRedirect("VerProductos");
	}
	

}
