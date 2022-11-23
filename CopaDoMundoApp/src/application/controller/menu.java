package application.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

public class menu {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private HBox bttinicio;
    
    @FXML
    private BorderPane mainpane;

    @FXML
    void pageinicio(MouseEvent event) {
    	this.openPage("/application/view/HomePage.fxml");
    }
    
    @FXML
    void initialize() {
        assert bttinicio != null : "fx:id=\"bttinicio\" was not injected: check your FXML file 'HomePage.fxml'.";

    }

    private void openPage(String url) {
    	Parent root = null;
    	try {
    		root = FXMLLoader.load(getClass().getResource(url));
    	} catch (Exception e) {

    	}
    	this.mainpane.setCenter(root);;
    }
}
