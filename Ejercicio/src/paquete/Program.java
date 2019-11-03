package paquete;

import Servicios.Consultas;

public class Program {
	public static void main(String[] args) {
//		Persona p = new Persona(4,"Jose","Diaz",8891);
//		Consultas.guardar(p);
//		Persona p = new Persona(10,"Pedro","Gonzalez",1234);
//		Consultas.guardar(p);
//		Persona p2 = (Persona) Consultas.obtenerPorId(Persona.class, 10 );
//		System.out.println(p2.toString());
		
		Persona p3 = new Persona(10,"PedroModificado","GonzalezModificado",123456);
		Consultas.modificar(p3);
		
		
//		Consultas.eliminar(p2);
	//	pdao.alta(new Persona(65,"Ramon","Diaz",15698774));
//		Persona p = pdao.obtenerPorId(65);
//		System.out.println(p.toString());
//		p.setNombre("Ramonazo");
//		pdao.modificar(p);
		
//		Conexion.cerrarConexion();
	}

}
