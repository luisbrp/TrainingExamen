package controlador;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import modelo.Producto;
import modelo.ProductoModelo;
import modelo.Supermercado;
import modelo.SupermercadoModelo;

/**
 * Servlet implementation class EliminarProducto
 */
@WebServlet("/EliminarProducto")
public class EliminarProducto extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EliminarProducto() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ProductoModelo pm = new ProductoModelo();
        int id = Integer.parseInt(request.getParameter("id"));

        pm.conectar();
        ArrayList<Producto> todosLosProductos = pm.productos();

        for (Producto producto : todosLosProductos) {
        	//comprobar que el id coincida y que la cantidad sea mayor que 0 para disminuir
            if (producto.getId() == id) {
                if (producto.getCantidad() > 0) {
                    pm.disminuirCantidad(id);
                    response.sendRedirect("VerProductos");
                    return;
                
                    /*comprobar si el arraylist de supermercados de esta vacio o no 
                     * para poder eliminar los supermercados o el producto
                     */
                } else {
                    ArrayList<Supermercado> supermercados = pm.getSupermercados(id);
                    if (supermercados.isEmpty()) {
                        pm.eliminar(id);
                        response.sendRedirect("VerProductos");
                        return;
                    } else {
                        for (Supermercado supermercado : supermercados) {
                            pm.eliminarProductoSupermercado(id, supermercado);
                        }
                        response.sendRedirect("VerProductos");
                        return;
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
