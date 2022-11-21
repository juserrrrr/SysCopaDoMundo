package application;
	
import java.net.URL;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			FXMLLoader loader = new FXMLLoader();
			URL xmlURL = getClass().getResource("/application/view/HomePage.fxml");
			loader.setLocation(xmlURL);
			Parent root = loader.load();
			Scene scene = new Scene(root,800,600);
			primaryStage.setTitle("Tela inicial");
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		launch(args);
	}
}
