package controlador;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import modelo.Producto;
import modelo.ProductoModelo;

/**
 * Servlet implementation class EliminarMultiple
 */
@WebServlet("/EliminarMultiple")
public class EliminarMultiple extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EliminarMultiple() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ProductoModelo pm = new ProductoModelo();
		boolean error = false;
		String codigosString = request.getParameter("codigos");
		
		pm.conectar();
		ArrayList<Producto> TodosLosProductos = pm.productos();
		if(codigosString == null) {
			request.setAttribute("TodosLosProductos", TodosLosProductos);
			request.getRequestDispatcher("VerProductos.jsp").forward(request, response);
			
		} else {
			String[] CodigoPartes = codigosString.split(",");
			
			if(CodigoPartes != null) {
				
				for (Producto producto : TodosLosProductos) {
					 for (String codigo : CodigoPartes) {
						 if(producto.getCodigo().contains(codigo)) {
							 pm.eliminarCodigoString(CodigoPartes);
							 request.setAttribute("TodosLosProductos", TodosLosProductos);
							 request.getRequestDispatcher("VerProductos.jsp").forward(request, response);
						 }  else if (!producto.getCodigo().contains(codigo)) {
							 error = true;
							 request.setAttribute("error", error);
							 request.setAttribute("TodosLosProductos", TodosLosProductos);
							 request.getRequestDispatcher("VerProductos.jsp").forward(request, response);
						 }
					}
				}
				
			} 	
		}
		pm.cerrar();
		
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
