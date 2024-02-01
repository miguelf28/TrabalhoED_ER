package org.Estruturas.Exceptions;

/**
 * This exception is thrown when attempting to access or manipulate an empty collection.
 *
 * @author 8190357 - Marcelo Barbosa
 * @author 8180325 - Miguel Fonseca
 */
public class EmptyCollectionException extends Exception {
    /**
     * Constructs an EmptyCollectionException with the specified detail message.
     *
     * @param message the detail message
     */
    public EmptyCollectionException(String message) {
        super(message);

    }
}
