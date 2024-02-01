package org.Estruturas.LinkedStack;

import org.Estruturas.Exceptions.EmptyCollectionException;

/**
 * Represents the interface for a stack data structure.
 *
 * @param <T> the type of elements stored in the stack
 * @author 8190357 - Marcelo Barbosa
 * @author 8180325 - Miguel Fonseca
 */
public interface StackADT<T> {
    /**
     * Adds the specified element to the top of the stack.
     *
     * @param elem the element to be pushed onto the stack
     */
    void push(T elem);

    /**
     * Removes and returns the element at the top of the stack.
     *
     * @return the element at the top of the stack
     * @throws EmptyCollectionException if the stack is empty
     */
    T pop() throws EmptyCollectionException;


    /**
     * Returns the element at the top of the stack without removing it.
     *
     * @return the element at the top of the stack
     * @throws EmptyCollectionException if the stack is empty
     */
    T peek() throws EmptyCollectionException;

    /**
     * Checks whether the stack is empty.
     *
     * @return true if the stack is empty, false otherwise
     */
    boolean isEmpty();

    /**
     * Returns the number of elements in the stack.
     *
     * @return the number of elements in the stack
     */
    int size();
}
