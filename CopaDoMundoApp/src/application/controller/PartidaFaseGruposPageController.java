package application.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;

public class PartidaFaseGruposPageController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableView<?> tabelaArbitros;

    @FXML
    void criarArbitro(MouseEvent event) {

    }

    @FXML
    void initialize() {
        assert tabelaArbitros != null : "fx:id=\"tabelaArbitros\" was not injected: check your FXML file 'PartidasFaseDeGrupo.fxml'.";

    }

}
