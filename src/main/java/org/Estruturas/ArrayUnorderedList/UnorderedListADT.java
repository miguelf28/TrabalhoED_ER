package org.Estruturas.ArrayUnorderedList;

import org.Estruturas.Exceptions.ElementNotFoundException;

/**
 * Represents the interface for an unordered list.
 * @param <T> the type of elements held in this list
 * @author 8190357 - Marcelo Barbosa
 * @author 8180325 - Miguel Fonseca
 */
public interface UnorderedListADT<T> {
    /**
     * Adds the specified element to the front of the list.
     * @param element the element to be added to the front of the list
     */
    public void addToFront (T element);

    /**
     * Adds the specified element to the rear of the list.
     * @param element the element to be added to the rear of the list
     */
    public void addToRear (T element);

    /**
     * Adds the specified element after the specified target element.
     * Throws a ElementNotFoundException if the target is not found.
     * @param element the element to be added
     * @param target the target element after which to add the new element
     * @throws ElementNotFoundException if the target element is not found
     */
    public void addAfter (T element, T target) throws ElementNotFoundException;
}

