package Utilidades;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class UConexion {

	private static Connection conect;

	private UConexion(){}

	public static Connection abrirConexion(){
		ResourceBundle rb = ResourceBundle.getBundle("framework");
		if (conect == null) {
			try {
				Class.forName(rb.getString("driver"));
				conect = DriverManager.getConnection(rb.getString("url"), rb.getString("user"),rb.getString("pass"));
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}		
		}
		return conect;
	}

	public static void cerrarConexion(){
		try {
			conect.close();
			conect = null;
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
