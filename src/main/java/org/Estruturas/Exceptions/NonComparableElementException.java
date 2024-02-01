package org.Estruturas.Exceptions;

/**
 * This exception is thrown when an attempt to compare non-comparable elements occurs.
 *
 * @author 8190357 - Marcelo Barbosa
 * @author 8180325 - Miguel Fonseca
 */
public class NonComparableElementException extends Throwable {

    /**
     * Constructs a NonComparableElementException with no specified detail message.
     */
    public NonComparableElementException() {
    }

    /**
     * Constructs a NonComparableElementException with the specified detail message.
     *
     * @param msg the detail message
     */
    public NonComparableElementException(String msg) {
        super(msg);
    }
}
