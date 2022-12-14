package application.model;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;



/**
 * Classe responsavel pelo manejamento do mata-mata, como o proprio chavemente.
 * @author Joao Victor e José Gabriel
 */
public class MataMata {
	public static MataMata gerenciadorMataMata = new MataMata();
	private List<Partida> partidasSemis = new ArrayList<Partida>();
	private List<Partida> partidasQuartas = new ArrayList<Partida>();
	private List<Selecao> quartasSelecoesGanhadoras = new ArrayList<Selecao>();
	private List<Partida> partidasOitavas = new ArrayList<Partida>();
	private Partida partidaFinal;
	private Selecao selecaoCampea;
	private boolean finalizado = false;
	private boolean iniciado = false;

	public void ordenarGrupos(){
    	List<Grupo> grupos = FaseDeGrupo.faseDeGrupo.findAll();
    	for(Grupo grupo: grupos) {
    		grupo.getSelecoes().sort(new Comparator<Selecao>() {
        		public int compare(Selecao selec1,Selecao selec2) {
        			return selec1.compareTo(selec2);	
        		}
    		});
    	}
    	setIniciado(true);
    }

	
/**
 * Função responsavel por obter as seleções ganhadoras da FaseDeGrupo.
 * @author Joao Victor e José Gabriel
 */
	public List<Selecao> obterSelecoesGanhadoras() {
		ordenarGrupos();
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
	
	
/**
 * Função responsavel por criar o mata Mata.
 * @author Joao Victor e José Gabriel
 */
	public void criarMataMata() {
		List<Selecao> selecoesGanhadoras = obterSelecoesGanhadoras();
		for(int i = 0; i<16; i+=2) {
			Partida partida = new Partida(selecoesGanhadoras.get(i), selecoesGanhadoras.get(i+1));
			this.partidasOitavas.add(partida);
		}
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

	public boolean isFinalizado() {
		return finalizado;
	}

	public void setFinalizado(boolean finalizado) {
		this.finalizado = finalizado;
	}
	
	public boolean isIniciado() {
		return iniciado;
	}

	public void setIniciado(boolean iniciado) {
		this.iniciado = iniciado;
	}

	

}
