package customExceptions;

public class IllegalInputException extends Exception {

	/**
	 * The string that is to be tested by the exception.
	 */
	private String cadena;
	
	/**
	 * The string message that the exception shows.
	 */
	private String customMessage;
	
	private static final long serialVersionUID = 1L;

	/**
	 * <b>Description:</b>
	 * This function initializes a new illegal input exception.
	 * @param cadena The string to be tested by the exception.
	 */
	public IllegalInputException(String cadena ) {
		super("The string: "+cadena+" is ilegal uncompatible");
		this.cadena= cadena;
		customMessage = decideMessageISE();
	}
	
	/**
	 * <b>Description:</b>
	 * This function obtains this exception's custom message.
	 * @return This exception's custom message.
	 */
	public String getCustomMessage() {
		return customMessage;
	}
	
	/**
	 * <b>Description:</b>
	 * This function determines the custom message that is to be shown.
	 * @return The string representing the custom message.
	 */
	public String decideMessageISE() {
		if(cadena.isEmpty() || cadena==null ) {
			customMessage = "Please enter a string";
		}else {
			customMessage ="Please enter the required values";
		}
		return customMessage;
	}
	
	/**
	 *<b>Description:</b>
	 *This function obtains the exception's custom message.
	 */
	@Override
	public String getMessage() {
		return getCustomMessage();
	}
	
}
