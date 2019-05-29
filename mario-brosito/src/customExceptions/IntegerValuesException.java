package customExceptions;

public class IntegerValuesException extends Exception{
		private String cadena;
		private String customMessage;
		
		private static final long serialVersionUID = 1L;

		public IntegerValuesException(String cadena ) {
			super("The string: "+cadena+" is ilegal uncompatible");
			this.cadena= cadena;
			customMessage = decideMessageISE();
		}
		
		public String getCustomMessage() {
			return customMessage;
		}
		
		public String decideMessageISE() {
			if(isNumeric(cadena) ) {
				customMessage = "Enter valid name";
			}else {
				customMessage ="Please enter the required values";
			}
			return customMessage;
		}
		public boolean isNumeric(String cadena){
			try {
				Integer.parseInt(cadena);
				return true;
			} catch (NumberFormatException nfe){
				return false;
			}
		}
		@Override
		public String getMessage() {
			return getCustomMessage();
		}
		
	}
