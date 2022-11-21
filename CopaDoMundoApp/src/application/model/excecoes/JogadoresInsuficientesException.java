package application.model.excecoes;

/**
 * Exceção que indica que não há jogadores suficientes nas seleções informadas para que se possa iniciar a fase de grupos
 * @author João Victor e José Gabriel
 *
 */
@SuppressWarnings("serial")
public class JogadoresInsuficientesException extends Exception{
	public JogadoresInsuficientesException(String selecoes) {
		super("Nao ha jogadores suficientes na(s) selecao(oes): " + selecoes);
	}

}
