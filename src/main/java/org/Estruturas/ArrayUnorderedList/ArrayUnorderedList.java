package org.Estruturas.ArrayUnorderedList;

import org.Estruturas.ArrayList.ArrayList;
import org.Estruturas.Exceptions.ElementNotFoundException;

/**
 * Represents an unordered list implemented using an array.
 * @param <T> the type of elements held in this list
 * @author 8190357 - Marcelo Barbosa
 * @author 8180325 - Miguel Fonseca
 */
public class ArrayUnorderedList <T> extends ArrayList<T> implements UnorderedListADT<T> {

    /**
     * Creates an empty list using the default capacity.
     */
    public ArrayUnorderedList() {
        super();
    }

    /**
     * Creates an empty list using the specified capacity.
     * @param initialCapacity the initial capacity of the list
     */
    public ArrayUnorderedList(int initialCapacity) {
        super(initialCapacity);
    }

    /**
     * Adds the specified element to the front of the list.
     * @param element the element to be added to the front of the list
     */
    @Override
    public void addToFront(T element) {
        if (size() == list.length) {
            expandCapacity();
        }
        // shift elements to make room
        for (int scan = count; scan > 0; scan--) {
            list[scan] = list[scan - 1];
        }
        list[0] = element;
        count++;
    }


    /**
     * Adds the specified element to the rear of the list.
     * @param element the element to be added to the rear of the list
     */
    @Override
    public void addToRear(T element) {
        if (size() == list.length) {
            expandCapacity();
        }
        list[count] = element;
        count++;
    }

    /**
     * Adds the specified element after the specified target element.
     * Throws a ElementNotFoundException if the target is not found.
     * @param element the element to be added
     * @param target the target element after which to add the new element
     * @throws ElementNotFoundException if the target element is not found
     */
    @Override
    public void addAfter(T element, T target) throws ElementNotFoundException {
        if (size() == list.length) {
            expandCapacity();
        }
        int scan = 0;
        while (scan < count && !target.equals(list[scan])) {
            scan++;
        }
        if (scan == count) {
            throw new ElementNotFoundException("list");
        }
        scan++;
        for (int scan2 = count; scan2 > scan; scan2--) {
            list[scan2] = list[scan2 - 1];
        }
        list[scan] = element;
        count++;
    }
}