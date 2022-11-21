package application.model.dao;

import java.util.HashMap;
import java.util.Map;

import javax.naming.SizeLimitExceededException;

import application.model.Jogador;
import application.model.excecoes.EmptyMapException;
import application.model.interfaces.IJogadorDAO;

public class JogadorDAO implements IJogadorDAO {
	Map<Integer, Jogador> jogadores = new HashMap<Integer, Jogador>();

	@Override
	public void create(Jogador jog) throws SizeLimitExceededException {
		if(this.jogadores.size() >= 11) {
			throw new SizeLimitExceededException("Limite de  11 jogadores excedido!");
		}
		this.jogadores.put(jog.getCodJog(), jog);

	}

	@Override
	public void update(int id, Jogador jog) {
		this.jogadores.put(id, jog);
	}

	@Override
	public void delete(int id) throws NullPointerException{
		Jogador jog = this.jogadores.get(id);
		if(jog == null) {
			throw new NullPointerException("Nao existe um jogador com este ID");
		}
		this.jogadores.remove(id);

	}

	@Override
	public Jogador findById(int id) throws NullPointerException {
		Jogador jog = this.jogadores.get(id);
		if(jog == null) {
			throw new NullPointerException("Nao existe um jogador com este ID");
		}
		return jog;
	}

	@Override
	public Map<Integer, Jogador> findAll() throws EmptyMapException {
		if(this.jogadores.isEmpty()){
			throw new EmptyMapException("jogadores");
		}
		return this.jogadores;

	}
}
