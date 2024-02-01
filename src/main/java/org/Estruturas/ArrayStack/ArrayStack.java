package org.Estruturas.ArrayStack;

import org.Estruturas.Exceptions.EmptyCollectionException;
import org.Estruturas.LinkedStack.StackADT;

/**
 * The {@code ArrayStack} class represents a stack implemented using an array.
 *
 * @param <T> The type of elements stored in the stack.
 * @author 8190357 - Marcelo Barbosa
 * @author 8180325 - Miguel Fonseca
 */
public class ArrayStack<T> implements StackADT<T> {

    private final int DEFAULT_CAPACITY = 100;

    private int top;

    private T[] stack;

    /**
     * Constructs an empty stack with the default capacity.
     */
    public ArrayStack() {
        top = 0;
        stack = (T[]) (new Object[DEFAULT_CAPACITY]);
    }

    /**
     * Constructs an empty stack with the specified initial capacity.
     *
     * @param initialCapacity The initial capacity of the stack.
     */
    public ArrayStack(int initialCapacity) {
        top = 0;
        stack = (T[]) (new Object[initialCapacity]);
    }


    /**
     * Expands the capacity of the stack by creating a new array with
     * twice the capacity of the current one and copying the elements.
     */
    private void expandCapacity() {
        T[] larger = (T[]) (new Object[stack.length * 2]);
        for (int i = 0; i < stack.length; i++) {
            larger[i] = stack[i];
        }
        stack = larger;
    }

    /**
     * Adds an element to the top of the stack.
     *
     * @param element The element to be pushed onto the stack.
     */
    @Override
    public void push(T element) {
        if (size() == stack.length) {
            expandCapacity();
        }
        stack[top] = element;
        top++;
    }

    /**
     * Removes and returns the element from the top of the stack.
     *
     * @return The element removed from the top of the stack.
     * @throws EmptyCollectionException If the stack is empty.
     */
    @Override
    public T pop() throws EmptyCollectionException {
        if (isEmpty()) {
            throw new EmptyCollectionException("Empty Stack");
        }
        top--;
        T result = stack[top];
        stack[top] = null;
        return result;
    }

    /**
     * Returns the element at the top of the stack without removing it.
     *
     * @return The element at the top of the stack.
     * @throws EmptyCollectionException If the stack is empty.
     */
    @Override
    public T peek() throws EmptyCollectionException {
        if (isEmpty()) {
            throw new EmptyCollectionException("Empty Stack");
        }
        return stack[top - 1];
    }

    /**
     * Checks if the stack is empty.
     *
     * @return {@code true} if the stack is empty, {@code false} otherwise.
     */
    @Override
    public boolean isEmpty() {
        return (top == 0);
    }

    /**
     * Returns the number of elements in the stack.
     *
     * @return The number of elements in the stack.
     */
    @Override
    public int size() {
        return top;
    }
}