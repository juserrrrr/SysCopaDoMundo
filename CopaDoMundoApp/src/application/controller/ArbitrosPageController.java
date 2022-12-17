package application.controller;

import java.net.URL;
import java.util.Collection;
import java.util.ResourceBundle;
import java.util.function.Predicate;

import application.model.Arbitro;
import application.model.Jogador;
import application.model.Selecao;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
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
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ArbitrosPageController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableView<Arbitro> tabelaArbitros;
    
    private ObservableList<Arbitro> arbitroData;
    
    private FilteredList<Arbitro> filtroTabelaArbitros;
    @FXML
    private TextField searchField;
    
    @FXML
    void filtrarTabela(ActionEvent event) {
    	if(searchField.getText().isEmpty()) {
    		tabelaArbitros.setItems(arbitroData);
    	} else {
    		filtroTabelaArbitros = new FilteredList<Arbitro>(arbitroData);
    		filtroTabelaArbitros.setPredicate(
    			    new Predicate<Arbitro>(){
    			        public boolean test(Arbitro arb){
    			            return arb.getNome().equals(searchField.getText());
    			        }
    			    }
    			);
        	this.tabelaArbitros.setItems(filtroTabelaArbitros);
    	}
    	tabelaArbitros.refresh();
    }
    
    @FXML
    void criarArbitro(MouseEvent event) {
        this.abrirDialogArbitro(null);
    }

    public void abrirDialogArbitro(Arbitro arbitro) {
    	try {
    		FXMLLoader loader = new FXMLLoader();
    		URL xmlURL = getClass().getResource("/application/view/DialogArbitro.fxml");
    		loader.setLocation(xmlURL);    		
    		Parent parent = loader.load();
    		DialogArbitroController controler = loader.getController();
    		
    		Scene scene = new Scene(parent);
    		Stage stage = new Stage();
    		
    		
    		stage.setTitle(arbitro == null ? "Criando Árbitro": "Editando Árbitro");
    		stage.setScene(scene);
    		stage.setResizable(false);
    		stage.initModality(Modality.APPLICATION_MODAL);
    		
    		controler.setArbitro(arbitro);   
    		controler.setStage(stage);
    		controler.setArbitroData(arbitroData);
    		controler.setArbitroTableView(tabelaArbitros);
    		controler.setCampoNome(arbitro == null ? "":arbitro.getNome());
    		stage.show();
    		
    	} catch (Exception e) {
    		System.out.println(e.getMessage());
    	}
    }
    
    public boolean abrirDialogConfirmacao(Arbitro arbitro) {
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
    		
    		controler.setMensagemAvisoLabel("Tem certeza que deseja excluir o árbitro " + arbitro.getNome()+"?");
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
				Arbitro arbitro = tabelaArbitros.getSelectionModel().getSelectedItem();
				abrirDialogArbitro(arbitro);
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
    			Arbitro arbitro = tabelaArbitros.getSelectionModel().getSelectedItem();
    			if(abrirDialogConfirmacao(arbitro)) {
    				Arbitro.arbitroDao.delete(arbitro.getCodArb());
    				arbitroData.remove(arbitro);
    				tabelaArbitros.refresh();
    			}
    			
    		}
    	});
    	return deletBtn;
    }
    
    @FXML
    void initialize() {
    	Collection<Arbitro> arbitros = null;
    	this.arbitroData = FXCollections.observableArrayList();
    	try {
    		arbitros = Arbitro.arbitroDao.findAll().values();
    		arbitroData.addAll(arbitros);
    	} catch (Exception ignore) {}
    	
    	TableColumn<Arbitro,Integer> idCol  = new TableColumn<Arbitro,Integer>("ID");
    	TableColumn<Arbitro,String> nomeCol  = new TableColumn<Arbitro,String>("Nome");
    	TableColumn<Arbitro,String> acoesCol  = new TableColumn<Arbitro,String>("Ações");
    	
    	
    	idCol.setCellValueFactory(new PropertyValueFactory<Arbitro,Integer>("codArb"));
    	nomeCol.setCellValueFactory(new PropertyValueFactory<Arbitro,String>("nome"));
    	acoesCol.setCellFactory(col -> new TableCell<Arbitro, String>() {
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
    	
    	this.tabelaArbitros.getColumns().addAll(idCol, nomeCol, acoesCol);
    	this.tabelaArbitros.setItems(arbitroData);
    }
}
