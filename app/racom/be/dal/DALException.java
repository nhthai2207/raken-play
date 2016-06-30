package racom.be.dal;

public class DALException extends Exception {

	public int errorCode = 0;

	public DALException(int errorCode, Exception ex) {
		super();
		this.errorCode = errorCode;
		printError();
	}

	public DALException(int errorCode) {
		super();
		this.errorCode = errorCode;
		printError();
	}

	public DALException(String format, Exception e) {
		super(format, e);
		printError();
	}

	public DALException(Exception e) {
		super(e);
		printError();
	}

	public DALException(String message) {
		super(message);

	}

	private void printError() {

	}

	public void printErrorWithContext(String context) {

	}
}
