package org.Estruturas.ArrayPriorityQueue;

import org.Estruturas.ArrayHeap.ArrayHeap;
import org.Estruturas.Exceptions.EmptyCollectionException;
import org.Estruturas.Nodes.PriorityQueueNode;

/**
 * The {@code ArrayPriorityQueue} class represents a priority queue implemented
 * using an array-based heap.
 *
 * @param <T> The type of elements stored in the priority queue.
 * @author 8190357 - Marcelo Barbosa
 * @author 8180325 - Miguel Fonseca
 */
public class ArrayPriorityQueue<T> extends ArrayHeap<PriorityQueueNode<T>> {

    /**
     * Constructs an empty priority queue.
     */
    public ArrayPriorityQueue() {
        super();
    }

    /**
     * Adds an element with the specified priority to the priority queue.
     *
     * @param object   The element to be added to the priority queue.
     * @param priority The priority of the element.
     */
    public void addElement(T object, int priority) {
        PriorityQueueNode<T> node = new PriorityQueueNode<>(object, priority);
        super.addElement(node);
    }

    /**
     * Removes and returns the element with the highest priority from the priority queue.
     *
     * @return The element with the highest priority.
     * @throws EmptyCollectionException If the priority queue is empty.
     */
    public T removeNext() throws EmptyCollectionException {
        PriorityQueueNode<T> temp = (PriorityQueueNode<T>) super.removeMin();
        return temp.getElement();
    }

}