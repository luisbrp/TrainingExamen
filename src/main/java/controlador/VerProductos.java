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

        // Comprobar si llega la cadena para el buscador
        if (cadena != null && !cadena.isEmpty()) {
            pm.conectar();
            ArrayList<Producto> productos = pm.productosConNombreSeccion();
            pm.cerrar();

            ArrayList<Producto> productosEncontrados = new ArrayList<Producto>();
            for (Producto producto : productos) {
                if (producto.getCodigo().toLowerCase().contains(cadena.toLowerCase()) || producto.getNombre().toLowerCase().contains(cadena.toLowerCase())) {
                    productosEncontrados.add(producto);
                }
            }

            request.setAttribute("productosEncontrados", productosEncontrados);
            request.getRequestDispatcher("VerProductos.jsp").forward(request, response);
        } else {
            pm.conectar();
            ArrayList<Producto> productos = pm.productosConNombreSeccion();
            pm.cerrar();

            request.setAttribute("productos", productos);
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
