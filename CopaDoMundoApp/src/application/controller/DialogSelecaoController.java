package application.controller;

import java.net.URL;
import java.util.ResourceBundle;

import javax.naming.SizeLimitExceededException;

import application.model.Selecao;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
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

    private Stage stage;
    
	private ObservableList<Selecao> selecaoData;
	
	private TableView<Selecao> selecaoTableView;
	
	private Selecao selecao;

	public void setCampoNome(String nome) {
		this.campoNome.setText(nome);
	}
    
    public Stage getStage() {
		return stage;
	}

	public void setStage(Stage stage) {
		this.stage = stage;
	}
	
	public ObservableList<Selecao> getSelecaoData() {
		return selecaoData;
	}
	
	public void setSelecaoData(ObservableList<Selecao> selecaoData) {
		this.selecaoData = selecaoData;
	}

	public void setSelecaoTableView(TableView<Selecao> selecaoTableView) {
		this.selecaoTableView = selecaoTableView;
	}
	
	public void setSelecao(Selecao selecao) {
		this.selecao = selecao;
	}
	
	@FXML
    void cancelarCriacao(MouseEvent event) {
    	this.stage.close();
    }
	
	private void criarSelecao() {
		Selecao selec = new Selecao(campoNome.getText());
		try {
			Selecao.selecaoDao.create(selec);
			selecaoData.add(selec);
		} catch (SizeLimitExceededException e) {
			//Completar codigo!
		}
	}
		
	private void editarSelecao() {
		selecao.setNome(campoNome.getText());
	}
			
	
	@FXML
    void confirmarCriacao(MouseEvent event) {
		if(selecao == null) {
			criarSelecao();
		} else {
			editarSelecao();
		}
		selecaoTableView.refresh();
    	stage.close();
    }
   
	


}
