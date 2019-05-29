package customExceptions;

public class IllegalInputException extends Exception {

	
	private String cadena;
	private String customMessage;
	
	private static final long serialVersionUID = 1L;

	public IllegalInputException(String cadena ) {
		super("The string: "+cadena+" is ilegal uncompatible");
		this.cadena= cadena;
		customMessage = decideMessageISE();
	}
	
	public String getCustomMessage() {
		return customMessage;
	}
	
	public String decideMessageISE() {
		if(cadena.isEmpty() || cadena==null ) {
			customMessage = "Please enter a string";
		}else {
			customMessage ="Please enter the required values";
		}
		return customMessage;
	}
	
	@Override
	public String getMessage() {
		return getCustomMessage();
	}
	
}
