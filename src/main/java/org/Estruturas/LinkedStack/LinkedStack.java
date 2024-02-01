package org.Estruturas.LinkedStack;

import org.Estruturas.Exceptions.EmptyCollectionException;
import org.Estruturas.Nodes.Node;

/**
 * Represents a linked stack data structure.
 *
 * @param <T> the type of elements stored in the stack
 * @author 8190357 - Marcelo Barbosa
 * @author 8180325 - Miguel Fonseca
 */
public class LinkedStack<T> implements StackADT<T> {

    private int count;
    private Node<T> top;

    /**
     * Constructs an empty linked stack.
     */
    public LinkedStack() {
        count = 0;
        top = null;
    }

    /**
     * Adds the specified element to the top of the stack.
     *
     * @param element the element to be pushed onto the stack
     */
    @Override
    public void push(T element) {
        Node<T> temp = new Node<>(element);

        temp.setNext(top);
        top = temp;
        count++;
    }

    /**
     * Removes and returns the element at the top of the stack.
     *
     * @return the element at the top of the stack
     * @throws EmptyCollectionException if the stack is empty
     */
    @Override
    public T pop() throws EmptyCollectionException {
        if (isEmpty()) {
            throw new EmptyCollectionException("Stack");
        }
        T result = top.getValue();
        top = top.getNext();
        count--;

        return result;
    }

    /**
     * Returns the element at the top of the stack without removing it.
     *
     * @return the element at the top of the stack
     * @throws EmptyCollectionException if the stack is empty
     */
    @Override
    public T peek() throws EmptyCollectionException {
        if (isEmpty()) {
            throw new EmptyCollectionException("Stack");
        }
        return top.getValue();
    }

    /**
     * Checks whether the stack is empty.
     *
     * @return true if the stack is empty, false otherwise
     */
    @Override
    public boolean isEmpty() {
        return (count == 0);
    }

    /**
     * Returns the number of elements in the stack.
     *
     * @return the number of elements in the stack
     */
    @Override
    public int size() {
        return count;
    }

    /**
     * Returns a string representation of the linked stack.
     *
     * @return a string representation of the linked stack
     */
    public String toString() {
        StringBuilder result = new StringBuilder();
        Node<T> current = top;

        result.append("LinkedStack: [");
        while (current != null) {
            result.append(current.getValue());
            if (current.getNext() != null) {
                result.append(", ");
            }
            current = current.getNext();
        }
        result.append("]");

        return result.toString();
    }
}