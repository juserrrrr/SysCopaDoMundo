package application.model;

import java.util.concurrent.atomic.AtomicInteger;

import application.model.dao.ArbitroDAO;

/**
 * Classe contendo a abstração do Arbitro exigido pelo programa.
 * 
 * @author João Victor Alves Cerqueira
 * @author José Gabriel de Almeida Pontes
 *
 */
public class Arbitro {
	
	public static ArbitroDAO arbitroDao = new ArbitroDAO();
	private static AtomicInteger codSeq = new AtomicInteger();

	private int codArb;
	private String nome;

	public Arbitro(String nome) {
		setCodArb(codSeq.incrementAndGet());
		setNome(nome);
	}

	public int getCodArb() {
		return codArb;
	}

	public void setCodArb(int codArb) {
		this.codArb = codArb;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@Override
	public String toString() {
		return "Codigo do Arbitro: " + this.codArb + "\nNome: " + this.nome;
	}

}
