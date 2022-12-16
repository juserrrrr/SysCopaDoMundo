package application.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.ResourceBundle;

import application.model.FaseDeGrupo;
import application.model.Grupo;
import application.model.MataMata;
import application.model.Partida;
import application.model.Selecao;
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
    
    
    
    public void alterarOitavas(VBox oitavasTela,MataMata mataMataGerenciador,int contadorPartidas) {
    	for(int i = 0; i<4; i++) {
        	BorderPane borderP = (BorderPane) oitavasTela.getChildren().get(i);
        	VBox vboxNome = (VBox) borderP.getCenter();
        	VBox vboxGols = (VBox) borderP.getRight();
        	Partida partida = mataMataGerenciador.getPartidasOitavas().get(contadorPartidas);
        	contadorPartidas++;
        	
    		Label labelNome1 = (Label) vboxNome.getChildren().get(0);
    		labelNome1.setText(partida.getSelecao1().getNome());
    		Label labelGol1 = (Label) vboxGols.getChildren().get(0);
    		labelGol1.setText(Integer.toString(partida.getGolsTime1()));
    		
    		Label labelNome2 = (Label) vboxNome.getChildren().get(2);
    		labelNome2.setText(partida.getSelecao2().getNome());
    		Label labelGol2 = (Label) vboxGols.getChildren().get(2);
    		labelGol2.setText(Integer.toString(partida.getGolsTime2()));
        }
    }
    
    public void alterarQuartas(VBox quartasTela,MataMata mataMataGerenciador,int contadorPartidas) {
    	List<Partida> partidas = mataMataGerenciador.getPartidasQuartas();	
		for(int i = 0; i<2; i++) {
			BorderPane borderP = (BorderPane) quartasTela.getChildren().get(i);
			VBox vboxNome = (VBox) borderP.getCenter();
			VBox vboxGols = (VBox) borderP.getRight();
			Partida partida = null;
			try {
				partida = partidas.get(contadorPartidas);				
			} catch (Exception ignore) {}
			contadorPartidas++;
			
			Label labelNome1 = (Label) vboxNome.getChildren().get(0);
			labelNome1.setText(partida != null ? partida.getSelecao1().getNome():"Ganhador");
			Label labelGol1 = (Label) vboxGols.getChildren().get(0);
			labelGol1.setText(Integer.toString(partida != null ? partida.getGolsTime1():0));
			
			Label labelNome2 = (Label) vboxNome.getChildren().get(2);
			labelNome2.setText(partida != null ? partida.getSelecao2().getNome():"Ganhador");
			Label labelGol2 = (Label) vboxGols.getChildren().get(2);
			labelGol2.setText(Integer.toString(partida != null ? partida.getGolsTime2():0));
		}
    }
    
    public void alterarSemis(HBox quartasTela,MataMata mataMataGerenciador) {
    	List<Partida> partidas = mataMataGerenciador.getPartidasSemis();
    	if(partidas.size() > 0) {			
    		for(int i = 0; i<2; i++) {
    			BorderPane borderP = (BorderPane) quartasTela.getChildren().get(i);
    			VBox vboxNome = (VBox) borderP.getCenter();
    			VBox vboxGols = (VBox) borderP.getRight();
    			Partida partida = null;
    			try {
    				partida = partidas.get(i);				
    			} catch (Exception ignore) {}
      			
    			Label labelNome1 = (Label) vboxNome.getChildren().get(0);
    			labelNome1.setText(partida != null ? partida.getSelecao1().getNome():"Ganhador");
    			Label labelGol1 = (Label) vboxGols.getChildren().get(0);
    			labelGol1.setText(Integer.toString(partida != null ? partida.getGolsTime1():0));
    			
    			Label labelNome2 = (Label) vboxNome.getChildren().get(2);
    			labelNome2.setText(partida != null ? partida.getSelecao2().getNome():"Ganhador");
    			Label labelGol2 = (Label) vboxGols.getChildren().get(2);
    			labelGol2.setText(Integer.toString(partida != null ? partida.getGolsTime2():0));
    		}
    	}
    }
    
    public void alterarFinal(BorderPane borderPane,MataMata mataMataGerenciador) {
    	VBox vboxNome = (VBox) borderPane.getCenter();
    	VBox vboxGols = (VBox) borderPane.getRight();
    	Partida partida = mataMataGerenciador.getPartidaFinal();
    	
    	Label labelNome1 = (Label) vboxNome.getChildren().get(0);
		labelNome1.setText(partida != null ? partida.getSelecao1().getNome():"Ganhador");
		Label labelGol1 = (Label) vboxGols.getChildren().get(0);
		labelGol1.setText(Integer.toString(partida != null ? partida.getGolsTime1():0));
		
		Label labelNome2 = (Label) vboxNome.getChildren().get(2);
		labelNome2.setText(partida != null ? partida.getSelecao2().getNome():"Ganhador");
		Label labelGol2 = (Label) vboxGols.getChildren().get(2);
		labelGol2.setText(Integer.toString(partida != null ? partida.getGolsTime2():0));
    }

    @FXML
    void initialize() {
    	MataMata mataMataGerenciador = MataMata.gerenciadorMataMata;
    	Selecao selecaoCampea = mataMataGerenciador.getSelecaoCampea();
        alterarOitavas(oitavasTela1,mataMataGerenciador,0);
        alterarOitavas(oitavasTela2,mataMataGerenciador,4);
        alterarQuartas(quartasTela1,mataMataGerenciador,0);
        alterarQuartas(quartasTela2,mataMataGerenciador,2);
        alterarSemis(semisTela,mataMataGerenciador);
        alterarFinal(campeaoTela,mataMataGerenciador);
        this.labelCampeao.setText(selecaoCampea != null?selecaoCampea.getNome():"Ganhador");
        

    }

}
