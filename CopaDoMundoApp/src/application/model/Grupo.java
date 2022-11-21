package application.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Classe responsavel pelo gerencimento dos grupos, criacao, edicao e demais funcoes que alteram diretamento o grupo.
 * @author Joao Victor e José Gabriel
 */

public class Grupo {
	
	private List<Selecao> selecoes = new ArrayList<Selecao>();
	private Map<Integer, Partida> partidas = new HashMap<Integer, Partida>();
	

	public Grupo(Selecao selecao1, Selecao selecao2, Selecao selecao3, Selecao selecao4) {
		addSelecoes(selecao1);
		addSelecoes(selecao2);
		addSelecoes(selecao3);
		addSelecoes(selecao4);
		createPartidas(selecoes);
	}	

	/**
	 * Função responsável por criar as partidas de cada seleção do grupo
	 * @param selecoes
	 */
	private void createPartidas(List<Selecao> selecoes) {
		Partida partida1 = new Partida(selecoes.get(0), selecoes.get(1));
		Partida partida2 = new Partida(selecoes.get(2), selecoes.get(3));
		Partida partida3 = new Partida(selecoes.get(0), selecoes.get(2));
		Partida partida4 = new Partida(selecoes.get(3), selecoes.get(1));
		Partida partida5 = new Partida(selecoes.get(0), selecoes.get(3));
		Partida partida6 = new Partida(selecoes.get(1), selecoes.get(2));
		partidas.put(partida1.getCodPart(), partida1);
		partidas.put(partida2.getCodPart(), partida2);
		partidas.put(partida3.getCodPart(), partida3);
		partidas.put(partida4.getCodPart(), partida4);
		partidas.put(partida5.getCodPart(), partida5);
		partidas.put(partida6.getCodPart(), partida6);		
	}

	public List<Selecao> getSelecoes() {
		return selecoes;
	}

	public void addSelecoes(Selecao selecao) {
		this.selecoes.add(selecao);
	}

	public Map<Integer, Partida> getPartidas() {
		return partidas;
	}
	
	public void setPartidas(Map<Integer, Partida> partidas) {
		this.partidas = partidas;
	}
}
