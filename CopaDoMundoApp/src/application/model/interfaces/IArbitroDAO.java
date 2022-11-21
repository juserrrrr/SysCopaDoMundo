package application.model.interfaces;

import java.util.Map;

import application.model.Arbitro;
import application.model.excecoes.EmptyMapException;

/**
 * Interface responsavel por pre-moldar os metodos padrões do CRUD no ArbitroDAO
 * 
 * @author João Victor Alves Cerqueira
 * @author José Gabriel de Almeida Pontes
 *
 */
public interface IArbitroDAO {
	public void create(Arbitro arb);

	public void update(int id, Arbitro arb);

	public void delete(int id);

	public Arbitro findById(int id) throws NullPointerException;

	public Map<Integer, Arbitro> findAll() throws EmptyMapException;
}