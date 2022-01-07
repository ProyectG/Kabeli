package cl.ms.kabeli.utilitarios;

public class CustomException extends Exception {

	private static final long serialVersionUID = 764100699426521478L;
	
	public CustomException(String mensajeError){super(mensajeError);}
}
