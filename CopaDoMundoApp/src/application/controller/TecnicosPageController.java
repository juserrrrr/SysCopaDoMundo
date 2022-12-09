package application.controller;

import java.net.URL;
import java.util.Collection;
import java.util.HashSet;
import java.util.ResourceBundle;

import application.model.Jogador;
import application.model.Selecao;
import application.model.Tecnico;
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

public class TecnicosPageController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableView<Tecnico> tabelaTecnicos;
    
    private ObservableList<Tecnico> tecnicosData;

    @FXML
    void criarTecnico(MouseEvent event) {
    	this.abrirDialogTecnico(null);
    }
    
    public void abrirDialogTecnico(Tecnico tec) {
    	try {
    		FXMLLoader loader = new FXMLLoader();
    		URL xmlURL = getClass().getResource("/application/view/DialogTecnico.fxml");
    		loader.setLocation(xmlURL);    		
    		Parent parent = loader.load();
    		DialogTecnicoController controler = loader.getController();
    		
    		Scene scene = new Scene(parent);
    		Stage stage = new Stage();
    		
    		
    		stage.setTitle(tec == null ? "Criando Tecnico": "Editando Tecnico");
    		stage.setScene(scene);
    		stage.setResizable(false);
    		stage.initModality(Modality.APPLICATION_MODAL);
    		
    		controler.setTecnico(tec);   
    		controler.setStage(stage);
    		controler.setTecnicoData(tecnicosData);
    		controler.setTecnicoTableView(tabelaTecnicos);
    		controler.setCampoNome(tec == null ? "":tec.getNome());
    		controler.setVisibleSelecaoBox(tec == null ? true : false);
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
    
    public boolean abrirDialogConfirmacao(Tecnico tec) {
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
    		
    		controler.setMensagemAvisoLabel("Tem certeza que deseja excluir o tecnico " + tec.getNome()+"?");
    		controler.setStage(stage);
    		stage.showAndWait();
    		
    		
    	} catch (Exception e) {
    		System.out.println(e.getMessage());
    	} 
    	return controler.getRespostaConfirmacao();
    }
    
    private Collection<Tecnico> listarTodosTecnicos()	{
    	Collection<Selecao> selecoes = null;
    	Collection<Tecnico> tecnicos = new HashSet<Tecnico>();
    	Tecnico tec =null;
    	try {
			selecoes = Selecao.selecaoDao.findAll().values();
			for(Selecao selecao: selecoes) {
				tec = selecao.getTecnicoDao().read();
				if(tec.getNome() != null) {
					tecnicos.add(selecao.getTecnicoDao().read());					
				}
			}
		} catch (Exception ignore) {}
    	return tecnicos;
    }

    public ImageView editarBotao(){
    	ImageView editBtn = new ImageView(new Image(getClass().getResourceAsStream("/application/view/imagens/Editar.png")));
		editBtn.setFitHeight(35);
		editBtn.setPreserveRatio(true);
		editBtn.setCursor(Cursor.HAND);
    	editBtn.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent arg0) {
				Tecnico tec = tabelaTecnicos.getSelectionModel().getSelectedItem();
				abrirDialogTecnico(tec);
			}
    	});
    	return editBtn;
    }
    
    public Selecao getSelecaoResponsavel(String nome) {
    	Collection<Selecao> selecoes = null;
    	Tecnico tec = null;
    	try {
			selecoes = Selecao.selecaoDao.findAll().values();
			for(Selecao selecao: selecoes) {
					tec = selecao.getTecnicoDao().read();
					if (tec.getNome() == nome) {
						return selecao;
					}	
			}
		} catch (Exception e) {}
    	return null;
    }
    
    public ImageView deletarBotao(){
    	ImageView deletBtn = new ImageView(new Image(getClass().getResourceAsStream("/application/view/imagens/Lixeira.png")));
		deletBtn.setFitHeight(35);
		deletBtn.setPreserveRatio(true);
		deletBtn.setCursor(Cursor.HAND);
    	deletBtn.setOnMouseClicked(new EventHandler<MouseEvent>() {
    		@Override
    		public void handle(MouseEvent arg0) {
    			Tecnico tec = tabelaTecnicos.getSelectionModel().getSelectedItem();
    			if(abrirDialogConfirmacao(tec)) {
    				Selecao selec = getSelecaoResponsavel(tec.getNome());
    				selec.getTecnicoDao().delete();
    				tecnicosData.remove(tec);
    				tabelaTecnicos.refresh();
    			}
    		}
    	});
    	return deletBtn;
    }
    
    @FXML
    void initialize() {
    	
    	Collection<Tecnico> tecnicos = null;
    	this.tecnicosData = FXCollections.observableArrayList();
		tecnicos = listarTodosTecnicos();
		this.tecnicosData.addAll(tecnicos);
        
    	
    	TableColumn<Tecnico, String> nomeCol = new TableColumn<>("Nome");
    	TableColumn<Tecnico,String> acoesCol  = new TableColumn<Tecnico,String>("Ações");
    	
    	nomeCol.setCellValueFactory(new PropertyValueFactory<Tecnico, String>("nome"));
    	acoesCol.setCellFactory(col -> new TableCell<Tecnico, String>() {
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
    	
    	
    	this.tabelaTecnicos.getColumns().addAll(nomeCol,acoesCol);
    	this.tabelaTecnicos.setItems(tecnicosData);
    	
    	
    }

}
