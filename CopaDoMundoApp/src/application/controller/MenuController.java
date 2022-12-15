package application.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

public class MenuController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private HBox bttinicio;
    
    @FXML
    private HBox bttSelecoes;
       
    @FXML
    private BorderPane mainpane;

    @FXML
    void pageInicio(MouseEvent event) {
    	this.openPage("/application/view/HomePage.fxml");
    }
    
    @FXML
    void pageSelecoes(MouseEvent event) {
    	this.openPage("/application/view/SelecoesPage.fxml");
    }
    
    @FXML
    void pageJogadores(MouseEvent event) {
    	this.openPage("/application/view/JogadoresPage.fxml");
    }
    
    @FXML
    void pageArbitros(MouseEvent event) {
    	this.openPage("/application/view/ArbitrosPage.fxml");
    }
    
    @FXML
    void pageTecnicos(MouseEvent event) {
    	this.openPage("/application/view/TecnicosPage.fxml");
    }
    
    @FXML
    void pagMataMata(MouseEvent event) {
    	this.openPage("/application/view/TecnicosPage.fxml");
    }
    
    @FXML
    void pageFaseGrupos(MouseEvent event) {
    	this.openPage("/application/view/FaseDeGruposPontos.fxml");
    }
    
    @FXML
    void initialize() {
    	this.openPage("/application/view/HomePage.fxml");
    }

    private void openPage(String url) {
    	Parent root = null;
    	try {
    		root = FXMLLoader.load(getClass().getResource(url));
    	} catch (Exception e) {

    	}
    	this.mainpane.setCenter(root);

    }
}
