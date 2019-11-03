package paquete;

import Anotaciones.Columna;
import Anotaciones.Id;
import Anotaciones.Tabla;


@Tabla(nombre="Persona")
public class Persona {
	
	@Id
	@Columna(nombre = "id")
	private long id;
	
	@Columna(nombre="nombre")
	private String nombre;
	
	@Columna(nombre="apellido")
	private String apellido;
	
	@Columna(nombre="dni")
	private int dni;
	
	public Persona(long id, String nombre, String apellido, int dni) {
		this.id=id;
		this.nombre=nombre;
		this.apellido=apellido;
		this.dni=dni;
	}

	public Persona() {
		// TODO Auto-generated constructor stub
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public int getDni() {
		return dni;
	}

	public void setDni(int dni) {
		this.dni = dni;
	}

	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Nombre: "+ this.getNombre() +" Apellido: " + this.getApellido() + " DNI: " + this.getDni();
	}
	
	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return super.hashCode();
	}
}
