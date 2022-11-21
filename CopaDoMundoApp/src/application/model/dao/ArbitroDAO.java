package application.model.dao;

import java.util.HashMap;
import java.util.Map;

import application.model.Arbitro;
import application.model.excecoes.EmptyMapException;
import application.model.interfaces.IArbitroDAO;

public class ArbitroDAO implements IArbitroDAO {
	Map<Integer, Arbitro> arbitros = new HashMap<Integer, Arbitro>();

	@Override
	public void create(Arbitro arb) {
		this.arbitros.put(arb.getCodArb(), arb);
	}

	@Override
	public void update(int id, Arbitro arb) {
		this.arbitros.put(id, arb);
	}

	@Override
	public void delete(int id) {
		this.arbitros.remove(id);
	}

	@Override
	public Arbitro findById(int id) throws NullPointerException{
		Arbitro arb = arbitros.get(id);
		if(arb == null) {
			throw new NullPointerException("Nao existe arbitro com esse ID");
		}
		return arb;
	}

	@Override
	public Map<Integer, Arbitro> findAll() throws EmptyMapException{
		if(this.arbitros.isEmpty()) {
			throw new EmptyMapException("arbitros");
		}
		return arbitros;
	}

}
