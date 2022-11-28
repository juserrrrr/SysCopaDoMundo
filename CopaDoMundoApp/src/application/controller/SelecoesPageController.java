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
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.util.Callback;

public class SelecoesPageController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableView<Selecao> listaSelecao;

    @FXML
    void criarSelecao(MouseEvent event) {

    }

    @FXML
    void listarSelecao(ActionEvent event) {

    }
    
    private ObservableList<Selecao> selecaoData;
    

    
    @FXML
    void initialize() {
        
        Collection<Selecao> selecoes = null;
		try {
			selecoes = Selecao.selecaoDao.findAll().values();
		} catch (Exception ignore) {}
		this.selecaoData = FXCollections.observableArrayList(selecoes);
    	
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
                Button viewBtn = new Button("View");
                Button clearBtn = new Button("Clear");

                container = new HBox(5, viewBtn, clearBtn);
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
    
    public Button deletar(){
        Button del = new Button("X");
        del.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent arg0) {}
        });
        return del;
    }
}
    
    

