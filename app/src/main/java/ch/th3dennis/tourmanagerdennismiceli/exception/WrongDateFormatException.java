package ch.th3dennis.tourmanagerdennismiceli.exception;

/**
 * Exception made by Dennis to throw when a wrong Date format in a String is encountered
 */
public class WrongDateFormatException extends Exception {

    public WrongDateFormatException(String message) {
        super(message);
    }
}
