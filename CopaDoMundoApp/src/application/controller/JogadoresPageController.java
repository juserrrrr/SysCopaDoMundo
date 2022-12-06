package application.controller;

import java.net.URL;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.ResourceBundle;

import application.model.Jogador;
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

public class JogadoresPageController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableView<Jogador> tabelaJogadores;
    
    private ObservableList<Jogador> jogadoresData;
    
    private Collection<Jogador> listarTodosJogadores()	{
    	Collection<Selecao> selecoes = null;
    	Collection<Jogador> jogadores = new HashSet<Jogador>();
    	
    	try {
			selecoes = Selecao.selecaoDao.findAll().values();
			for(Selecao selecao: selecoes) {
				try {
					jogadores.addAll(selecao.getJogadoresDao().findAll().values());
				} catch (Exception ignore) {}
			}
		} catch (Exception ignore) {}
    	return jogadores;
    }
    
    public void abrirDialogSelecao(Jogador jog) {
    	try {
    		FXMLLoader loader = new FXMLLoader();
    		URL xmlURL = getClass().getResource("/application/view/DialogJogador.fxml");
    		loader.setLocation(xmlURL);    		
    		Parent parent = loader.load();
    		DialogJogadorController controler = loader.getController();
    		
    		Scene scene = new Scene(parent);
    		Stage stage = new Stage();
    		
    		
    		stage.setTitle(jog == null ? "Criando Jogador": "Editando Jogador");
    		stage.setScene(scene);
    		stage.setResizable(false);
    		stage.initModality(Modality.APPLICATION_MODAL);
    		
    		controler.setJogador(jog);   
    		controler.setStage(stage);
    		controler.setJogadorData(jogadoresData);
    		controler.setJogadorTableView(tabelaJogadores);
    		controler.setCampoNome(jog == null ? "":jog.getNome());
    		controler.setPosicoesCombo(Jogador.getListaPosicoes());
    		controler.setSelectedPosicaoCombo(jog.getPosicao());
    		controler.setVisibleOffSelecaoBox();
    		try {
    			controler.setSelecoesCombo(Selecao.selecaoDao.findAll().values());				
			} catch (Exception e) {
				System.out.println("Erro: " + e.getMessage());
			}

    		stage.show();
    		
    	} catch (Exception e) {
    		System.out.println(e.getMessage());
    	}
    }
    
    public boolean abrirDialogConfirmacao(Jogador jog) {
    	DialogConfirmacaoController controler = null;
    	try {
    		FXMLLoader loader = new FXMLLoader();
    		URL xmlURL = getClass().getResource("/application/view/ConfirmDialog.fxml");
    		loader.setLocation(xmlURL);    		
    		Parent parent = loader.load();
    		controler = loader.getController();
    		
    		Scene scene = new Scene(parent);
    		Stage stage = new Stage();
    		
    		
    		stage.setTitle("Confirmar exclusão");
    		stage.setScene(scene);
    		stage.setResizable(false);
    		stage.initModality(Modality.APPLICATION_MODAL);
    		
    		controler.setMensagemAvisoLabel("Tem certeza que deseja excluir o jogador " + jog.getNome());
    		controler.setStage(stage);
    		stage.showAndWait();
    		
    		
    	} catch (Exception e) {
    		System.out.println(e.getMessage());
    	} 
    	return controler.getRespostaConfirmacao();
    }
    
    public Selecao getSelecaoResponsavel(int id) {
    	Collection<Selecao> selecoes = null;
    	Collection<Jogador> jogadores = null;
    	try {
			selecoes = Selecao.selecaoDao.findAll().values();
			for(Selecao selecao: selecoes) {
				try {
					jogadores = selecao.getJogadoresDao().findAll().values();
					for(Jogador jogador: jogadores) {
						if(jogador.getCodJog() == id) {
							return selecao;
						}
					}					
				} catch (Exception ignore) {}
			}
		} catch (Exception e) {}
    	return null;
    }

    public ImageView editarBotao(){
    	ImageView editBtn = new ImageView(new Image(getClass().getResourceAsStream("/application/view/imagens/Editar.png")));
		editBtn.setFitHeight(35);
		editBtn.setPreserveRatio(true);
		editBtn.setCursor(Cursor.HAND);
    	editBtn.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent arg0) {
				Jogador jog = tabelaJogadores.getSelectionModel().getSelectedItem();
				abrirDialogSelecao(jog);

			}
    	});
    	return editBtn;
    }
    
    public ImageView deletarBotao(){
    	ImageView deletBtn = new ImageView(new Image(getClass().getResourceAsStream("/application/view/imagens/Lixeira.png")));
		deletBtn.setFitHeight(35);
		deletBtn.setPreserveRatio(true);
		deletBtn.setCursor(Cursor.HAND);
    	deletBtn.setOnMouseClicked(new EventHandler<MouseEvent>() {
    		@Override
    		public void handle(MouseEvent arg0) {
    			Jogador jog = tabelaJogadores.getSelectionModel().getSelectedItem();
    			if(abrirDialogConfirmacao(jog)) {
    				Selecao selec = getSelecaoResponsavel(jog.getCodJog());
    				selec.getJogadoresDao().delete(jog.getCodJog());
    				jogadoresData.remove(jog);
    				tabelaJogadores.refresh();
    			}
    			
    		}
    	});
    	return deletBtn;
    }
    
    @FXML
    void criarSelecao(MouseEvent event) {
    	this.abrirDialogSelecao(null);
    }

    @FXML
    void initialize() {
    	
    	
    	Collection<Jogador> jogadores = null;
    	this.jogadoresData = FXCollections.observableArrayList();
    	try {
			jogadores = listarTodosJogadores();
			this.jogadoresData.addAll(jogadores);
		} catch (Exception ignore) {}
    	
    	TableColumn<Jogador, Integer> idCol = new TableColumn<Jogador, Integer>("ID");
    	TableColumn<Jogador, String> nomeCol =new TableColumn<Jogador, String>("Nome");
    	TableColumn<Jogador, String> posCol =new TableColumn<Jogador, String>("Posição");
    	TableColumn<Jogador, Integer> cartoesAmarelosCol =new TableColumn<Jogador, Integer>("Cartões amarelos");
    	TableColumn<Jogador, Integer> cartoesVermelhosCol =new TableColumn<Jogador, Integer>("Cartões vermelhos");
    	TableColumn<Jogador, Integer> golsCol =new TableColumn<Jogador, Integer>("Qntd gols");
    	TableColumn<Jogador,String> acoesCol  = new TableColumn<Jogador,String>("Ações");
    	
    	idCol.setCellValueFactory(new PropertyValueFactory<Jogador, Integer>("codJog"));
    	nomeCol.setCellValueFactory(new PropertyValueFactory<Jogador, String>("nome"));
    	posCol.setCellValueFactory(new PropertyValueFactory<Jogador, String>("posicao"));
    	cartoesAmarelosCol.setCellValueFactory(new PropertyValueFactory<Jogador, Integer>("QntCartoesAmarelos"));
    	cartoesVermelhosCol.setCellValueFactory(new PropertyValueFactory<Jogador, Integer>("QntCartoesVermelhos"));
    	golsCol.setCellValueFactory(new PropertyValueFactory<Jogador, Integer>("QntGols"));
    	acoesCol.setCellFactory(col -> new TableCell<Jogador, String>() {
    		private final HBox container;
    		
    		{
    			ImageView editBtn = editarBotao();
    			ImageView deletBtn = deletarBotao();
    			
    			container = new HBox();
    			container.setSpacing(20);
    			container.getChildren().addAll(editBtn,deletBtn);
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
    	
    	
    	this.tabelaJogadores.getColumns().addAll(idCol,nomeCol,posCol,cartoesAmarelosCol,cartoesVermelhosCol,golsCol,acoesCol);
    	this.tabelaJogadores.setItems(jogadoresData);
    	
    }

}
