package Servicios;


import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Anotaciones.Columna;
import Anotaciones.Id;
import Anotaciones.Tabla;
import Utilidades.UBean;
import Utilidades.UConexion;

public class Consultas {

	public static void guardar(Object o){   // ANDA
		Class c = o.getClass();
		ArrayList<Field> att = UBean.obtenerAtributos(o);
		int contador=0;

		Tabla t = (Tabla) c.getAnnotation(Tabla.class);

		String query = "INSERT INTO "  + t.nombre()  + " (";
		for(Field f : att){
			Columna col = (Columna) f.getAnnotation(Columna.class);
			query += col.nombre()+",";
			contador++;
		}

		//elimino ultima coma
		query = query.substring(0, query.length()-1);
		query += ") VALUES (";
		for(int i=0;i<contador;i++){
			query += "?,";
		}
		//elimino ultima coma
		query = query.substring(0, query.length()-1);
		query += ")";

		System.out.println(query);
		Connection conect = UConexion.abrirConexion();
		try {
			PreparedStatement pst = conect.prepareStatement(query);
			for(int i = 1 ; i<= att.size(); i++){

				pst.setObject(i, UBean.ejecutarGet(o, att.get(i-1).getName()));

			}

			pst.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}


	public static Object obtenerPorId(Class c, Object id){ // ANDA
		Tabla t = (Tabla) c.getAnnotation(Tabla.class);
		Object o = null;
		try {
			o = c.newInstance();
		} catch (InstantiationException | IllegalAccessException e1) {
			e1.printStackTrace();
		}

		ArrayList<Field> att = UBean.obtenerAtributos(o);

		String query = "SELECT ";
		String campoId = null;

		for(Field f : att ){
			Columna col = (Columna) f.getAnnotation(Columna.class);
			Id anotacionId = (Id) f.getAnnotation(Id.class);	 		
			query += col.nombre()+",";	
			if(anotacionId != null){	 			
				campoId = col.nombre();
			}
		}
		//elimino ultima coma
		query = query.substring(0, query.length()-1);
		query += " FROM " + t.nombre() + " WHERE "+campoId+"=" + id;

		System.out.println(query);
		Connection conect = UConexion.abrirConexion();
		try {
			PreparedStatement pst = conect.prepareStatement(query);
			ResultSet rs = pst.executeQuery();
			while(rs.next()){
				int i = 1;	
				for(Field f  :  att) {								
					UBean.ejecutarSet(o, f.getName(), rs.getObject(i));
					i++;
				}							
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return o;
	}

	public static void eliminar (Object o){ // ANDA

		Class c = o.getClass();

		Tabla t = (Tabla) c.getAnnotation(Tabla.class);

		ArrayList<Field> att = UBean.obtenerAtributos(o);
		long pk = 0;
		String campoId = null;
		for (Field f : att) {
			Id id = (Id) f.getAnnotation(Id.class);
			Columna col = (Columna) f.getAnnotation(Columna.class);
			if(id != null){
				pk = (long) UBean.ejecutarGet(o, col.nombre());
				campoId = col.nombre();
			}
		}

		String query = "DELETE FROM " + t.nombre() + " WHERE "+campoId+ "="+pk;

		Connection conect = UConexion.abrirConexion();
		PreparedStatement pst;
		try {
			pst = conect.prepareStatement(query);
			pst.execute();
			System.out.println("Eliminado correctamente.");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void modificar (Object o){ //ANDA
		Class c = o.getClass();
		Tabla t = (Tabla) c.getAnnotation(Tabla.class);
		ArrayList<Field> att = UBean.obtenerAtributos(o);
		String query = "UPDATE " + t.nombre() + " SET "; 
		long id = 0;
		String campoId = null;
		for (Field f : att) {
			Id anotacionId = (Id) f.getAnnotation(Id.class);
			Columna col = (Columna) f.getAnnotation(Columna.class);

			if(anotacionId == null){
				query += col.nombre() +"='"+ UBean.ejecutarGet(o, col.nombre())+"',";
			}else{
				id = (long) UBean.ejecutarGet(o, col.nombre());
				campoId = col.nombre();
			}
		}

		//elimino ultima coma
		query = query.substring(0, query.length()-1);

		query += " WHERE " +campoId+"="+id;

		System.out.println(query);

		Connection conect = UConexion.abrirConexion();
		PreparedStatement pst;
		try {
			pst = conect.prepareStatement(query);
			pst.execute();
			System.out.println("Modificado correctamente.");
		} catch (SQLException e) {
			e.printStackTrace();
		}						
	}
}
