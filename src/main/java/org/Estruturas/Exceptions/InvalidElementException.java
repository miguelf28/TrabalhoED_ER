package org.Estruturas.Exceptions;

/**
 * This exception is thrown when an invalid element is encountered.
 *
 * @author 8190357 - Marcelo Barbosa
 * @author 8180325 - Miguel Fonseca
 */
public class InvalidElementException extends Throwable {

    /**
     * Constructs an InvalidElementException with no specified detail message.
     */
    public InvalidElementException() {
    }

    /**
     * Constructs an InvalidElementException with the specified detail message.
     *
     * @param msg the detail message
     */
    public InvalidElementException(String msg) {
        super(msg);
    }
}