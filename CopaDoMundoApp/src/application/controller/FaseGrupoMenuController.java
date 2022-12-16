/**
 * Sample Skeleton for 'FaseDeGruposPontos.fxml' Controller Class
 */

package application.controller;

import java.net.URL;
import java.util.ResourceBundle;

import application.model.MataMata;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;

public class FaseGrupoMenuController {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;
    
    @FXML
    private BorderPane mainPane;
    
    void setMainPane(BorderPane mainPane) {
    	this.mainPane = mainPane;
    }

    @FXML
    void gerenciarPartidas(MouseEvent event) {
    	this.openPage("/application/view/PartidasFaseDeGrupo.fxml");
    }

    @FXML
    void verGrupos(MouseEvent event) {
    	this.openPage("/application/view/FaseDeGruposPontos.fxml");
    }
    
    private void openPage(String url) {
    	Parent root = null;
    	
    	try {
    		root = FXMLLoader.load(getClass().getResource(url));
    	} catch (Exception e) {

    	}
    	this.mainPane.setCenter(root);

    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {

    }

}
