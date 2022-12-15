/**
 * Sample Skeleton for 'FaseDeGruposPontos.fxml' Controller Class
 */

package application.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;

import application.model.FaseDeGrupo;
import application.model.Grupo;
import application.model.Selecao;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class FaseGrupoController {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="Border"
    private BorderPane Border; // Value injected by FXMLLoader

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
    	HBox hboxCenter = (HBox) Border.getCenter();  
    	List<Grupo> grupos = FaseDeGrupo.faseDeGrupo.findAll();
   
    	for(int i = 0;i<4;i++) {
    		VBox vboxGrupos = (VBox) hboxCenter.getChildren().get(i);
    		
    		for(int j =0;j<2;j++) {
    			BorderPane borderGrupo = (BorderPane) vboxGrupos.getChildren().get(j);
    			VBox vboxCenter = (VBox) borderGrupo.getCenter();    
    			VBox vboxRight = (VBox) borderGrupo.getRight();
    			List<Selecao> selecoes = grupos.get(i+j).getSelecoes();
    			for(int l = 0; l<4 ; l++) {
    				Label labelSelecao = (Label)vboxCenter.getChildren().get(l);
    				labelSelecao.setText(selecoes.get(l).getNome());
    				Label labelPontos = (Label)vboxRight.getChildren().get(l);
    				Integer pontos = selecoes.get(l).getPontos();
    				labelPontos.setText(pontos.toString());
    			}
    		}
    	}
    	
    }

}