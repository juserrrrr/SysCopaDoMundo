package application.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;

public class ArbitrosPageController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableView<?> listaSelecao;

    @FXML
    void criarSelecao(MouseEvent event) {

    }

    @FXML
    void listarSelecao(ActionEvent event) {

    }

    @FXML
    void initialize() {
        assert listaSelecao != null : "fx:id=\"listaSelecao\" was not injected: check your FXML file 'ArbitrosPage.fxml'.";

    }

}
