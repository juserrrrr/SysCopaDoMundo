package application.controller;

import java.net.URL;
import java.util.Collection;
import java.util.ResourceBundle;

import application.model.Arbitro;
import application.model.Jogador;
import application.model.Partida;
import application.model.Selecao;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
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
    
    public void abrirDialogSelecao(Partida partida) {
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
				abrirDialogArbitro(arbitro);
			}
    	});
    	return editBtn;
    }

    @FXML
    void initialize() {
    	Collection<Partida> partidas = null;
    	this.partidaData = FXCollections.observableArrayList();
    	try {
    		partidas = FaseDeGrupo.findAll().values();
    		arbitroData.addAll(arbitros);
    	} catch (Exception ignore) {}
    	
    	TableColumn<Arbitro,Integer> idCol  = new TableColumn<Arbitro,Integer>("ID");
    	TableColumn<Arbitro,String> nomeCol  = new TableColumn<Arbitro,String>("Nome");
    	TableColumn<Arbitro,String> acoesCol  = new TableColumn<Arbitro,String>("Ações");
    	
    	
    	idCol.setCellValueFactory(new PropertyValueFactory<Arbitro,Integer>("codArb"));
    	nomeCol.setCellValueFactory(new PropertyValueFactory<Arbitro,String>("nome"));
    }

}
