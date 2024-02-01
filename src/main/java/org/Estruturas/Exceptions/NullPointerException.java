package org.Estruturas.Exceptions;

/**
 * This exception is thrown when a null pointer is encountered.
 *
 * @author 8190357 - Marcelo Barbosa
 * @author 8180325 - Miguel Fonseca
 */
public class NullPointerException extends Throwable {
    /**
     * Constructs a NullPointerException with no specified detail message.
     */
    public NullPointerException() {
    }

    /**
     * Constructs a NullPointerException with the specified detail message.
     *
     * @param msg the detail message
     */
    public NullPointerException(String msg) {
        super(msg);
    }

    /**
     * This exception is thrown when an empty collection is encountered.
     */
    public static class EmptyCollectionException extends Throwable {

        /**
         * Constructs an EmptyCollectionException with the specified detail message.
         *
         * @param emptyHeap the detail message
         */
        public EmptyCollectionException(String emptyHeap) {
        }
    }
}
