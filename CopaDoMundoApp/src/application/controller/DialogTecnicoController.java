package application.controller;

import java.net.URL;
import java.util.Collection;
import java.util.ResourceBundle;

import javax.naming.SizeLimitExceededException;

import application.model.Jogador;
import application.model.Selecao;
import application.model.Tecnico;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class DialogTecnicoController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField campoNome;
    
    @FXML
    private Label campoSelecaoLabel;

    @FXML
    private ComboBox<Selecao> selecaoCombo;
    
    private Stage stage;
    
	private ObservableList<Tecnico> tecnicoData;
	
	private TableView<Tecnico> tecnicoTableView;
	
	
	private Tecnico tecnico;

    @FXML
    void cancelarCriacao(MouseEvent event) {
    	stage.close();
    }

    @FXML
    void confirmarCriacao(MouseEvent event) {
    	if(tecnico == null) {
			criarTecnico();
		} else {
			editarTecnico();
		}
		tecnicoTableView.refresh();
    	stage.close();
    }
    
    private void criarTecnico() {
		Tecnico tec = new Tecnico(campoNome.getText());
		Selecao selec = selecaoCombo.getSelectionModel().getSelectedItem();
		try {
			selec.getTecnicoDao().create(tec);
			tecnicoData.add(tec);
		} catch (Exception e) {
			System.out.println("Erro: " + e.getMessage());
		}
	}
		
	private void editarTecnico() {
		this.tecnico.setNome(campoNome.getText());
	}

	public void setStage(Stage stage) {
		this.stage = stage;
	}

	public void setTecnicoData(ObservableList<Tecnico> tecnicoData) {
		this.tecnicoData = tecnicoData;
	}

	public void setTecnicoTableView(TableView<Tecnico> tecnicoTableView) {
		this.tecnicoTableView = tecnicoTableView;
	}

	
	public void setCampoNome(String nome) {
		this.campoNome.setText(nome);
	}

	public void setTecnico(Tecnico tecnico) {
		this.tecnico = tecnico;
	}
	
	public void setVisibleSelecaoBox(boolean bool) {
		this.selecaoCombo.setVisible(bool);
		this.campoSelecaoLabel.setVisible(bool);
	}
	
	public void setSelecoesCombo(Collection<Selecao> selecoes) {
		this.selecaoCombo.getItems().addAll(selecoes);
		
	}
	

}
