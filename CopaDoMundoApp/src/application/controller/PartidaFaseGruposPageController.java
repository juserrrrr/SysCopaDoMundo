package application.controller;

import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.HashSet;
import java.util.ResourceBundle;

import application.model.Arbitro;
import application.model.FaseDeGrupo;
import application.model.Grupo;
import application.model.Jogador;
import application.model.Partida;
import application.model.Selecao;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class PartidaFaseGruposPageController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableView<Partida> tabelaPartidas;
    
    private ObservableList<Partida> partidaData;
    
    private Collection<Partida> listarTodasPartidas(){
    	Collection<Grupo> grupos = null;
    	Collection<Partida> partidas = new HashSet<Partida>();
    	try {
			grupos = FaseDeGrupo.faseDeGrupo.findAll();
			for(Grupo grupo: grupos) {
				partidas.addAll(grupo.getPartidas().values());
			}
		} catch (Exception ignore) {}
    	return partidas;
    }
    
    public void abrirDialogPartida(Partida partida) {
    	try {
    		FXMLLoader loader = new FXMLLoader();
    		URL xmlURL = getClass().getResource("/application/view/DialogPartida.fxml");
    		loader.setLocation(xmlURL);    		
    		Parent parent = loader.load();
    		DialogPartidaController controler = loader.getController();
    		DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    		
    		Scene scene = new Scene(parent);
    		Stage stage = new Stage();
    		
    		
    		stage.setTitle("Editando Dados da Partida");
    		stage.setScene(scene);
    		stage.setResizable(false);
    		stage.initModality(Modality.APPLICATION_MODAL);
    		
    		controler.setPartida(partida);
    		controler.setStage(stage);
    		controler.setPartidaData(partidaData);
    		controler.setPartidaTableView(tabelaPartidas);
    		controler.setCampoLocal(partida.getLocal() == null ? "":partida.getLocal());
    		controler.setCampoData(partida.getData() == null ? "":partida.getData().format(formato));
    		controler.setCampoHora(partida.getHorario() == null ? "":partida.getHorario().toString());
    		
    		try {
    			controler.setArbitrosCombo(Arbitro.arbitroDao.findAll().values());				
			} catch (Exception e) {
				System.out.println("Erro: " + e.getMessage());
			}

    		stage.show();
    		
    	} catch (Exception e) {
    		System.out.println(e.getMessage());
    	}
    }
    
    public void abrirDialogInformacoes(Partida partida) {
    	try {
    		FXMLLoader loader = new FXMLLoader();
    		URL xmlURL = getClass().getResource("/application/view/DialogInfoPartida.fxml");
    		loader.setLocation(xmlURL);    		
    		Parent parent = loader.load();
    		DialogInfoPartidaController controler = loader.getController();
    		DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    		Scene scene = new Scene(parent);
    		Stage stage = new Stage();
    		
    		stage.setTitle("Dados da Partida");
    		stage.setScene(scene);
    		stage.setResizable(false);
    		stage.initModality(Modality.APPLICATION_MODAL);
    		
    		controler.setCampoLocal(partida.getLocal() == null ? "Sem local": partida.getLocal());
    		controler.setCampoData(partida.getData() == null ? "Sem Data":partida.getData().format(formato));
    		controler.setCampoHora(partida.getHorario() == null ? "Sem Horário":partida.getHorario().toString());
    		controler.setCampoArbitro(partida.getArbitro() == null ? "Sem Árbitro":partida.getArbitro().getNome());
    		controler.setStage(stage);
    		
    		stage.show();
    		
    	} catch (Exception e) {
    		System.out.println(e.getMessage());
    	}
    }
    
    public void abrirDialogFinalizar(Partida partida) {
    	try {
    		FXMLLoader loader = new FXMLLoader();
    		URL xmlURL = getClass().getResource("/application/view/DialogFinalizarPartida.fxml");
    		loader.setLocation(xmlURL);    		
    		Parent parent = loader.load();
    		DialogFinalizarPartidaController controler = loader.getController();
    		Scene scene = new Scene(parent);
    		Stage stage = new Stage();
    		
    		stage.setTitle("Finalizar Partida");
    		stage.setScene(scene);
    		stage.setResizable(false);
    		stage.initModality(Modality.APPLICATION_MODAL);
    		
    		controler.setPartida(partida);
    		controler.setStage(stage);
    		controler.setPartidaTableView(tabelaPartidas);
    		controler.setCampoTime1(partida.getSelecao1().getNome());
    		controler.setCampoTime2(partida.getSelecao2().getNome());
    		controler.setJogadoresTime1Data(partida.getSelecao1().getJogadoresDao().findAll().values());
    		controler.setJogadoresTime2Data(partida.getSelecao2().getJogadoresDao().findAll().values());
    		controler.carregarTabela();
    		
    		stage.show();
    		
    	} catch (Exception e) {
    		System.out.println(e.getMessage());
    	}
    }
    
    public ImageView editarBotao(){
    	ImageView editBtn = new ImageView(new Image(getClass().getResourceAsStream("/application/view/imagens/Editar.png")));
		editBtn.setFitHeight(35);
		editBtn.setPreserveRatio(true);
		editBtn.setCursor(Cursor.HAND);
    	editBtn.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent arg0) {
				Partida partida = tabelaPartidas.getSelectionModel().getSelectedItem();
				abrirDialogPartida(partida);
			}
    	});
    	return editBtn;
    }
    
    public ImageView informacoesBotao() {
    	ImageView infoBtn = new ImageView(new Image(getClass().getResourceAsStream("/application/view/imagens/Info.png")));
    	infoBtn.setFitHeight(41);
    	infoBtn.setPreserveRatio(true);
		infoBtn.setCursor(Cursor.HAND);
		infoBtn.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent arg0) {
				Partida partida = tabelaPartidas.getSelectionModel().getSelectedItem();
				abrirDialogInformacoes(partida);
			}
    	});
    	return infoBtn;
    }
    
    public ImageView finalizarBotao() {
    	ImageView finBtn = new ImageView(new Image(getClass().getResourceAsStream("/application/view/imagens/Check.png")));
    	finBtn.setFitHeight(41);
    	finBtn.setPreserveRatio(true);
    	finBtn.setCursor(Cursor.HAND);
    	finBtn.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent arg0) {
				Partida partida = tabelaPartidas.getSelectionModel().getSelectedItem();
				if(!partida.isFinalizada()) {
					abrirDialogFinalizar(partida);
				}else {
					
				}
			}
    	});
    	return finBtn;
    }

    @FXML
    void initialize() {
    	Collection<Partida> partidas = null;
    	this.partidaData = FXCollections.observableArrayList();
    	try {
    		partidas = listarTodasPartidas();
    		partidaData.addAll(partidas);
    	} catch (Exception ignore) {}
    	
    	TableColumn<Partida,Integer> idCol  = new TableColumn<Partida,Integer>("ID");
    	TableColumn<Partida,String> selecoesCol  = new TableColumn<Partida,String>("Seleções");
    	TableColumn<Partida,String> placarCol  = new TableColumn<Partida,String>("Placar");
    	TableColumn<Partida,String> acoesCol  = new TableColumn<Partida,String>("Ações");
    	
    	
    	idCol.setCellValueFactory(new PropertyValueFactory<Partida,Integer>("codPart"));
    	selecoesCol.setCellValueFactory(new PropertyValueFactory<Partida,String>("selecoes"));
    	placarCol.setCellValueFactory(new PropertyValueFactory<Partida,String>("placar"));
    	acoesCol.setCellFactory(col -> new TableCell<Partida, String>() {
		private final HBox container;
		
		{
			ImageView infoBtn = informacoesBotao();
			ImageView finBtn = finalizarBotao();
			ImageView editBtn = editarBotao();
			
			container = new HBox();
			container.setSpacing(10);
			container.getChildren().addAll(infoBtn, finBtn, editBtn);
			container.setAlignment(Pos.CENTER);
			
		}
		
		@Override
		public void updateItem(String item, boolean empty) {
			super.updateItem(item, empty);
			if(empty) {
				setGraphic(null);
			} else {
				setGraphic(container);
			}
		}
	});
    	
    	this.tabelaPartidas.getColumns().addAll(idCol,selecoesCol,placarCol,acoesCol);
    	this.tabelaPartidas.setItems(partidaData);
    }

}
