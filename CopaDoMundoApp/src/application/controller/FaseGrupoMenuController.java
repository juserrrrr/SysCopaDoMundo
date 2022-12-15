/**
 * Sample Skeleton for 'FaseDeGruposPontos.fxml' Controller Class
 */

package application.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;

public class FaseGrupoMenuController {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="Border"
    private BorderPane Border; // Value injected by FXMLLoader

    @FXML
    void gerenciarPartidas(MouseEvent event) {

    }

    @FXML
    void verGrupos(MouseEvent event) {

    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert Border != null : "fx:id=\"Border\" was not injected: check your FXML file 'FaseDeGruposPontos.fxml'.";

    }

}
