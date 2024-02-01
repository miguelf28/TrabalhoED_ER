package org.Estruturas.CircularArrayQueue;

import org.Estruturas.Exceptions.EmptyCollectionException;
import org.Estruturas.LinkedQueue.QueueADT;

/**
 * CircularArrayQueue represents an array implementation of a circular queue.
 * @param <T> the type of elements held in this queue
 * @author 8190357 - Marcelo Barbosa
 * @author 8180325 - Miguel Fonseca
 */
public class CircularArrayQueue<T> implements QueueADT {

    private final int DEFAULT_CAPACITY = 100;

    private int count;

    private int front;

    private int rear;

    private T[] queue;

    /**
     * Creates an empty queue with the default capacity.
     */
    public CircularArrayQueue() {
        this.front = 0;
        this.rear = 0;
        this.count = 0;
        this.queue = (T[]) (new Object[DEFAULT_CAPACITY]);
    }

    /**
     * Creates an empty queue with the specified capacity.
     * @param capacity the initial capacity of the queue
     */
    public CircularArrayQueue(int capacity) {
        this.front = 0;
        this.rear = 0;
        this.count = 0;
        this.queue = (T[]) (new Object[capacity]);
    }

    /**
     * Adds the specified element to the rear of the queue.
     * @param element the element to be added to the rear of the queue
     */
    @Override
    public void enqueue(Object element) {
        if (this.isFull()) {
            this.resize();
        }
        this.queue[this.rear] = (T) element;
        this.rear = (this.rear + 1) % this.queue.length;
        this.count++;
    }

    /**
     * Removes and returns the element at the front of the queue.
     * @return the element at the front of the queue
     * @throws EmptyCollectionException if the queue is empty
     */
    @Override
    public T dequeue() throws EmptyCollectionException {
        if (this.isEmpty()) {
            throw new EmptyCollectionException("Empty queue");
        }
        T element = this.queue[this.front];
        this.front = (this.front + 1) % this.queue.length;
        this.count--;
        return element;
    }

    /**
     * Returns the element at the front of the queue without removing it.
     * @return the element at the front of the queue
     * @throws EmptyCollectionException if the queue is empty
     */
    @Override
    public T first() throws EmptyCollectionException {
        if (this.isEmpty()) {
            throw new EmptyCollectionException("Empty queue");
        }
        return this.queue[this.front];
    }

    /**
     * Checks if the queue is empty.
     * @return true if the queue is empty, false otherwise
     */
    @Override
    public boolean isEmpty() {
        return this.count == 0;
    }

    /**
     * Returns the number of elements in the queue.
     * @return the number of elements in the queue
     */
    @Override
    public int size() {
        return this.count;
    }

    /**
     * Returns a string representation of the queue.
     * @return a string representation of the queue
     */
    @Override
    public String toString() {
        String output = "";
        //The string representation of this queue should only reveal the item
        //count as well as the rear and front of the queue due to not having
        //an iterator implemented
        output += "Count = " + this.count;
        if (!this.isEmpty()) {
            try {
                output += "\nFront Item: " + this.first().toString()
                        + "\nRear Item: " + this.queue[this.rear - 1].toString();
            } catch (EmptyCollectionException ex) {
                System.out.println(ex);
            }
        } else {
            output += "\nNo front or rear items.";
        }
        return output;
    }

    /**
     * Checks if the queue is full.
     * @return true if the queue is full, false otherwise
     */
    private boolean isFull() {
        return this.count == this.queue.length;
    }

    /**
     * Resizes the queue by creating a new array with twice the capacity
     * and copying elements from the old array to the new one.
     */
    private void resize() {
        int currentQueueLength = this.queue.length;
        T[] newQueue = (T[]) (new Object[currentQueueLength * 2]);
        int index = this.front;

        for (int i = 0; i < currentQueueLength; i++) {
            newQueue[i] = this.queue[index];
            index = (index + 1) % currentQueueLength;
        }
        this.front = 0;
        this.rear = currentQueueLength++;
        this.queue = newQueue;

    }
}
