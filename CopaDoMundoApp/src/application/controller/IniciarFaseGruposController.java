/**
 * Sample Skeleton for 'FaseDeGruposPontos.fxml' Controller Class
 */

package application.controller;

import java.net.URL;
import java.util.ResourceBundle;

import application.model.FaseDeGrupo;
import application.model.Selecao;
import application.model.dao.SelecaoDAO;
import application.model.excecoes.EmptyMapException;
import application.model.excecoes.JogadoresInsuficientesException;
import application.model.excecoes.SelecoesInsuficientesException;
import application.model.excecoes.TecnicosInsuficientesException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;

public class IniciarFaseGruposController {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="bttComecarFase"
    private Label bttComecarFase; // Value injected by FXMLLoader

    @FXML // fx:id="mensagemErro"
    private Text mensagemErro; // Value injected by FXMLLoader

    @FXML // fx:id="mensagemPossibildiade"
    private Label mensagemPossibildiade; // Value injected by FXMLLoader
    
    @FXML
    private BorderPane mainPane;
    
    void setMainPane(BorderPane mainPane) {
    	this.mainPane = mainPane;
    }

    @FXML
    void comecarFaseDeGrupos(MouseEvent event) {
    	SelecaoDAO selecDao = Selecao.selecaoDao;
    	try {
			FaseDeGrupo.faseDeGrupo.createFase(selecDao);
			this.openPageFaseController("/application/view/FaseDeGruposMenu.fxml");
		} catch (EmptyMapException | SelecoesInsuficientesException | JogadoresInsuficientesException
				| TecnicosInsuficientesException e) {
			this.mensagemPossibildiade.setVisible(true);
			this.mensagemErro.setVisible(true);
			this.bttComecarFase.setVisible(false);
			this.mensagemErro.setText(e.getMessage());
		}
    	
    }
    
    private void openPageFaseController(String url) {
    	Parent root = null;
    	
    	try {
    		FXMLLoader loader = new FXMLLoader();
    		URL xmlURL = getClass().getResource(url);
    		loader.setLocation(xmlURL);    		
    		root = loader.load();
    		FaseGrupoMenuController controler = loader.getController();
    		controler.setMainPane(mainPane);
    	} catch (Exception e) {
    		
    	}
    	this.mainPane.setCenter(root);
    	
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
       

    }

}
