package org.Estruturas.LinkedQueue;

import org.Estruturas.Exceptions.EmptyCollectionException;

/**
 * An interface representing a generic queue.
 *
 * @param <T> the type of elements stored in the queue
 * @author 8190357 - Marcelo Barbosa
 * @author 8180325 - Miguel Fonseca
 */
public interface QueueADT<T> {
    /**
     * Adds the specified element to the rear of this queue.
     *
     * @param element the element to be added to the rear of the queue
     */
    public void enqueue(T element);

    /**
     * Removes and returns the element at the front of this queue.
     *
     * @return the element at the front of the queue
     * @throws EmptyCollectionException if the queue is empty
     */
    public T dequeue() throws EmptyCollectionException;

    /**
     * Returns the element at the front of this queue without removing it.
     *
     * @return the element at the front of the queue
     * @throws EmptyCollectionException if the queue is empty
     */
    public T first() throws EmptyCollectionException;

    /**
     * Checks whether the queue is empty.
     *
     * @return true if the queue is empty, false otherwise
     */
    public boolean isEmpty();

    /**
     * Returns the number of elements in the queue.
     *
     * @return the number of elements in the queue
     */
    public int size();
}

