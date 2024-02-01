package org.Estruturas.ArrayOrderedList;

/**
 * The {@code OrderedListADT} interface defines the operations to be implemented
 * by ordered list data structures.
 *
 * @param <T> The type of elements stored in the ordered list.
 * @author 8190357 - Marcelo Barbosa
 * @author 8180325 - Miguel Fonseca
 */
public interface OrderedListADT<T> {

    /**
     * Adds the specified element to the ordered list while maintaining the order.
     *
     * @param element The element to be added to the ordered list.
     */
    public void add (T element);
}