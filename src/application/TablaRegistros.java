package application;

public class TablaRegistros {
	
	private String id, nombre, carrera, matricula, telefono, fecha, monto;
	
	public TablaRegistros(String id, String nombre, String carrera, String matricula, String telefono, String fecha, String monto) {
		this.setId(id);
		this.setNombre(nombre);
		this.setCarrera(carrera);
		this.setMatricula(matricula);
		this.setTelefono(telefono);
		this.setFecha(fecha);
		this.setMonto(monto);
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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
