package application.model.excecoes;

/**
 * Exceção que indica que uma estrutura MAP está vazia
 * @author Joao Victor e Joao Gabriel
 *
 */
@SuppressWarnings("serial")
public class EmptyMapException extends Exception{
	
	public EmptyMapException(String className) {
		super("Nao existem " + className + " para executar esta acao!");
	}		
}
