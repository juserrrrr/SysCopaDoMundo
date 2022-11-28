package application.controller;

import java.net.URL;
import java.util.Collection;
import java.util.ResourceBundle;

import application.model.Arbitro;
import application.model.Selecao;
import application.model.dao.ArbitroDAO;
import application.model.dao.SelecaoDAO;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

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
    void initialize() {
        qtdArbitros.setText(contarArbitros());
        qtdJogadores.setText(contarJogadores() + "/352");
        qtdSelecoes.setText(contarSelecoes() + "/32");

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
    

}
