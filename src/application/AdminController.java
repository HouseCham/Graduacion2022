package application;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import data.Registro;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class AdminController implements Initializable {

	@FXML
	BorderPane adminBorderPane;
	@FXML
	private TextField buscarRegistroField, nombreField, carreraField, matriculaField, telField, montoField;
	@FXML
	private Button botonBuscar;
	@FXML
	private Button mainButton, newRegistroButton, registroButton, configButton, more_InfoButton;
	@FXML
	private Button logoutButton;
	@FXML
	private AnchorPane registroPane, mainPane, infoPane, newRegisterPane, tablaRegistroPane;
	@FXML
	private TableView<TablaRegistros> tablaRegistro;
	@FXML
	private Text usenameTag, etiquetaStatus;
	@FXML
	private TableColumn<TablaRegistros, String> idColumn, nombreColumn, carreraColumn, matriculaColumn, montoColumn, fechaColumn; 
	
	ObservableList<TablaRegistros> oblist = FXCollections.observableArrayList();

	@FXML
	protected void adminLogout() {
		Alert closeLoginAlert = new Alert(AlertType.CONFIRMATION);
		closeLoginAlert.setTitle("Cerrar sesión");
		closeLoginAlert.setHeaderText(null);
		closeLoginAlert.setContentText("¿Estás seguro de querer salir?");

		if (closeLoginAlert.showAndWait().get() == ButtonType.OK) {

			Stage loginStage = (Stage) adminBorderPane.getScene().getWindow();
			loginStage.close();
		}
	}

	@FXML
	private void cambiarInterfaz(ActionEvent event) {

		if (event.getSource() == mainButton) {
			etiquetaStatus.setText("Principal");
			registroPane.setBackground(
					new Background(new BackgroundFill(Color.rgb(236, 139, 128), CornerRadii.EMPTY, Insets.EMPTY)));
			mainPane.toFront();

		} else if (event.getSource() == newRegistroButton) {
			etiquetaStatus.setText("Nuevo registro");
			registroPane.setBackground(
					new Background(new BackgroundFill(Color.rgb(250, 128, 114), CornerRadii.EMPTY, Insets.EMPTY)));
			newRegisterPane.toFront();

		} else if (event.getSource() == registroButton) {
			etiquetaStatus.setText("Registros");
			registroPane.setBackground(
					new Background(new BackgroundFill(Color.rgb(255, 114, 97), CornerRadii.EMPTY, Insets.EMPTY)));
			tablaRegistroPane.toFront();

		} else if (event.getSource() == more_InfoButton) {
			etiquetaStatus.setText("Acerca de");
			registroPane.setBackground(
					new Background(new BackgroundFill(Color.rgb(205, 92, 92), CornerRadii.EMPTY, Insets.EMPTY)));
			infoPane.toFront();
		}
	}

	@FXML
	private void guardarNuevoRegistro() {
		if (!nombreField.equals("") && !carreraField.equals("") && !matriculaField.equals("") && !telField.equals("")
				&& !montoField.equals("")) {
			String nombre = nombreField.getText();
			String carrera = carreraField.getText();
			String matricula = matriculaField.getText();
			String telefono = telField.getText();
			String monto = montoField.getText();

			Registro registro = new Registro(nombre, carrera, matricula, telefono, monto);
			registro.registrarDepositoDataBase();

			limpiarRegistro();
		}
	}

	private void limpiarRegistro() {
		nombreField.setText("");
		carreraField.setText("");
		matriculaField.setText("");
		telField.setText("");
		montoField.setText("");
	}
	

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		try {
			Connection connection = DatabaseConnection.conectar();
			ResultSet resulset = connection.createStatement().executeQuery("SELECT * FROM `registros`");
			
			while(resulset.next()) {
				oblist.add(new TablaRegistros(resulset.getString("id_registro"), resulset.getString("nombre"), 
						resulset.getString("carrera"), resulset.getString("matricula"), resulset.getString("telefono"), 
						resulset.getString("fecha"), resulset.getString("monto")));
			}
			
			idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
			nombreColumn.setCellValueFactory(new PropertyValueFactory<>("nombre"));
			carreraColumn.setCellValueFactory(new PropertyValueFactory<>("carrera"));
			matriculaColumn.setCellValueFactory(new PropertyValueFactory<>("matricula"));
			fechaColumn.setCellValueFactory(new PropertyValueFactory<>("fecha"));
			montoColumn.setCellValueFactory(new PropertyValueFactory<>("monto"));
			
			tablaRegistro.setItems(oblist);
			
			//initial filtered list
			FilteredList<TablaRegistros> buscarInfo = new FilteredList<>(oblist, b -> true);
			buscarRegistroField.textProperty().addListener((observable, oldValue, newValue) -> {
				buscarInfo.setPredicate(TablaRegistros -> {
					
					//Si no hay búsqueda activa, se muestran los datos por default
					if(newValue.isEmpty() || newValue == null) {
						return true;
					}
					
					String searchKeyword = newValue.toLowerCase();
					
					if(TablaRegistros.getNombre().toLowerCase().indexOf(searchKeyword) != -1) {
						return true;	//Se encontró búsqueda por nombre
						
					} else if(TablaRegistros.getCarrera().toLowerCase().indexOf(searchKeyword) != -1){
						return true;
					} else if(TablaRegistros.getMatricula().toLowerCase().indexOf(searchKeyword) != -1) {
						return true;
					} else if(TablaRegistros.getFecha().toLowerCase().indexOf(searchKeyword) != -1) {
						return true;
					} else {
						return false;	//No se encontró nada
					}
					
				});
			});
			
			SortedList<TablaRegistros> sortedData = new SortedList<>(buscarInfo);
			
			//Enlazamos  sortedlist con tableview
			sortedData.comparatorProperty().bind(tablaRegistro.comparatorProperty());
			
			//Actualizamos la tabla con los datos de búsqueda
			tablaRegistro.setItems(sortedData);
			
		} catch(SQLException e) {
			e.printStackTrace();
			e.getCause();
		} 
	}
}
