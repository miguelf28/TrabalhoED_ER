package org.Estruturas.ArrayList;

import org.Estruturas.Exceptions.ElementNotFoundException;
import org.Estruturas.Exceptions.EmptyCollectionException;

import java.util.Iterator;
/**
 * The {@code ListADT} interface represents a generic list data structure.
 * It extends the {@link Iterable} interface, allowing the list to be
 * iterated using enhanced for loops.
 *
 * @param <T> The type of elements stored in the list.
 * @author 8190357 - Marcelo Barbosa
 * @author 8180325 - Miguel Fonseca
 */
public interface ListADT<T> extends Iterable<T>{

    /**
     * Removes and returns the first element in the list.
     *
     * @return The first element in the list.
     * @throws EmptyCollectionException if the list is empty.
     */
    public T removeFirst() throws EmptyCollectionException;

    /**
     * Removes and returns the last element in the list.
     *
     * @return The last element in the list.
     * @throws EmptyCollectionException if the list is empty.
     */
    public T removeLast() throws EmptyCollectionException;

    /**
     * Removes and returns the specified element from the list.
     *
     * @param element The element to be removed.
     * @return The removed element.
     * @throws ElementNotFoundException if the specified element is not found.
     * @throws EmptyCollectionException  if the list is empty.
     */
    public T remove(T element) throws ElementNotFoundException, EmptyCollectionException;

    /**
     * Returns a reference to the first element in the list without removing it.
     *
     * @return The first element in the list.
     * @throws EmptyCollectionException if the list is empty.
     */
    public T first() throws EmptyCollectionException;

    /**
     * Returns a reference to the last element in the list without removing it.
     *
     * @return The last element in the list.
     * @throws EmptyCollectionException if the list is empty.
     */
    public T last() throws EmptyCollectionException;

    /**
     * Checks if the list contains the specified target element.
     *
     * @param target The element to check for.
     * @return {@code true} if the list contains the element, {@code false} otherwise.
     * @throws EmptyCollectionException if the list is empty.
     */
    public boolean contains(T target) throws EmptyCollectionException;

    /**
     * Checks if the list is empty.
     *
     * @return {@code true} if the list is empty, {@code false} otherwise.
     */
    public boolean isEmpty();

    /**
     * Returns the number of elements in the list.
     *
     * @return The number of elements in the list.
     */
    public int size();

    /**
     * Returns an iterator for the elements in the list.
     *
     * @return An iterator for the elements in the list.
     */
    public Iterator<T> iterator();

    /**
     * Returns a string representation of the list.
     *
     * @return A string representation of the list.
     */
    @Override
    public String toString();
}