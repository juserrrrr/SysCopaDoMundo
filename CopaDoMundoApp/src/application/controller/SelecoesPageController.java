package application.controller;

import java.net.URL;
import java.util.Collection;
import java.util.ResourceBundle;

import application.model.Selecao;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
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

public class SelecoesPageController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableView<Selecao> listaSelecao;
    
    private ObservableList<Selecao> selecaoData;

    @FXML
    void criarSelecao(MouseEvent event) {
        this.abrirDialogSelecao(null);
    }
    
    public void abrirDialogSelecao(Selecao selec) {
    	try {
    		FXMLLoader loader = new FXMLLoader();
    		URL xmlURL = getClass().getResource("/application/view/DialogSelecao.fxml");
    		loader.setLocation(xmlURL);    		
    		Parent parent = loader.load();
    		DialogSelecaoController controler = loader.getController();
    		
    		Scene scene = new Scene(parent);
    		Stage stage = new Stage();
    		
    		
    		stage.setTitle(selec == null ? "Criando Seleção": "Editando Seleção");
    		stage.setScene(scene);
    		stage.setResizable(false);
    		stage.initModality(Modality.APPLICATION_MODAL);
    		
    		controler.setSelecao(selec);   
    		controler.setStage(stage);
    		controler.setSelecaoData(selecaoData);
    		controler.setSelecaoTableView(listaSelecao);
    		controler.setCampoNome(selec == null ? "":selec.getNome());
    		stage.show();
    		
    	} catch (Exception e) {
    		System.out.println(e.getMessage());
    	}
    }
    
    public boolean abrirDialogConfirmacao(Selecao selec) {
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
    		
    		controler.setMensagemAvisoLabel("Tem certeza que deseja excluir a selecão " + selec.getNome());
    		controler.setStage(stage);
    		stage.showAndWait();
    		
    		
    	} catch (Exception e) {
    		System.out.println(e.getMessage());
    	} 
    	return controler.getRespostaConfirmacao();
    }
    
    public ImageView editarBotao(){
    	ImageView editBtn = new ImageView(new Image(getClass().getResourceAsStream("/application/view/imagens/Editar.png")));
		editBtn.setFitHeight(35);
		editBtn.setPreserveRatio(true);
		editBtn.setCursor(Cursor.HAND);
    	editBtn.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent arg0) {
				Selecao selec = listaSelecao.getSelectionModel().getSelectedItem();
				abrirDialogSelecao(selec);
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
    			Selecao selec = listaSelecao.getSelectionModel().getSelectedItem();
    			if(abrirDialogConfirmacao(selec)) {
    				Selecao.selecaoDao.delete(selec.getCodSel());
    				selecaoData.remove(selec);
    				listaSelecao.refresh();
    			}
    			
    		}
    	});
    	return deletBtn;
    }
    
    @FXML
    void initialize() {
    	
    	Collection<Selecao> selecoes = null;
    	this.selecaoData = FXCollections.observableArrayList();
    	try {
    		selecoes = Selecao.selecaoDao.findAll().values();
    		selecaoData.addAll(selecoes);
    	} catch (Exception ignore) {}
    	
    	TableColumn<Selecao,Integer> idCol  = new TableColumn<Selecao,Integer>("ID");
    	TableColumn<Selecao,String> nomeCol  = new TableColumn<Selecao,String>("Nome");
    	TableColumn<Selecao,ListView> jogadoresListCol  = new TableColumn<Selecao,ListView>("Jogadores");
    	TableColumn<Selecao,Integer> jogadoresCol  = new TableColumn<Selecao,Integer>("Quantidade de Jogadores");
    	TableColumn<Selecao,String> tecnicoCol  = new TableColumn<Selecao,String>("Técnico");
    	TableColumn<Selecao,String> acoesCol  = new TableColumn<Selecao,String>("Ações");
    	
    	idCol.setCellValueFactory(new PropertyValueFactory<Selecao,Integer>("codSel"));
    	nomeCol.setCellValueFactory(new PropertyValueFactory<Selecao,String>("nome"));
    	jogadoresCol.setCellValueFactory(new PropertyValueFactory<Selecao,Integer>("qntdJogadores"));
    	jogadoresListCol.setCellValueFactory(new PropertyValueFactory<Selecao,ListView>("nomeJogadores"));
    	jogadoresListCol.setMinWidth(100);
    	tecnicoCol.setCellValueFactory(new PropertyValueFactory<Selecao,String>("nomeTecnico"));
    	acoesCol.setCellFactory(col -> new TableCell<Selecao, String>() {
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
    			if (empty) {
    				setGraphic(null);
    			} else {
    				setGraphic(container);
    			}
    		}
    	});
    	
    	
    	this.listaSelecao.getColumns().addAll(idCol, nomeCol, jogadoresCol, jogadoresListCol, tecnicoCol, acoesCol);
    	this.listaSelecao.setItems(selecaoData);
    }
    
}
    
    

