package application.controller;

import java.net.URL;
import java.util.Collection;
import java.util.ResourceBundle;

import application.MockarValores;
import application.model.Arbitro;
import application.model.FaseDeGrupo;
import application.model.Selecao;
import application.model.dao.ArbitroDAO;
import application.model.dao.SelecaoDAO;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

public class HomePageController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label qtdArbitros;

    @FXML
    private Label qtdJogadores;

    @FXML
    private Label qtdSelecoes;
    
    @FXML
    private VBox bttPovoamento;
	
    @FXML
    void initialize() {
    	String qntdArbitros = contarArbitros();
    	String qntdJogadores = contarJogadores();
    	String qntdSelecoes = contarSelecoes();
    	atualizarLabels(qntdArbitros, qntdJogadores, qntdSelecoes);
        if(!qntdArbitros.equals("0") || !qntdJogadores.equals("0") || !qntdSelecoes.equals("0")) {
        	bttPovoamento.setVisible(false);
        }
        

    }
    
    private void atualizarLabels(String qntdArbitros, String qntdJogadores,String qntdSelecoes) {
        qtdArbitros.setText(qntdArbitros);
        qtdJogadores.setText(qntdJogadores + "/352");
        qtdSelecoes.setText(qntdSelecoes + "/32");
    }
	
	private String contarSelecoes(){
        SelecaoDAO selecDao = Selecao.selecaoDao;
       	Integer qntSelecoes = 0;
        try {
	        qntSelecoes = selecDao.findAll().size();			
		} catch (Exception ignore) {}
		return qntSelecoes.toString();
    } 
     
	private String contarJogadores(){
		SelecaoDAO selecDao = Selecao.selecaoDao;
        Collection<Selecao> selecoes;
       	int qntJogadores = 0;
        try {
	        selecoes = selecDao.findAll().values();		
	        for(Selecao selec: selecoes){
                try {					
	                qntJogadores += selec.getJogadoresDao().findAll().size();
				} catch (Exception ignore) {}
            }	
		} catch (Exception ignore) {}
		return Integer.toString(qntJogadores);
    }  
    
	private String contarArbitros(){
        ArbitroDAO arbitroDao = Arbitro.arbitroDao;
       	int qntdArbitros = 0;
        try {
	        qntdArbitros = arbitroDao.findAll().size();			
		} catch (Exception ignore) {}
		return Integer.toString(qntdArbitros);
    }  
	
	@FXML
    void povoarSistema(MouseEvent event) {
		MockarValores.MockSelecoes(Selecao.selecaoDao);
		MockarValores.MockJogadores(Selecao.selecaoDao);
		MockarValores.MockTecnicos(Selecao.selecaoDao);
		MockarValores.MockPartidas(FaseDeGrupo.faseDeGrupo, Selecao.selecaoDao);
		atualizarLabels("0","352","32");
		bttPovoamento.setVisible(false);
    }
    

}
