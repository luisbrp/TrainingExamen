package modelo;

import java.util.ArrayList;

public class Supermercado {
	private int id;
	private String nombre;
	
	private ArrayList<Producto> productos = new ArrayList<Producto>();
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public ArrayList<Producto> getProductos() {
		return productos;
	}
	public void setProductos(ArrayList<Producto> productos) {
		this.productos = productos;
	}
	public void addProducto(Producto producto) {
		if(this.productos != null) {
			productos.add(producto);
		}
		
	}
	
	

}
