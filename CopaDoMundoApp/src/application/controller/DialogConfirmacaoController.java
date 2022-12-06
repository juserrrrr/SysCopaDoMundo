package application.controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class DialogConfirmacaoController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label mensagemAvisoLabel;
    
    private Stage stage;
    
    private boolean respostaConfirmacao = false;

    public void setMensagemAvisoLabel(String mensagem) {
    	this.mensagemAvisoLabel.setText(mensagem);
    }

	public void setStage(Stage stage) {
		this.stage = stage;
	}
	
	public boolean getRespostaConfirmacao() {
		return respostaConfirmacao;
	}
	
	@FXML
	void cancelarAcao(MouseEvent event) {
		stage.close();
	}
	
	@FXML
	void confirmarAcao(MouseEvent event) {
		this.respostaConfirmacao = true;
		stage.close();
	}

}
