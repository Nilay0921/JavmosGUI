package javmos.exceptions;

/**
 * This exception class is thrown whenever the user inputs an invalid polynomial
 * into the GUI.
 */
public class PolynomialException extends Exception {

    /**
     * Exception class constructor.
     */
    public PolynomialException() {
    }

    /**
     * Overloaded exception class construtor.
     *
     * @param msg A custom message that is sent to the user.
     */
    public PolynomialException(String msg) {
        super(msg);
    }
}
