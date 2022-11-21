package application.model.excecoes;
/**
 * Exceção que indica que não há tecnicos nas seleções informadas para que se possa iniciar a fase de grupos
 * @author João Victor e José Gabriel
 *
 */
@SuppressWarnings("serial")
public class TecnicosInsuficientesException extends Exception{
	public TecnicosInsuficientesException(String selecoes) {
		super("Nao ha tecnicos na(s) selecao(oes): " + selecoes);
	}
}
