package application;
	
import java.net.URL;

import application.model.Selecao;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		MockarValores.MockSelecoes(Selecao.selecaoDao);
		MockarValores.MockJogadores(Selecao.selecaoDao);
		MockarValores.MockTecnicos(Selecao.selecaoDao);
		try {
			FXMLLoader loader = new FXMLLoader();
			URL xmlURL = getClass().getResource("/application/view/Menu.fxml");
			loader.setLocation(xmlURL);
			Parent root = loader.load();
			Scene scene = new Scene(root);
//			primaryStage.initStyle(StageStyle.UNDECORATED);
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
