package application.model;

import java.util.ArrayList;
import java.util.List;

public class MataMata {
	public static MataMata gerenciadorMataMata = new MataMata();
	
	private List<Partida> partidasSemis;
	private List<Partida> partidasQuartas;
	private List<Partida> partidasOitavas;
	private Partida partidaFinal;
	private Selecao selecaoCampea;
	
	public List<Selecao> obterSelecoesGanhadoras() {
		List<Grupo> grupos	= FaseDeGrupo.faseDeGrupo.findAll();
		List<Selecao> selecoes = new ArrayList<Selecao>();
		for(int i = 0;i<8;i+=2) {
			selecoes.add(grupos.get(i).getSelecoes().get(0));
			selecoes.add(grupos.get(i+1).getSelecoes().get(1));
			selecoes.add(grupos.get(i+1).getSelecoes().get(0));
			selecoes.add(grupos.get(i).getSelecoes().get(1));
		}
		return selecoes;
	}
	
	public void criarMataMata() {
		List<Selecao> selecoesSeparadas = obterSelecoesGanhadoras();
		for(Selecao selecao: selecoesSeparadas) {
			System.out.println(selecao.getNome());
		}
		
	}
	
	public static MataMata getGerenciadorMataMata() {
		return gerenciadorMataMata;
	}


	public static void setGerenciadorMataMata(MataMata gerenciadorMataMata) {
		MataMata.gerenciadorMataMata = gerenciadorMataMata;
	}


	public List<Partida> getPartidasSemis() {
		return partidasSemis;
	}


	public void setPartidasSemis(List<Partida> partidasSemis) {
		this.partidasSemis = partidasSemis;
	}


	public List<Partida> getPartidasQuartas() {
		return partidasQuartas;
	}


	public void setPartidasQuartas(List<Partida> partidasQuartas) {
		this.partidasQuartas = partidasQuartas;
	}


	public List<Partida> getPartidasOitavas() {
		return partidasOitavas;
	}


	public void setPartidasOitavas(List<Partida> partidasOitavas) {
		this.partidasOitavas = partidasOitavas;
	}


	public Partida getPartidaFinal() {
		return partidaFinal;
	}


	public void setPartidaFinal(Partida partidaFinal) {
		this.partidaFinal = partidaFinal;
	}


	public Selecao getSelecaoCampea() {
		return selecaoCampea;
	}


	public void setSelecaoCampea(Selecao selecaoCampea) {
		this.selecaoCampea = selecaoCampea;
	}


	

}
