package application.controller;

import java.net.URL;
import java.util.ResourceBundle;

import application.model.Arbitro;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class DialogArbitroController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;
    
    @FXML
    private TextField campoNome;

    private Stage stage;
    
	private ObservableList<Arbitro> arbitroData;
	
	private TableView<Arbitro> arbitroTableView;
	
	private Arbitro arbitro;

	public void setCampoNome(String nome) {
		this.campoNome.setText(nome);
	}
    
    public Stage getStage() {
		return stage;
	}

	public void setStage(Stage stage) {
		this.stage = stage;
	}
	
	public ObservableList<Arbitro> getarbitroData() {
		return arbitroData;
	}
	
	public void setArbitroData(ObservableList<Arbitro> arbitroData) {
		this.arbitroData = arbitroData;
	}

	public void setArbitroTableView(TableView<Arbitro> arbitroTableView) {
		this.arbitroTableView = arbitroTableView;
	}
	
	public void setArbitro(Arbitro arbitro) {
		this.arbitro = arbitro;
	}
	
	@FXML
    void cancelarCriacao(MouseEvent event) {
    	this.stage.close();
    }
	
	/**
	 * Função responável por criar um arbitro com os dados inseridos pelo usuario
	 */
	private void criarArbitro() {
		Arbitro selec = new Arbitro(campoNome.getText());
		Arbitro.arbitroDao.create(selec);
		arbitroData.add(selec);
	}
		
	/**
	 * Função responável por editar um arbitro selecionado pelo usuario
	 */
	private void editarArbitro() {
		arbitro.setNome(campoNome.getText());
	}
			
	
	/**
	 * Função responsável pelo botão de confirmar
	 * @param event
	 */
	@FXML
    void confirmarCriacao(MouseEvent event) {
		if(arbitro == null) {
			criarArbitro();
		} else {
			editarArbitro();
		}
		arbitroTableView.refresh();
    	stage.close();
    }
}
