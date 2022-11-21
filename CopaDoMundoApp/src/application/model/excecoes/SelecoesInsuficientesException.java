package application.model.excecoes;
/**
 * Exceção que indica que não há selecoes suficientes para que se possa iniciar a fase de grupos
 * @author João Victor e José Gabriel
 *
 */
@SuppressWarnings("serial")
public class SelecoesInsuficientesException extends Exception {
	public SelecoesInsuficientesException() {
		super("Nao ha selecoes sufientes para iniciar a fase de grupos!");
	}

}