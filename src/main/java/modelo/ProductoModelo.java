package modelo;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ProductoModelo extends Conector {

	public ArrayList<Producto> productos() {
		SeccionModelo sm = new SeccionModelo();
		sm.setCon(con);
		String sql = "SELECT * FROM productos";
		Statement st;

		ArrayList<Producto> productos = new ArrayList<Producto>();
		try {
			st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);

			Producto p;
			while (rs.next()) {
				p = new Producto();
				p.setId(rs.getInt("id"));
				p.setNombre(rs.getString("nombre"));
				p.setCodigo(rs.getString("codigo"));
				p.setPrecio(rs.getDouble("precio"));
				p.setCantidad(rs.getInt("cantidad"));
				p.setSeccion(sm.seccion(rs.getInt("id_seccion")));
				
				Date fechaCaducidad = rs.getDate("caducidad");
	            if (rs.wasNull()) {
	                p.setCaducidad(null);
	            } else {
	                p.setCaducidad(new Date(fechaCaducidad.getTime()));
	            }

				productos.add(p);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return productos;
	}
	
	public ArrayList<Producto> productosConNombreSeccion() {
	    SeccionModelo sm = new SeccionModelo();
	    sm.setCon(con);
	    String sql = "SELECT p.*, s.nombre FROM productos p JOIN secciones s on s.id = p.id_seccion";
	    Statement st;

	    ArrayList<Producto> productos = new ArrayList<Producto>();
	    try {
	        st = con.createStatement();
	        ResultSet rs = st.executeQuery(sql);

	        Producto p;
	        while (rs.next()) {
	            p = new Producto();
	            p.setId(rs.getInt("id"));
	            p.setNombre(rs.getString("nombre"));
	            p.setCodigo(rs.getString("codigo"));
	            p.setPrecio(rs.getDouble("precio"));
	            p.setCantidad(rs.getInt("cantidad"));
	            p.setSeccion(sm.seccion(rs.getInt("id_seccion")));

	            Date fechaCaducidad = rs.getDate("caducidad");
	            if (rs.wasNull()) {
	                p.setCaducidad(null);
	            } else {
	                p.setCaducidad(new Date(fechaCaducidad.getTime()));
	            }

	            p.setNombreSeccion(rs.getString(8));
	            productos.add(p);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return productos;
	}


	public boolean registrar(Producto producto) {
		PreparedStatement pst = null;
		String sql = "INSERT INTO productos(codigo, nombre, precio, cantidad, caducidad )VALUES(?, ?, ?, ?, ?)";
		try {
			pst = con.prepareStatement(sql);

			pst.setString(1, producto.getCodigo());
			pst.setString(2, producto.getNombre());
			pst.setDouble(3, producto.getPrecio());
			pst.setInt(4, producto.getCantidad());
			pst.setDate(5, new Date (producto.getCaducidad().getTime()));
			pst.execute();
			return true;

		} catch (SQLException e) {
			e.printStackTrace();
			System.err.println(e);
			return false;
		}
	}

	public boolean modificar(Producto producto) {
		String sql = "UPDATE productos SET codigo=?, nombre=?, cantidad=?, precio=?, id_seccion=? WHERE id=?";
		PreparedStatement pst;
		try {
			pst = con.prepareStatement(sql);
			pst.setString(1, producto.getCodigo());
			pst.setString(2, producto.getNombre());
			pst.setInt(3, producto.getCantidad());
			pst.setDouble(4, producto.getPrecio());
			pst.setInt(5, producto.getSeccion().getId());
			pst.setInt(6, producto.getId());
			pst.execute();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

	}

	/*
	 * producto viene con codigo relleno elimin el procducto con ese código
	 */
	public boolean eliminar(Producto producto) {
		PreparedStatement pst = null;
		String sql = "DELETE FROM productos WHERE codigo = ?";
		try {
			pst = con.prepareStatement(sql);
			pst.setString(1, producto.getCodigo());
			pst.execute();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean eliminar(int id) {
		PreparedStatement pst = null;
		String sql = "DELETE FROM productos WHERE id = ?";
		try {
			pst = con.prepareStatement(sql);
			pst.setInt(1, id);
			pst.execute();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean buscar(Producto producto) {
		return false;
	}

	public Producto get(int id) {
		String sql = "select * from productos where id = ?";
		PreparedStatement pst;
		try {
			pst = con.prepareStatement(sql);
			pst.setInt(1, id);

			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				Producto producto = new Producto();
				producto.setId(rs.getInt("id"));
				producto.setCodigo(rs.getString("codigo"));
				producto.setNombre(rs.getString("nombre"));
				producto.setCantidad(rs.getInt("cantidad"));
				producto.setPrecio(rs.getDouble("precio"));
				producto.setCaducidad(new Date(rs.getDate("Caducidad").getTime()));
				Seccion s = new Seccion();
				s.setId(rs.getInt("id_seccion"));
				producto.setSeccion(s);
				producto.setSupermercados(getSupermercados(rs.getInt("id")));
				return producto;
			}

			return null;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	private ArrayList<Supermercado> getSupermercados(int idProducto) {
		ArrayList<Supermercado> supermercados = new ArrayList<Supermercado>();
		String sql = "select * from productos_supermercados where id_producto = ?";
		PreparedStatement pst;

		ResultSet rs;
		try {
			pst = con.prepareStatement(sql);
			pst.setInt(1, idProducto);
			rs = pst.executeQuery();
			
			Supermercado supermercado;
			while (rs.next()) {
				supermercado = new Supermercado();
				supermercado.setId(rs.getInt("id_supermercado"));
				
				supermercados.add(supermercado);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return supermercados;

		

	}

	public Producto buscar(String codigo) {
		String sql = "select * from productos where codigo = ?";
		PreparedStatement pst;
		try {
			pst = con.prepareStatement(sql);
			pst.setString(1, codigo);

			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				Producto producto = new Producto();
				producto.setId(rs.getInt("id"));
				producto.setCodigo(rs.getString("codigo"));
				producto.setNombre(rs.getString("nombre"));
				producto.setCantidad(rs.getInt("cantidad"));
				producto.setPrecio(rs.getDouble("precio"));
				return producto;
			}

			return null;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public void insertar(Producto producto) {
		String sql = "INSERT INTO productos(codigo, nombre, cantidad, precio, caducidad, id_seccion) VALUES (?, ?, ?, ?, ?, ?)";
		PreparedStatement pst;
		try {
			pst = con.prepareStatement(sql);
			pst.setString(1, producto.getCodigo());
			pst.setString(2, producto.getNombre());
			pst.setInt(3, producto.getCantidad());
			pst.setDouble(4, producto.getPrecio());
			pst.setDate(5, new Date(producto.getCaducidad().getTime()));
			pst.setInt(6, producto.getSeccion().getId());

			pst.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
	
	public void insertarProductoSupermercado(Producto productoConId, int id_supermercado) {
		String sql = "INSERT INTO productos_supermercados (id_producto, id_supermercado) VALUES (?, ?)";
		PreparedStatement pst;
		try {
			pst = con.prepareStatement(sql);
			pst.setInt(1, productoConId.getId());
			pst.setInt(2, id_supermercado);
			
			pst.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public boolean productoEnSupermercado(int idProducto, int idSupermercado) {
		PreparedStatement pst = null;
		String sql = "INSERT INTO productos_supermercados (id_producto, id_supermercado) VALUES (?, ?)";
		try {
			pst = con.prepareStatement(sql);

			pst.setInt(1, idProducto);
			pst.setInt(2, idSupermercado);

			pst.execute();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean productoSupermercados(int idProducto, int[] idsSupermercados) {
		for (int i = 0; i < idsSupermercados.length; i++) {
			if (!productoEnSupermercado(idProducto, idsSupermercados[i])) {
				return false; // error en algun insert
			}
		}
		return true;
	}
	
	public String getCodigo (String codigo) {
		String codigoBBDD = null;
		String sql = "SELECT codigo FROM productos WHERE codigo = ?";
		PreparedStatement pst;
		try {
			pst = con.prepareStatement(sql);
			pst.setString(1, codigo);
			
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				codigoBBDD = rs.getString("codigo");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return codigoBBDD;
	}

	public Producto productoConId(Producto producto) {
		String sql = "SELECT * FROM productos WHERE codigo = ?";
		PreparedStatement pst;
		try {
			pst = con.prepareStatement(sql);
			pst.setString(1, producto.getCodigo());
			
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				Producto productoConId = new Producto();
				productoConId.setId(rs.getInt("id"));
				productoConId.setCodigo(rs.getString("codigo"));
				productoConId.setNombre(rs.getString("nombre"));
				productoConId.setCantidad(rs.getInt("cantidad"));
				productoConId.setPrecio(rs.getDouble("precio"));
				return productoConId;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;

		}

		public void disminuirCantidad(int id) {
			String sql = "UPDATE productos SET cantidad= (SELECT cantidad FROM productos where id = ?) - 1 WHERE id = ?";
			PreparedStatement pst;
				try {
					pst = con.prepareStatement(sql);
					pst.setInt(1, id);
					pst.setInt(2, id);
					pst.execute();
				} catch (SQLException e) {
					e.printStackTrace();
				}		
		} 
	}
