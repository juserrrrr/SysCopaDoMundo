package application.model;

import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import application.model.dao.JogadorDAO;
import application.model.dao.SelecaoDAO;
import application.model.dao.TecnicoDAO;
import application.model.excecoes.EmptyMapException;
import javafx.scene.control.ListView;

/**
 * Classe contendo a abstração da Seleção exigido pelo programa.
 * 
 * @author João Victor Alves Cerqueira
 * @author José Gabriel de Almeida Pontes
 *
 */

public class Selecao {
	
	public static SelecaoDAO selecaoDao = new SelecaoDAO();
	public static AtomicInteger codSeq = new AtomicInteger();

	private int codSel;
	private String nome;
	private JogadorDAO jogadoresDao = new JogadorDAO();
	private TecnicoDAO tecnicoDao = new TecnicoDAO();
	private int pontos;
	private int saldoGols;

	public Selecao(String nome) {
		setCodSel(codSeq.incrementAndGet());
		setNome(nome);
	}

	public int getCodSel() {
		return codSel;
	}

	public void setCodSel(int codSel) {
		this.codSel = codSel;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public JogadorDAO getJogadoresDao() {
		return jogadoresDao;
	}

	public void setJogadoresDao(JogadorDAO jogadoresDao) {
		this.jogadoresDao = jogadoresDao;
	}

	public TecnicoDAO getTecnicoDao() {
		return tecnicoDao;
	}

	public void setTecnico(TecnicoDAO tecnicoDao) {
		this.tecnicoDao = tecnicoDao;
	}
	
	public int getPontos() {
		return pontos;
	}

	public void setPontos(int pontos) {
		this.pontos = pontos;
	}

	public int getSaldoGols() {
		return saldoGols;
	}

	public void setSaldoGols(int saldoGols) {
		this.saldoGols = saldoGols;
	}

	public int getQntdJogadores() {
		try {
			return this.jogadoresDao.findAll().size();
		} catch (EmptyMapException e) {
			return 0;
		}
	}

	public String getNomeTecnico() {
		return this.tecnicoDao.read().getNome();
	}
	
	
	public ListView<String> getNomeJogadores() {
		Map<Integer, Jogador> todosJogadores;
		try {
			todosJogadores = this.jogadoresDao.findAll();
		} catch (EmptyMapException e) {
			return null;
		}
	    ListView<String> jogadores = new ListView<String>();
	    jogadores.setPrefHeight(80);
		for (Jogador jog : todosJogadores.values()) {
			jogadores.getItems().add(jog.getCodJog() + " - " + jog.getNome());
		}
		return jogadores;
	}

	@Override
	public String toString() {
	return this.nome;
	}
}
