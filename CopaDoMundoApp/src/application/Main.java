package application;
	
import java.net.URL;

import application.model.Selecao;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.fxml.FXMLLoader;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
//		MockarValores.MockSelecoes(Selecao.selecaoDao);
//		MockarValores.MockJogadores(Selecao.selecaoDao);
//		MockarValores.MockTecnicos(Selecao.selecaoDao);
		try {
			FXMLLoader loader = new FXMLLoader();
			URL xmlURL = getClass().getResource("/application/view/Menu.fxml");
			loader.setLocation(xmlURL);
			Parent root = loader.load();
			Scene scene = new Scene(root,1000,600);
//			primaryStage.initStyle(StageStyle.UNDECORATED);
			primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("/application/view/imagens/ScLogo.png")));
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
