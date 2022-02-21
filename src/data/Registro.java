package data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.text.SimpleDateFormat;
import java.util.Date;

import application.DatabaseConnection;

public class Registro {

	private String nombre, carrera, matricula, telefono, monto;

	public Registro(String nombre, String carrera, String matricula, String telefono, String monto) {
		
		this.nombre = nombre;
		this.carrera = carrera;
		this.matricula = matricula;
		this.telefono = telefono;
		this.monto = monto;
	}

	public String getNombre() {
		return nombre;
	}

	public String getCarrera() {
		return carrera;
	}

	public String getMatricula() {
		return matricula;
	}

	public String getTelefono() {
		return telefono;
	}

	public String getMonto() {
		return monto;
	}

	public void registrarDepositoDataBase() {
		try {
			Connection connection = DatabaseConnection.conectar();
			PreparedStatement sqlStatement = connection.prepareStatement("insert into registros values (?,?,?,?,?,?,?)");
			
			sqlStatement.setInt(1, 0);
			sqlStatement.setString(2, this.nombre);
			sqlStatement.setString(3, this.carrera);
			sqlStatement.setString(4, this.matricula);
			sqlStatement.setString(5, this.telefono);
			sqlStatement.setString(6, fechaActual());
			sqlStatement.setString(7, this.monto);
			
			sqlStatement.executeUpdate();
			connection.close();
			
		} catch(Exception e) {
			e.printStackTrace();
			e.getCause();
		}
	}

	public static String fechaActual() {
		Date fecha = new Date();
		SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/YYYY");
		
		return formatoFecha.format(fecha);
	}
	
}
