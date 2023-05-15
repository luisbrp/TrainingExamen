package modelo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class SupermercadoModelo extends Conector{
	
	public ArrayList<Supermercado> supermercados() {
		String sql = "SELECT * FROM supermercados";
		Statement st;

		ArrayList<Supermercado> supermercados = new ArrayList<Supermercado>();
		try {
			st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);

			Supermercado s;
			while (rs.next()) {
				s = new Supermercado();
				s.setId(rs.getInt("id"));
				s.setNombre(rs.getString("nombre"));
				
				supermercados.add(s);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return supermercados;
	}
	
	
}
