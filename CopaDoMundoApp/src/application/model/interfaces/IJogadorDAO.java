package application.model.interfaces;

import java.util.Map;

import javax.naming.SizeLimitExceededException;

import application.model.Jogador;
import application.model.excecoes.EmptyMapException;

/**
 * Interface responsavel por pre-moldar os metodos padrões do CRUD no JogadorDAO
 * 
 * @author João Victor Alves Cerqueira
 * @author José Gabriel de Almeida Pontes
 *
 */
public interface IJogadorDAO {
	public void create(Jogador jog) throws SizeLimitExceededException;

	public void update(int id, Jogador jog);

	public void delete(int id) throws NullPointerException;

	public Jogador findById(int id) throws NullPointerException;

	public Map<Integer, Jogador> findAll() throws EmptyMapException;

}