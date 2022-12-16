package application.controller;

import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Collection;
import java.util.ResourceBundle;

import application.model.Arbitro;
import application.model.Partida;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class DialogPartidaController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;
    
    @FXML
    private ComboBox<Arbitro> arbitroCombo;

    @FXML
    private TextField campoData;

    @FXML
    private TextField campoHora;

    @FXML
    private TextField campoLocal;
    
    @FXML
    private Label labelError;

    private Stage stage;
    
	private ObservableList<Partida> partidaData;
	
	private TableView<Partida> partidaTableView;
	
	private Partida partida;

	public void setCampoLocal(String local) {
		this.campoLocal.setText(local);
	}
	
	public void setArbitrosCombo(Collection<Arbitro> arbitros) {
		this.arbitroCombo.getItems().addAll(arbitros);
	}
	
	public void setCampoData(String data) {
		this.campoData.setText(data);
	}
	
	public void setCampoHora(String hora) {
		this.campoHora.setText(hora);
	}
	
	
	public void setPartidaData(ObservableList<Partida> partidaData) {
		this.partidaData = partidaData;
	}
	
	public void setPartidaTableView(TableView<Partida> partidaTableView) {
		this.partidaTableView = partidaTableView;
	}
	
	public void setPartida(Partida partida) {
		this.partida = partida;
	}
    
    public Stage getStage() {
		return stage;
	}

	public void setStage(Stage stage) {
		this.stage = stage;
	}
	
	public ObservableList<Partida> getpartidaData() {
		return partidaData;
	}
	
	@FXML
    void cancelarCriacao(MouseEvent event) {
    	this.stage.close();
    }
	
	public LocalDate validateDate(String dateStr) throws DateTimeParseException {
		DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		LocalDate inputDt = LocalDate.parse(dateStr,formato);
		return inputDt;
	}
	
	
	public LocalTime validateTime(String timeStr) throws DateTimeParseException {
		DateTimeFormatter formato = DateTimeFormatter.ofPattern("HH:mm");
		LocalTime inputTm = LocalTime.parse(timeStr,formato);
		return inputTm;
	}
	
	private void editarPartida() throws DateTimeParseException{
		LocalTime horaValidada = validateTime(campoHora.getText());
		LocalDate dataValidada = validateDate(campoData.getText());
		Arbitro arb = arbitroCombo.getSelectionModel().getSelectedItem();
		partida.setLocal(campoLocal.getText());
		partida.setHorario(horaValidada);
		partida.setData(dataValidada);
		partida.setArbitro(arb);
	}
			
	@FXML
    void confirmarCriacao(MouseEvent event) {
		try {
			editarPartida();
			partidaTableView.refresh();
	    	stage.close();
		}catch(DateTimeParseException e) {
			this.labelError.setText("Data ou Hora inválida ou com formato inválido!");
		}
	}
}
