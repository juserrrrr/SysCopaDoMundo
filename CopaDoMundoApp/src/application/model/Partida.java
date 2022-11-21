package application.model;


import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Classe responsavel pelo manejamento de partida, como valores, local, gols e muitas outras funcionalidades.
 * @author Joao Victor e José Gabriel
 */
public class Partida {
	private static AtomicInteger codSeq = new AtomicInteger();
	
	private int codPart;
	private Selecao selecao1;
	private Selecao selecao2;
	private Arbitro arbitro;
	private LocalDate data = null;
	private LocalTime horario = null;
	private String local = null;
	private int golsTime1 = 0;
	private int golsTime2 = 0;
	private boolean finalizada = false;
	private Map<Integer, JogPartida> jogPartidas = new HashMap<Integer, JogPartida>();

	public Partida(Selecao selecao1, Selecao selecao2) {
		setCodPart(codSeq.incrementAndGet());
		setSelecao1(selecao1);
		setSelecao2(selecao2);
	}

	public int getCodPart() {
		return codPart;
	}


	public void setCodPart(int codPart) {
		this.codPart = codPart;
	}


	public Selecao getSelecao1() {
		return selecao1;
	}


	public void setSelecao1(Selecao selecao1) {
		this.selecao1 = selecao1;
	}


	public Selecao getSelecao2() {
		return selecao2;
	}


	public void setSelecao2(Selecao selecao2) {
		this.selecao2 = selecao2;
	}


	public LocalDate getData() {
		return data;
	}


	public void setData(LocalDate data) {
		this.data = data;
	}


	public LocalTime getHorario() {
		return horario;
	}


	public void setHorario(LocalTime horario) {
		this.horario = horario;
	}


	public String getLocal() {
		return local;
	}


	public void setLocal(String local) {
		this.local = local;
	}


	public int getGolsTime1() {
		return golsTime1;
	}


	public void setGolsTime1(int golsTime1) {
		this.golsTime1 = golsTime1;
	}


	public int getGolsTime2() {
		return golsTime2;
	}


	public void setGolsTime2(int golsTime2) {
		this.golsTime2 = golsTime2;
	}

	public boolean isFinalizada() {
		return finalizada;
	}

	public void setFinalizada(boolean finalizada) {
		this.finalizada = finalizada;
	}

	public Map<Integer, JogPartida> getJogPartidas() {
		return jogPartidas;
	}

	public void setJogPartidas(Map<Integer, JogPartida> jogPartidas) {
		this.jogPartidas = jogPartidas;
	}

	public Arbitro getArbitro() {
		return arbitro;
	}

	public void setArbitro(Arbitro arbitro) {
		this.arbitro = arbitro;
	}
	
}
