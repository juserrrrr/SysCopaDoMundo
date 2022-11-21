package application.model.dao;

import application.model.Tecnico;
import application.model.interfaces.ITecnicoDAO;

public class TecnicoDAO implements ITecnicoDAO {
	
	Tecnico tecnico = new Tecnico(null);

	@Override
	public void create(Tecnico tec) throws NullPointerException{
		if (this.tecnico.getNome() != null) {
			throw new NullPointerException("Ja existe um tecnico nesta selecao.");
		} 
		this.tecnico.setNome(tec.getNome());
	}

	@Override
	public void update(Tecnico tec) {
		this.tecnico.setNome(tec.getNome());
	}

	@Override
	public void delete() throws NullPointerException{
		if (this.tecnico.getNome() == null) {
			throw new NullPointerException("Esta selecao, ja nao possui um tecnico.");
		} 
		this.tecnico.setNome(null);
	}

	@Override
	public Tecnico read(){
		return tecnico;
	}
}
