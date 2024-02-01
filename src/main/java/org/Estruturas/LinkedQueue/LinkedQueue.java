package org.Estruturas.LinkedQueue;

import org.Estruturas.Exceptions.EmptyCollectionException;
import org.Estruturas.Nodes.Node;

/**
 * A generic linked queue implementation.
 *
 * @param <T> the type of elements stored in the queue
 * @author 8190357 - Marcelo Barbosa
 * @author 8180325 - Miguel Fonseca
 */
public class LinkedQueue<T> implements QueueADT<T> {
    private Node<T> front, rear;
    private int count;

    /**
     * Constructs an empty linked queue.
     */
    public LinkedQueue() {
        front = rear = null;
        count = 0;
    }

    /**
     * Adds the specified element to the rear of this queue.
     *
     * @param element the element to be added to the rear of the queue
     */
    @Override
    public void enqueue(T element) {
        Node<T> node = new Node<>(element);
        if (isEmpty()) {
            front = node;
        } else {
            rear.setNext(node);
        }
        rear = node;
        count++;
    }

    /**
     * Removes and returns the element at the front of this queue.
     *
     * @return the element at the front of the queue
     * @throws EmptyCollectionException if the queue is empty
     */
    @Override
    public T dequeue() throws EmptyCollectionException {
        if (isEmpty()) {
            throw new EmptyCollectionException("queue");
        }
        T result = front.getValue();
        front = front.getNext();
        count--;
        if (isEmpty()) {
            rear = null;
        }
        return result;
    }

    /**
     * Returns the element at the front of this queue without removing it.
     *
     * @return the element at the front of the queue
     * @throws EmptyCollectionException if the queue is empty
     */
    @Override
    public T first() throws EmptyCollectionException {
        if (isEmpty()) {
            throw new EmptyCollectionException("queue");
        }
        return front.getValue();
    }

    /**
     * Checks whether the queue is empty.
     *
     * @return true if the queue is empty, false otherwise
     */
    @Override
    public boolean isEmpty() {
        return (count == 0);
    }

    /**
     * Returns the number of elements in the queue.
     *
     * @return the number of elements in the queue
     */
    @Override
    public int size() {
        return count;
    }

    /**
     * Returns a string representation of the queue.
     *
     * @return a string representation of the queue
     */
    @Override
    public String toString() {
        String result = "";
        Node<T> current = front;

        while (current != null) {
            result = result + (current.getValue()).toString() + "\n";
            current = current.getNext();
        }
        return result;
    }
}