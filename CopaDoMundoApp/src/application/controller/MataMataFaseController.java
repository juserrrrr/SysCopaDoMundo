package application.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class MataMataFaseController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private BorderPane campeaoTela;

    @FXML
    private Label labelCampeao;

    @FXML
    private VBox oitavasTela1;

    @FXML
    private VBox oitavasTela2;

    @FXML
    private VBox quartasTela1;

    @FXML
    private VBox quartasTela2;

    @FXML
    private HBox semisTela;

    @FXML
    private HBox telaMaaMata;

    @FXML
    void initialize() {
        assert campeaoTela != null : "fx:id=\"campeaoTela\" was not injected: check your FXML file 'Mata_mata.fxml'.";
        assert labelCampeao != null : "fx:id=\"labelCampeao\" was not injected: check your FXML file 'Mata_mata.fxml'.";
        assert oitavasTela1 != null : "fx:id=\"oitavasTela1\" was not injected: check your FXML file 'Mata_mata.fxml'.";
        assert oitavasTela2 != null : "fx:id=\"oitavasTela2\" was not injected: check your FXML file 'Mata_mata.fxml'.";
        assert quartasTela1 != null : "fx:id=\"quartasTela1\" was not injected: check your FXML file 'Mata_mata.fxml'.";
        assert quartasTela2 != null : "fx:id=\"quartasTela2\" was not injected: check your FXML file 'Mata_mata.fxml'.";
        assert semisTela != null : "fx:id=\"semisTela\" was not injected: check your FXML file 'Mata_mata.fxml'.";
        assert telaMaaMata != null : "fx:id=\"telaMaaMata\" was not injected: check your FXML file 'Mata_mata.fxml'.";

    }

}
