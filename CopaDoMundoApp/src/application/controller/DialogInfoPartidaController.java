package application.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class DialogInfoPartidaController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label labelData;

	@FXML
    private Label labelHora;

    @FXML
    private Label labelArbitro;

    @FXML
    private Label labelLocal;
    
    private Stage stage;

	public void setCampoData(String data) {
		this.labelData.setText(data);
	}

	public void setCampoHora(String hora) {
		this.labelHora.setText(hora);
	}

	public void setCampoArbitro(String arbitro) {
		this.labelArbitro.setText(arbitro);
	}

	public void setCampoLocal(String local) {
		this.labelLocal.setText(local);;
	}

	public void setStage(Stage stage) {
		this.stage = stage;
	}
	
    @FXML
    void fecharDialog(MouseEvent event) {
    	this.stage.close();
    }

    @FXML
    void initialize() {
        

    }

}
