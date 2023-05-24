package modelo;

import java.util.ArrayList;
import java.util.Date;

public class Producto  {
	@Override
	public String toString() {
		return "Producto [id=" + id + ", codigo=" + codigo + ", nombre=" + nombre + ", precio=" + precio + ", cantidad="
				+ cantidad + ", caducidad=" + caducidad + ", nombreSeccion=" + nombreSeccion + ", seccion=" + seccion
				+ ", supermercados=" + supermercados + "]";
	}
	private int id;
	private String codigo;
	private String nombre;
	private Double precio;
	private int cantidad;
	private Date caducidad;
	private String nombreSeccion;
	
	public String getNombreSeccion() {
		return nombreSeccion;
	}
	public void setNombreSeccion(String nombreSeccion) {
		this.nombreSeccion = nombreSeccion;
	}
	public Date getCaducidad() {
		return caducidad;
	}
	public void setCaducidad(Date caducidad) {
		this.caducidad = caducidad;
	}
	private Seccion seccion;
	private ArrayList<Supermercado> supermercados;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public Double getPrecio() {
		return precio;
	}
	public void setPrecio(Double precio) {
		this.precio = precio;
	}
	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	public Seccion getSeccion() {
		return seccion;
	}
	public void setSeccion(Seccion seccion) {
		this.seccion = seccion;
	}
	public ArrayList<Supermercado> getSupermercados() {
		return supermercados;
	}
	public void setSupermercados(ArrayList<Supermercado> supermercados) {
		this.supermercados = supermercados;
	}
	
}
