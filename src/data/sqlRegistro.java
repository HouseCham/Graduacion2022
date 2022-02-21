package data;

public class sqlRegistro {
	
	private String idRegistro, nombre, carrera, matricula, telefono, fecha, monto;
	
	public sqlRegistro(String idRegistro, String nombre, String carrera, String matricula, 
			String telefono, String fecha, String monto) {
		
		this.idRegistro = idRegistro;
		this.nombre = nombre;
		this.carrera = carrera;
		this.matricula = matricula;
		this.telefono = telefono;
		this.fecha = fecha;
		this.monto = monto;
	}
	
	public String getIdRegistro() {
		return idRegistro;
	}

	public void setIdRegistro(String idRegistro) {
		this.idRegistro = idRegistro;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getCarrera() {
		return carrera;
	}

	public void setCarrera(String carrera) {
		this.carrera = carrera;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public String getMonto() {
		return monto;
	}

	public void setMonto(String monto) {
		this.monto = monto;
	}
	
}
