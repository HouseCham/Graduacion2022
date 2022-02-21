package data;

public class Graduado {
	
	private String nombre, carrera, correo, matricula, telefono;
	
	public Graduado(String nombre, String carrera, String correo, String matricula, String telefono) {
		this.nombre = nombre;
		this.carrera = carrera;
		this.correo = correo;
		this.matricula = matricula;
		this.telefono = telefono;
	}
	
	public String getNombre() {
		return nombre;
	}
	public String getCarrera() {
		return carrera;
	}
	public String getCorreo() {
		return correo;
	}
	public String getMatricula() {
		return matricula;
	}
	public String getTelefono() {
		return telefono;
	}
}
