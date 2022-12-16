package application.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class ComecarMataMataErroController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label mensagemPossibildiade;

    @FXML
    void initialize() {
        assert mensagemPossibildiade != null : "fx:id=\"mensagemPossibildiade\" was not injected: check your FXML file 'ComeçarMataMataErro.fxml'.";

    }

}
