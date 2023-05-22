package controlador;

import java.io.IOException;
import java.util.ArrayList;
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
        ArrayList<Producto> productos = pm.productosConNombreSeccion();
        pm.cerrar();

        ArrayList<Producto> productosFiltrados = new ArrayList<Producto>();

        String recargar = request.getParameter("recargar");
        boolean recargarProductos = (recargar != null && recargar.equals("true"));

        if (recargarProductos) {
            pm.conectar();
            productos = pm.productosConNombreSeccion(); //
            pm.cerrar();
        }

        // Filtrar por precio miny max
        if (precioMin != null || precioMax != null) {
            double min = Double.MIN_VALUE;
            double max = Double.MAX_VALUE;

            if (precioMin != null) {
                min = Double.parseDouble(precioMin);
            }

            if (precioMax != null) {
                max = Double.parseDouble(precioMax);
            }

            for (Producto producto : productos) {
                double precio = producto.getPrecio();
                if (precio >= min && precio <= max) {
                    productosFiltrados.add(producto);
                }
            }
        } else {
            productosFiltrados.addAll(productos);
        }

        if (cadena != null && !cadena.isEmpty()) {
            ArrayList<Producto> productosEncontrados = new ArrayList<Producto>();
            for (Producto producto : productos) {
                if (producto.getCodigo().toLowerCase().contains(cadena.toLowerCase()) || producto.getNombre().toLowerCase().contains(cadena.toLowerCase())) {
                    productosEncontrados.add(producto);
                }
            }
            request.setAttribute("productosEncontrados", productosEncontrados);
            request.getRequestDispatcher("VerProductos.jsp").forward(request, response);
        } else {
            request.setAttribute("productosEncontrados", productosFiltrados);
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
