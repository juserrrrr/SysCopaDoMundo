package application.controller;

import java.net.URL;
import java.util.Collection;
import java.util.Map;
import java.util.ResourceBundle;

import application.model.JogPartida;
import application.model.Jogador;
import application.model.Partida;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class DialogFinalizarPartidaController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label campoTime1;

    @FXML
    private Label campoTime2;

    @FXML
    private TableView<Jogador> tabelaTime1;

    @FXML
    private TableView<Jogador> tabelaTime2;
    
    private ObservableList<Jogador> jogadoresTime1Data;
    
    private ObservableList<Jogador> jogadoresTime2Data;
    
    private Stage stage;
    
    private Partida partida;

	private TableView<Partida> partidaTableView;

    @FXML
    void cancelarCriacao(MouseEvent event) {
    	this.stage.close();
    }

	public void setJogadoresTime1Data(Collection<Jogador> jogadoresTime1) {
		this.jogadoresTime1Data = FXCollections.observableArrayList();
		jogadoresTime1Data.addAll(jogadoresTime1);
	}
	
	public void setJogadoresTime2Data(Collection<Jogador> jogadoresTime2) {
		this.jogadoresTime2Data = FXCollections.observableArrayList();
		jogadoresTime2Data.addAll(jogadoresTime2);
	}
	
	
	public void setCampoTime1(String time1) {
		this.campoTime1.setText(time1);
	}

	public void setCampoTime2(String time2) {
		this.campoTime2.setText(time2);
	}

	public void setStage(Stage stage) {
		this.stage = stage;
	}
	
	public void setPartida(Partida partida) {
		this.partida = partida;
	}

	public void carregarTabela() {
		TableColumn<Jogador,Integer> idCol  = new TableColumn<Jogador,Integer>("ID");
    	TableColumn<Jogador,String> nomeCol  = new TableColumn<Jogador,String>("Jogador");
    	TableColumn<Jogador,Integer> golsCol  = new TableColumn<Jogador,Integer>("Gols");
    	TableColumn<Jogador,String> cartoesCol  = new TableColumn<Jogador,String>("Cartões");
    	
    	idCol.setCellValueFactory(new PropertyValueFactory<Jogador,Integer>("codJog"));
    	nomeCol.setCellValueFactory(new PropertyValueFactory<Jogador,String>("nome"));
    	golsCol.setCellValueFactory(new PropertyValueFactory<Jogador,Integer>("golsRange"));
    	cartoesCol.setCellValueFactory(new PropertyValueFactory<Jogador,String>("cartoes"));
    	
    	this.tabelaTime1.getColumns().addAll(idCol,nomeCol,golsCol,cartoesCol);
    	this.tabelaTime1.setItems(jogadoresTime1Data);
    	
    	TableColumn<Jogador,Integer> idCol2  = new TableColumn<Jogador,Integer>("ID");
    	TableColumn<Jogador,String> nomeCol2  = new TableColumn<Jogador,String>("Jogador");
    	TableColumn<Jogador,Integer> golsCol2  = new TableColumn<Jogador,Integer>("Gols");
    	TableColumn<Jogador,String> cartoesCol2  = new TableColumn<Jogador,String>("Cartões");
    	
    	idCol2.setCellValueFactory(new PropertyValueFactory<Jogador,Integer>("codJog"));
    	nomeCol2.setCellValueFactory(new PropertyValueFactory<Jogador,String>("nome"));
    	golsCol2.setCellValueFactory(new PropertyValueFactory<Jogador,Integer>("golsRange"));
    	cartoesCol2.setCellValueFactory(new PropertyValueFactory<Jogador,String>("cartoes"));
    	
    	this.tabelaTime2.getColumns().addAll(idCol2,nomeCol2,golsCol2,cartoesCol2);
    	this.tabelaTime2.setItems(jogadoresTime2Data);
	}

	@FXML
	void confirmarCriacao(MouseEvent event) {
		Map<Integer, JogPartida> jogPartMap = partida.getJogPartidas();
		for(Jogador jogador: jogadoresTime1Data) {
			JogPartida jogPart = new JogPartida(jogador,partida,jogador.getGolsRange().getValue(),partida.getSelecao1().getCodSel());
			if(jogador.getCartoes().getSelectionModel().getSelectedIndex() == 1) {
				jogPart.setQntCartoesAmarelos(1);
			} else if(jogador.getCartoes().getSelectionModel().getSelectedIndex() == 2){
				jogPart.setQntCartoesAmarelos(2);
				jogPart.setQntCartoesVermelhos(1);
			}
			jogPartMap.put(jogador.getCodJog(),jogPart);
			partida.setGolsTime1(partida.getGolsTime1() + jogador.getGolsRange().getValue());
		}
		for(Jogador jogador: jogadoresTime2Data) {
			JogPartida jogPart = new JogPartida(jogador,partida,jogador.getGolsRange().getValue(),partida.getSelecao2().getCodSel());
			if(jogador.getCartoes().getSelectionModel().getSelectedIndex() == 1) {
				jogPart.setQntCartoesAmarelos(1);
			} else if(jogador.getCartoes().getSelectionModel().getSelectedIndex() == 2){
				jogPart.setQntCartoesAmarelos(2);
				jogPart.setQntCartoesVermelhos(1);
			}
			jogPartMap.put(jogador.getCodJog(),jogPart);
			partida.setGolsTime2(partida.getGolsTime2() + jogador.getGolsRange().getValue());
		}
		this.partidaTableView.refresh();
		partida.setFinalizada(true);
		stage.close();
	}	
	
	@FXML
    void initialize() {
    	
    }

	public void setPartidaTableView(TableView<Partida> partidaTableView) {
		this.partidaTableView = partidaTableView;
	}

}
