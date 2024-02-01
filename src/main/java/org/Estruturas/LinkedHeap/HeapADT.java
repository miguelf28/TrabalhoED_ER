package org.Estruturas.LinkedHeap;

import org.Estruturas.Exceptions.EmptyCollectionException;
import org.Estruturas.LinkedBinaryTree.BinaryTreeADT;

/**
 * Interface for a heap data structure.
 *
 * @param <T> the type of elements stored in the heap
 * @author 8190357 - Marcelo Barbosa
 * @author 8180325 - Miguel Fonseca
 */
public interface HeapADT<T> extends BinaryTreeADT<T> {
    /**
     * Adds the specified element to the heap.
     *
     * @param obj the element to be added to the heap
     */
    public void addElement (T obj);

    /**
     * Removes and returns the smallest element from the heap.
     *
     * @return the smallest element in the heap
     * @throws EmptyCollectionException if the heap is empty
     */
    public T removeMin() throws EmptyCollectionException;

    /**
     * Returns the smallest element in the heap without removing it.
     *
     * @return the smallest element in the heap
     * @throws EmptyCollectionException if the heap is empty
     */
    public T findMin() throws EmptyCollectionException;
}

