package application.controller;

import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
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
    		controler.setCampoData(partida.getData() == null ? "":partida.getData().toString());
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

    @FXML
    void initialize() {
    	Collection<Partida> partidas = null;
    	this.partidaData = FXCollections.observableArrayList();
    	try {
    		partidas = listarTodasPartidas();
    		partidaData.addAll(partidas);
    	} catch (Exception ignore) {}
    	
    	TableColumn<Partida,Integer> idCol  = new TableColumn<Partida,Integer>("ID");
    	TableColumn<Partida,String> selecao1Col  = new TableColumn<Partida,String>("Seleção 1");
    	TableColumn<Partida,String> selecao2Col  = new TableColumn<Partida,String>("Seleção 2");
    	TableColumn<Partida,String> localCol  = new TableColumn<Partida,String>("Local");
    	TableColumn<Partida,LocalDate> dataCol  = new TableColumn<Partida,LocalDate>("Data");
    	TableColumn<Partida,LocalTime> horaCol  = new TableColumn<Partida,LocalTime>("Hora");
    	TableColumn<Partida,String> arbCol  = new TableColumn<Partida,String>("Árbitro");
    	TableColumn<Partida,String> acoesCol  = new TableColumn<Partida,String>("Ações");
    	
    	
    	idCol.setCellValueFactory(new PropertyValueFactory<Partida,Integer>("codPart"));
    	selecao1Col.setCellValueFactory(new PropertyValueFactory<Partida,String>("selecao1"));
    	selecao2Col.setCellValueFactory(new PropertyValueFactory<Partida,String>("selecao2"));
    	localCol.setCellValueFactory(new PropertyValueFactory<Partida,String>("local"));
    	dataCol.setCellValueFactory(new PropertyValueFactory<Partida,LocalDate>("data"));
    	horaCol.setCellValueFactory(new PropertyValueFactory<Partida,LocalTime>("horario"));
    	arbCol.setCellValueFactory(new PropertyValueFactory<Partida,String>("arbitro"));
    	acoesCol.setCellFactory(col -> new TableCell<Partida, String>() {
		private final HBox container;
		
		{
			ImageView editBtn = editarBotao();
			
			container = new HBox();
			container.setSpacing(20);
			container.getChildren().addAll(editBtn);
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
    	
    	this.tabelaPartidas.getColumns().addAll(idCol,selecao1Col,selecao2Col,localCol,dataCol,horaCol,arbCol,acoesCol);
    	this.tabelaPartidas.setItems(partidaData);
    }

}
