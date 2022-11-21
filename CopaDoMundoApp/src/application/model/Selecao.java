package application.model;

import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import application.model.dao.JogadorDAO;
import application.model.dao.TecnicoDAO;
import application.model.excecoes.EmptyMapException;

/**
 * Classe contendo a abstração da Seleção exigido pelo programa.
 * 
 * @author João Victor Alves Cerqueira
 * @author José Gabriel de Almeida Pontes
 *
 */

public class Selecao {
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

	@Override
	public String toString() {
		String jogadores = "";
		try {
			Map<Integer, Jogador> todosJogadores = this.jogadoresDao.findAll();
			for (Jogador jog : todosJogadores.values()) {
				jogadores += "\n" + jog.getCodJog() + " - " + jog.getNome();
			}
		}catch(EmptyMapException e) {
			jogadores = "O time nao possui jogadores!";
		}
		
		return "ID da Selecao: " + this.codSel + "\nSelecao: " + this.nome + "\nTecnico: " + (this.tecnicoDao.read().getNome() == null ? "Nao possui" : this.tecnicoDao.read().getNome())
				+ "\nJogadores: " + jogadores;
	}

}
