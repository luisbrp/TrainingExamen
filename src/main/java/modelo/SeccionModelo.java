package modelo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class SeccionModelo extends Conector{
	
	public ArrayList<Seccion> secciones() {
		String sql = "SELECT * FROM secciones";
		Statement st;

		ArrayList<Seccion> secciones = new ArrayList<Seccion>();
		try {
			st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);

			Seccion s;
			while (rs.next()) {
				s = new Seccion();
				s.setId(rs.getInt("id"));
				s.setNombre(rs.getString("nombre"));
				
				secciones.add(s);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return secciones;
	}

	public Seccion seccion(int id) {
		String sql = "select * from secciones where id = ?";
		PreparedStatement pst;
		try {
			pst = con.prepareStatement(sql);
			pst.setInt(1, id);
			
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				Seccion seccion = new Seccion();
				seccion.setId(rs.getInt("id"));
				seccion.setNombre(rs.getString("nombre"));
				return seccion;
			}
			
			return null;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

}
