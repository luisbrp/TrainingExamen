package controlador;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import modelo.Producto;
import modelo.ProductoModelo;

/**
 * Servlet implementation class principal
 */
@WebServlet("/VerProductos")
public class VerProductos extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public VerProductos() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ProductoModelo pm = new ProductoModelo();

        String cadena = request.getParameter("cadena");
        String precioMin = request.getParameter("precioMin");
        String precioMax = request.getParameter("precioMax");

        pm.conectar();
        ArrayList<Producto> TodosLosProductos = pm.productosConNombreSeccion();
        pm.cerrar();

        ArrayList<Producto> productosFiltrados = new ArrayList<Producto>();
        
        //Recargar
        String recargar = request.getParameter("recargar");
        boolean recargarProductos = (recargar != null && recargar.equals("true"));

        if (recargarProductos) {
            response.sendRedirect("VerProductos");
            return;
        }

        // Filtrar por precio min y max
        if (precioMin != null && !precioMin.isEmpty() || precioMax != null && !precioMax.isEmpty()) {
	            double min = Double.MIN_VALUE;
	            double max = Double.MAX_VALUE;

                min = Double.parseDouble(precioMin);
                max = Double.parseDouble(precioMax);

            for (Producto producto : TodosLosProductos) {
                double precio = producto.getPrecio();
                if (precio >= min && precio <= max) {
                    productosFiltrados.add(producto);
                }
            }
                request.setAttribute("productosFiltrados", productosFiltrados);
                request.getRequestDispatcher("VerProductos.jsp").forward(request, response);
        } else {
            productosFiltrados.addAll(TodosLosProductos);
        }

        //comprobar que la cadena para el buscador no sea null
        if (cadena != null && !cadena.isEmpty()) {
            ArrayList<Producto> productosEncontrados = new ArrayList<Producto>();
            for (Producto producto : TodosLosProductos) {
                if (producto.getCodigo().toLowerCase().contains(cadena.toLowerCase()) || producto.getNombre().toLowerCase().contains(cadena.toLowerCase())) {
                    productosEncontrados.add(producto);
                }
            }
            request.setAttribute("productosEncontrados", productosEncontrados);
            request.getRequestDispatcher("VerProductos.jsp").forward(request, response);
        } else {
        	 pm.conectar();
             TodosLosProductos = pm.productosConNombreSeccion(); 
             pm.cerrar();
             
             request.setAttribute("TodosLosProductos", TodosLosProductos);
             request.getRequestDispatcher("VerProductos.jsp").forward(request, response);
        }
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
