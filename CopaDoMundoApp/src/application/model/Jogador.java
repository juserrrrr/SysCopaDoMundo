package application.model;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Classe contendo a abstração do Jogador exigido pelo programa.
 * 
 * @author João Victor Alves Cerqueira
 * @author José Gabriel de Almeida Pontes
 *
 */
public class Jogador {
	public static AtomicInteger codSeq = new AtomicInteger();
	private static String[] listaPosicoes = { "Atacante", "Goleiro", "Meio-Campo", "Defensor" };

	private int codJog;
	private String nome;
	private int posicao;
	private Map<Integer,JogPartida> jogPartidas = new HashMap<Integer, JogPartida>();

	public Jogador(String nome, int posicao) {
		setCodJog(codSeq.incrementAndGet());
		setNome(nome);
		setPosicao(posicao);
	}

	public int getCodJog() {
		return codJog;
	}

	public void setCodJog(int codJog) {
		this.codJog = codJog;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getPosicao() {
		return listaPosicoes[posicao];
	}

	public void setPosicao(int posicao) {
		this.posicao = posicao;
	}

	public int getQntCartoesAmarelos() {
		int qntCartoesAmarelos = 0;
		for(JogPartida jog: this.jogPartidas.values()){
			qntCartoesAmarelos += jog.getQntCartoesAmarelos();
		}
		return qntCartoesAmarelos;
	}

	public int getQntCartoesVermelhos() {
		int qntCartoesVermelhos = 0;
		for(JogPartida jog: this.jogPartidas.values()){
			qntCartoesVermelhos += jog.getQntCartoesVermelhos();
		}
		return qntCartoesVermelhos;
	}

	public int getQntGols() {
		int qntGols = 0;
		for(JogPartida jog: this.jogPartidas.values()){
			qntGols += jog.getQntGols();
		}
		return qntGols;
	}

	public static String[] getListaPosicoes() {
		return listaPosicoes;
	}

	public Map<Integer, JogPartida> getJogPartidas() {
		return jogPartidas;
	}

	public void setJogPartidas(Map<Integer, JogPartida> jogPartidas) {
		this.jogPartidas = jogPartidas;
	}

	@Override
	public String toString() {
		return "Codigo do Jogador: " + this.codJog + "\nNome: " + this.nome + "\nPosicao: "
				+ listaPosicoes[this.posicao] + "\nQuantidade de Cartoes Amarelos: " + this.getQntCartoesAmarelos()
				+ "\nQuantidade de Cartoes Vermelhos: " + this.getQntCartoesVermelhos() + "\nQuantidade de Gols: "
				+ this.getQntGols();

	}

}
