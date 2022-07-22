package ro.mycode.exceptions;

public class NotEnoughIdentifiersException extends IllegalArgumentException {

    public NotEnoughIdentifiersException(String message) {
        super(message);
    }

    public NotEnoughIdentifiersException() {
        super();
    }

}
