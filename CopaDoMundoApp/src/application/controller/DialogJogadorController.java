package application.controller;

import java.net.URL;
import java.util.Collection;
import java.util.ResourceBundle;

import application.model.Jogador;
import application.model.Selecao;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class DialogJogadorController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<String> posicaoCombo;
    
    @FXML
    private ComboBox<Selecao> selecaoCombo;

    @FXML
    private TextField campoNome;
    
    @FXML
    private Label campoSelecaoLabel;
    
    private Stage stage;
    
	private ObservableList<Jogador> jogadorData;
	
	private TableView<Jogador> jogadorTableView;
	
	private Jogador jogador;
	
	public void setVisibleSelecaoBox(boolean bool) {
		this.selecaoCombo.setVisible(bool);
		this.campoSelecaoLabel.setVisible(bool);
	}
	
	public void setCampoNome(String nome) {
		this.campoNome.setText(nome);
	}
	
	public void setPosicoesCombo(String[] posicoes) {
		this.posicaoCombo.getItems().addAll(posicoes);
		
	}
	
	public void setSelecoesCombo(Collection<Selecao> selecoes) {
		this.selecaoCombo.getItems().addAll(selecoes);
		
	}
	
	public void setSelectedPosicaoCombo(String poiscao) {
		this.posicaoCombo.getSelectionModel().select(poiscao);
	}

	public void setStage(Stage stage) {
		this.stage = stage;
	}

	public void setJogadorTableView(TableView<Jogador> jogadorTableView) {
		this.jogadorTableView = jogadorTableView;
	}

	public void setJogador(Jogador jogador) {
		this.jogador = jogador;
	}
	
	public void setJogadorData(ObservableList<Jogador> jogadorData) {
		this.jogadorData = jogadorData;
	}
	
	@FXML
	void cancelarCriacao(MouseEvent event) {
		stage.close();
	}
	
	private void criarSelecao() {
		int posicao = posicaoCombo.getSelectionModel().getSelectedIndex();
		Jogador jog = new Jogador(campoNome.getText(),posicao);
		Selecao selec = selecaoCombo.getSelectionModel().getSelectedItem();
		try {
			selec.getJogadoresDao().create(jog);
			jogadorData.add(jog);
		} catch (Exception e) {
			System.out.println("Erro: " + e.getMessage());
		}
	}
	
	private void editarSelecao() {
		jogador.setNome(campoNome.getText());
		jogador.setPosicao(posicaoCombo.getSelectionModel().getSelectedIndex());
	}
	
	@FXML
	void confirmarCriacao(MouseEvent event) {
		if(jogador == null) {
			criarSelecao();
		} else {
			editarSelecao();
		}
		jogadorTableView.refresh();
    	stage.close();
	}

}
