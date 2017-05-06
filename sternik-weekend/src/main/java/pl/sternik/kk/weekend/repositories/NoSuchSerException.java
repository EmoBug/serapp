package pl.sternik.kk.weekend.repositories;

public class NoSuchSerException extends Exception {
    private static final long serialVersionUID = -8555511053844242536L;

    public NoSuchSerException(String string) {
		super(string);
	}

	public NoSuchSerException() {
	}


}
