package application.controller;

import java.net.URL;
import java.util.ResourceBundle;

import application.model.FaseDeGrupo;
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
    	if(FaseDeGrupo.faseDeGrupo.isIniciada()) {
    		this.openPageFaseController("/application/view/FaseDeGruposMenu.fxml");
    	}else {
    		this.openPageFaseIniciarController("/application/view/ComecarFaseDeGrupo.fxml");
    	}
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
    private void openPageFaseController (String url) {
    	Parent root = null;
    	
    	try {
    		FXMLLoader loader = new FXMLLoader();
    		URL xmlURL = getClass().getResource(url);
    		loader.setLocation(xmlURL);    		
    		root = loader.load();
    		FaseGrupoMenuController controler = loader.getController();
    		controler.setMainPane(mainpane);
    	} catch (Exception e) {
    		
    	}
    	this.mainpane.setCenter(root);
    	
    }
    private void openPageFaseIniciarController (String url) {
    	Parent root = null;
    	
    	try {
    		FXMLLoader loader = new FXMLLoader();
    		URL xmlURL = getClass().getResource(url);
    		loader.setLocation(xmlURL);    		
    		root = loader.load();
    		IniciarFaseGruposController controler = loader.getController();
    		controler.setMainPane(mainpane);
    	} catch (Exception e) {
    		
    	}
    	this.mainpane.setCenter(root);
    	
    }
}
