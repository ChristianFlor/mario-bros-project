package customExceptions;

public class UncompatibleValuesException extends Throwable{

	private static final long serialVersionUID = 1L;
	private String customMessage;
	private String value1;
	private String value2;

	public UncompatibleValuesException(String value1, String value2) {
		
		super("The values: "+value1+" and "+value2+"are uncompatible");
		customMessage = decideMessageUVE();
	}
	public String getCustomMessage() {
		return customMessage;
	}
	/**
	 * This method verifies when the starting point with the chosen address is not compatible.
	 * @return an exception message
	 */
	public String decideMessageUVE() {
		if(value1.equals("Id")) {
			if(value2.equals("SOUTHWEST") || value2.equals("SOUTHEAST")) {
				customMessage= " uncompatible values";
			}
		}else if(value1.equals("SOUTH")){
			if(value2.equals("NORTHWEST") || value2.equals("NORTHEAST")) {
				customMessage= " uncompatible values";
			}
		}else if(value1.equals("EAST")){
			if(value2.equals("NORTHWEST") || value2.equals("SOUTHWEST")) {
				customMessage= " uncompatible values";
			}
		}else if(value1.equals("WEST")){
			if(value2.equals("SOUTHEAST") || value2.equals("NORTHEAST")) {
				customMessage= " uncompatible values";
			}
		}
		return customMessage;
	}
	@Override
	public String getMessage() {
		return getCustomMessage();
	}
		
}