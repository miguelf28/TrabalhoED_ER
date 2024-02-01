package org.Estruturas.Exceptions;

/**
 * This exception is thrown when an element is not found in a collection.
 *
 * @author 8190357 - Marcelo Barbosa
 * @author 8180325 - Miguel Fonseca
 */
public class ElementNotFoundException  extends Throwable{

    /**
     * Constructs an ElementNotFoundException with no specified detail message.
     */
    public ElementNotFoundException() {
    }

    /**
     * Constructs an ElementNotFoundException with the specified detail message.
     *
     * @param msg the detail message
     */
    public ElementNotFoundException(String msg) {
        super(msg);
    }
}
