package org.Estruturas.DoubleLinkedList;

import org.Estruturas.Exceptions.ElementNotFoundException;
import org.Estruturas.Exceptions.EmptyCollectionException;

import java.util.Iterator;

/**
 * This interface represents the specification of a doubly linked list.
 *
 * @param <T> the generic type of elements in the list
 * @author 8190357 - Marcelo Barbosa
 * @author 8180325 - Miguel Fonseca
 */
public interface ListADT<T> extends Iterable<T>{
    /**
     * Removes and returns the first element in the list.
     *
     * @return the first element in the list
     * @throws EmptyCollectionException if the list is empty
     */
    public T removeFirst() throws EmptyCollectionException;

    /**
     * Removes and returns the last element in the list.
     *
     * @return the last element in the list
     * @throws EmptyCollectionException if the list is empty
     */
    public T removeLast() throws EmptyCollectionException;

    /**
     * Removes and returns the specified element from the list.
     *
     * @param element the element to be removed from the list
     * @return the removed element
     * @throws ElementNotFoundException if the specified element is not found in the list
     * @throws EmptyCollectionException  if the list is empty
     */
    public T remove(T element) throws ElementNotFoundException, EmptyCollectionException;

    /**
     * Returns a reference to the first element in the list.
     *
     * @return the first element in the list
     * @throws EmptyCollectionException if the list is empty
     */
    public T first() throws EmptyCollectionException;

    /**
     * Returns a reference to the last element in the list.
     *
     * @return the last element in the list
     * @throws EmptyCollectionException if the list is empty
     */
    public T last() throws EmptyCollectionException;

    /**
     * Checks if the list contains the specified target element.
     *
     * @param target the target element to be checked in the list
     * @return true if the list contains the target element, false otherwise
     * @throws EmptyCollectionException if the list is empty
     */
    public boolean contains(T target) throws EmptyCollectionException;

    /**
     * Checks if the list is empty.
     *
     * @return true if the list is empty, false otherwise
     */
    public boolean isEmpty();

    /**
     * Returns the number of elements in the list.
     *
     * @return the number of elements in the list
     */
    public int size();

    /**
     * Returns an iterator for the elements in the list.
     *
     * @return an iterator for the elements in the list
     */
    public Iterator<T> iterator();

    /**
     * Returns a string representation of the list.
     *
     * @return a string representation of the list
     */
    @Override
    public String toString();
}
