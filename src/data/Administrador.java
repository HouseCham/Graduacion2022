package data;

public class Administrador {
	
	private String nombre, carrera, correo, matricula, telefono, username, contrase�a;
	
	public Administrador(String nombre, String carrera, String correo, 
			String matricula, String telefono, String username, String  contrase�a) {
		
		this.nombre = nombre;
		this.carrera = carrera;
		this.correo = correo;
		this.matricula = matricula;
		this.telefono = telefono;
		this.username = username;
		this.contrase�a = contrase�a;
	}

	public String getNombre() {
		return nombre;
	}

	public String getMatricula() {
		return matricula;
	}

	public String getCarrera() {
		return carrera;
	}

	public String getCorreo() {
		return correo;
	}

	public String getTelefono() {
		return telefono;
	}

	public String getUsername() {
		return username;
	}

	public String getContrase�a() {
		return contrase�a;
	}
}
