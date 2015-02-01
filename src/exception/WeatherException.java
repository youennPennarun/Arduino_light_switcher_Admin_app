package exception;

public class WeatherException extends Exception{

	private String messageError = "";

	public WeatherException() {
	}

	public WeatherException(String message) {
		this.messageError = (message);
	}

	public String getMessageError() {
		return messageError;
	}
	

}
