package application.controller;

import java.net.URL;
import java.util.ResourceBundle;

import javax.naming.SizeLimitExceededException;

import application.model.Selecao;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class DialogSelecaoController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;
    
    @FXML
    private TextField campoNome;

    @FXML
    void initialize() {

    }
    private Stage stage;
    
    private Selecao selecao;
    
	private ObservableList<Selecao> selecaoData;
    
    
    public Stage getStage() {
		return stage;
	}

	public void setStage(Stage stage) {
		this.stage = stage;
	}

	public Selecao getSelecao() {
		return selecao;
	}

	public void setSelecao(Selecao selecao) {
		this.selecao = selecao;
	}

	@FXML
    void cancelarCriacao(MouseEvent event) {
    	this.stage.close();
    }
	
	private void criarSelecao(String nome) {
		Selecao selec = new Selecao(nome);
		try {
			Selecao.selecaoDao.create(selec);
			selecaoData.addAll(selec);
		} catch (SizeLimitExceededException ignore) {}
	}
	
	private void editarSelecao(String nome) {
		selecao.setNome(nome);
	}

    @FXML
    void confirmarCriacao(MouseEvent event) {

		criarSelecao(campoNome.getText());
    	stage.close();
    }
   
    public ObservableList<Selecao> getSelecaoData() {
    	return selecaoData;
    }
    
    public void setSelecaoData(ObservableList<Selecao> selecaoData) {
    	this.selecaoData = selecaoData;
    }
    	
}
