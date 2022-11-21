package application.model;

/**
 * Classe contendo a abstração do Tecnico exigido pelo programa.
 * 
 * @author João Victor Alves Cerqueira
 * @author José Gabriel de Almeida Pontes
 *
 */
public class Tecnico {
	private String nome;

	public Tecnico(String nome) {
		setNome(nome);
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

}
