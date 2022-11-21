package application.model.interfaces;

import java.util.Map;

import javax.naming.SizeLimitExceededException;

import application.model.Selecao;

/**
 * Interface responsavel por pre-moldar os metodos padrões do CRUD na SelecaoDAO
 * 
 * @author João Victor Alves Cerqueira
 * @author José Gabriel de Almeida Pontes
 *
 */
public interface ISelecaoDAO {
	public void create(Selecao sel) throws SizeLimitExceededException;

	public void update(int id, Selecao sel);

	public void delete(int id);

	public Selecao findById(int id);

	public Map<Integer, Selecao> findAll() throws Exception;
}
