package application.model.interfaces;

import application.model.Tecnico;

/**
 * Interface responsavel por pre-moldar os metodos padrões do CRUD no TecnicoDAO
 * 
 * @author João Victor Alves Cerqueira
 * @author José Gabriel de Almeida Pontes
 *
 */
public interface ITecnicoDAO {
	public void create(Tecnico tec) throws NullPointerException;

	public void update(Tecnico tec);

	public void delete() throws NullPointerException;

	public Tecnico read();
}
