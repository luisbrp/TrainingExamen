package controlador;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import modelo.Producto;
import modelo.ProductoModelo;

/**
 * Servlet implementation class OrdenAscendente
 */
@WebServlet("/OrdenAscendente")
public class OrdenAscendente extends HttpServlet implements Comparator<Producto>{
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OrdenAscendente() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 ProductoModelo pm = new ProductoModelo();
		 
		 pm.conectar();
	     ArrayList<Producto> TodosLosProductos = pm.productosConNombreSeccion();
	     TodosLosProductos.sort(this);
	     pm.cerrar();
	     
	     
	     request.setAttribute("TodosLosProductos", TodosLosProductos);
	     request.getRequestDispatcher("VerProductos.jsp").forward(request, response);  
	}
	    
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	@Override
	public int compare(Producto o1, Producto o2) {
		Producto producto1 = (Producto) o1;
		Producto producto2 = (Producto) o2;
		return (producto1.getCodigo().compareTo(producto2.getCodigo()));
	}

}
