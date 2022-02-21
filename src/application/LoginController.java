package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class LoginController {
	@FXML
	TextField userField;
	@FXML
	PasswordField passwordField;
	@FXML
	Button exitButton;
	@FXML
	BorderPane loginBorderPane;
	
	public void login() {
		
		String username = userField.getText();
		String password = passwordField.getText();
		
		if(!username.equals("") && !password.equals("")){
			
			try {
				Connection connection = DatabaseConnection.conectar();
				PreparedStatement sqlStatement = connection.prepareStatement("SELECT * FROM `admins` "
						+ "WHERE username = \"" + username + "\" AND password = \"" + password + "\"");
				ResultSet resultSet = sqlStatement.executeQuery();
				
				if (resultSet.next()) {
					openAdminInterface();
					closeLogin();
				}
				
			} catch(Exception e) {
				e.printStackTrace();
				e.getCause();
			}
		}   
	}

	public void recoverPassword() {
		
	}

	public void closeLogin() {
		Stage loginStage = (Stage) loginBorderPane.getScene().getWindow();
		loginStage.close();
	}
	
	public void openAdminInterface() {
		
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/UserInterface.fxml"));
			Parent root = loader.load();
			Scene adminScene = new Scene(root);
			adminScene.getStylesheets().add(getClass().getResource("tableView.css").toExternalForm());
			
			Main.getAdminStage().setScene(adminScene);
			AdminController adminController = loader.getController();
			
			Main.getAdminStage().getIcons().add(new Image(Main.class.getResourceAsStream("/Grad_Logo.png")));
			Main.getAdminStage().setTitle("Admin Interface");
			Main.getAdminStage().initStyle(StageStyle.UNDECORATED);
			Main.getAdminStage().show();
			
		} catch(Exception e) {
			System.out.println(e);
		}
	}
}
