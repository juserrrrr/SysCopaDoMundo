 package application.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import application.model.dao.JogadorDAO;
import application.model.dao.SelecaoDAO;
import application.model.dao.TecnicoDAO;
import application.model.excecoes.EmptyMapException;
import application.model.excecoes.JogadoresInsuficientesException;
import application.model.excecoes.SelecoesInsuficientesException;
import application.model.excecoes.TecnicosInsuficientesException;

/**
 * Classe responsavel pelo manejamento de toda a fase de grupo, como tambem armazenamento e controle dos grupos e partidas de toda a copa.
 * @author Joao Victor e José Gabriel
 */
public class FaseDeGrupo {
	
	private List<Grupo> grupos = new ArrayList<Grupo>();
	private boolean fechada = false; //Pra vericação se a fase de grupo foi fechada ou não
	
	/**
	 * Função responsável por verificar se existem 32 selecoes cadastradas, se todas elas possuem 11 jogadores e 1 tecnico separa-las em 8 grupos
	 * @param selecaoDao lista de selecoes
	 * @throws EmptyMapException caso lista de selecoes vazia
	 * @throws SelecoesInsuficientesException caso numero de selecoes menor que 32
	 * @throws JogadoresInsuficientesException caso numero de jogadores por selecao menor que 11
	 * @throws TecnicosInsuficientesException caso uma das selecoes nao possua tecnico
	 */
	public void createFase(SelecaoDAO selecaoDao) throws EmptyMapException, SelecoesInsuficientesException, JogadoresInsuficientesException, TecnicosInsuficientesException {
		Map<Integer, Selecao> todasSelecoes = selecaoDao.findAll();
		
		if(todasSelecoes.size() < 32){
			throw new SelecoesInsuficientesException();
		}
		
		String selecoesIncompletas= "";
		for(Selecao selecao: todasSelecoes.values()){
			JogadorDAO jogadorDao = selecao.getJogadoresDao();
			try {
				if(jogadorDao.findAll().size() < 11){
					selecoesIncompletas += "\n" + selecao.getNome();
				}
			} catch (EmptyMapException e) {
				selecoesIncompletas += "\n" + selecao.getNome();
			}
		}
		
		if(!selecoesIncompletas.isEmpty()){
			throw new JogadoresInsuficientesException(selecoesIncompletas);
		}	
		
		String tecnicosIncompletos= "";
		for(Selecao selecao: todasSelecoes.values()){
			TecnicoDAO tecnicoDao = selecao.getTecnicoDao();
			if(tecnicoDao.read().getNome() == null){
				tecnicosIncompletos += "\n" + selecao.getNome();
				}
		} 
		
		if(!tecnicosIncompletos.isEmpty()){
			throw new TecnicosInsuficientesException(tecnicosIncompletos);
		}	
		
		List<Selecao> selecoes = new ArrayList<Selecao>(todasSelecoes.values());
		Random rand = new Random();
		int cont = 32;
		for(int i = 0;i < 8;i++){
			Selecao selecoesGrupo[] = {null,null,null,null};
			for(int j = 0; j < 4; j++) {
				int numAleatorio = rand.nextInt(cont);
				selecoesGrupo[j] = selecoes.get(numAleatorio);
				selecoes.remove(numAleatorio);
				cont--;
			}
			Grupo grupo = new Grupo(selecoesGrupo[0],selecoesGrupo[1],selecoesGrupo[2],selecoesGrupo[3]);
			grupos.add(grupo);
			
		}
		setFechada(true);
	}
	/**
	 * Furnção responsável por retornar uma lista com todos os grupos da fase de grupos
	 * @return Lista de grupos
	 * @throws NullPointerException caso a lista de grupo esteja vazia
	 */
	public List<Grupo> findAll() throws NullPointerException {
		if(this.grupos.isEmpty()){
			throw new NullPointerException("Lista de grupos vazia!");
		}
		return this.grupos;
	}
	
	/**
	 * Função responsável por informar se a fase de grupo já foi iniciada
	 * @return true se fechada, false se não fechada
	 */
	public boolean isFechada() {
		return fechada;
	}
	/**
	 * Função responsável por fechar a fase de grupos
	 * @param fechada
	 */
	private void setFechada(boolean fechada) {
		this.fechada = fechada;
	}
}
