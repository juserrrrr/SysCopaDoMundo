package application.model.dao;

import java.util.HashMap;
import java.util.Map;

import javax.naming.SizeLimitExceededException;

import application.model.Selecao;
import application.model.excecoes.EmptyMapException;
import application.model.interfaces.ISelecaoDAO;

public class SelecaoDAO implements ISelecaoDAO {

	Map<Integer, Selecao> selecoes = new HashMap<Integer, Selecao>();

	@Override
	public void create(Selecao sel) throws SizeLimitExceededException{
		if(selecoes.size() >= 32) {
			throw new SizeLimitExceededException("Limite de  32 selecoes excedido!");
		}
		this.selecoes.put(sel.getCodSel(), sel);
	}

	@Override
	public void update(int id, Selecao sel) {
		this.selecoes.put(id, sel);
	}

	@Override
	public void delete(int id) {
		this.selecoes.remove(id);
	}

	@Override
	public Selecao findById(int id) throws NullPointerException{
		Selecao sel = this.selecoes.get(id);
		if (sel == null) {
			throw new NullPointerException("Nao existe uma selecao com este ID");
		}
		return sel;
	}

	@Override
	public Map<Integer, Selecao> findAll() throws EmptyMapException{
		if(this.selecoes.isEmpty()){
			throw new EmptyMapException("selecoes");
		}
		return this.selecoes;
	}
	

}
