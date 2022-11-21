package application.model;

/**
 * Classe responsavel pela ligacao associativa entre Jogador e Partida, contendo informaçoes respectiva das duas, como tambem seu manejamento.
 * @author Joao Victor e José Gabriel
 */

public class JogPartida {
	
	private int qntCartoesAmarelos = 0;
	private int qntCartoesVermelhos = 0;
	private int qntGols = 0;
	private Jogador jogador;
	private Partida partida;
	private int codSelecao;
	
	public JogPartida(Jogador jogador,Partida partida,int qntGols,int codSelecao) {
		setJogador(jogador);
		setPartida(partida);
		setQntGols(qntGols);
	}
	
	public Jogador getJogador() {
		return jogador;
	}
	public void setJogador(Jogador jogador) {
		this.jogador = jogador;
	}
	public Partida getPartida() {
		return partida;
	}
	public void setPartida(Partida partida) {
		this.partida = partida;
	}
	public int getQntCartoesAmarelos() {
		return qntCartoesAmarelos;
	}
	
	public void setQntCartoesAmarelos(int qntCartoesAmarelos) {
		this.qntCartoesAmarelos = qntCartoesAmarelos;
	}
	
	public int getQntCartoesVermelhos() {
		return qntCartoesVermelhos;
	}
	
	public void setQntCartoesVermelhos(int qntCartoesVermelhos) {
		this.qntCartoesVermelhos = qntCartoesVermelhos;
	}
	
	public int getCodSelecao() {
		return qntGols;
	}
	
	public void setQntGols(int qntGols) {
		this.qntGols = qntGols;
	}
	
	public int getQntGols() {
		return codSelecao;
	}
	
	public void setCodSelecao(int codSelecao) {
		this.codSelecao = codSelecao;
	}

}
