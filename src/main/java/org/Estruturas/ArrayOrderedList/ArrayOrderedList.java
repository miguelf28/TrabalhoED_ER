package org.Estruturas.ArrayOrderedList;

import org.Estruturas.ArrayList.ArrayList;

/**
 * The {@code ArrayOrderedList} class represents an ordered list implemented
 * using an array. It extends the {@link ArrayList} class and implements the
 * {@link OrderedListADT} interface.
 *
 * @param <T> The type of elements stored in the ordered list.
 * @author 8190357 - Marcelo Barbosa
 * @author 8180325 - Miguel Fonseca
 */
public class ArrayOrderedList<T> extends ArrayList<T> implements OrderedListADT<T> {

    /**
     * Constructs an empty ordered list.
     */
    public ArrayOrderedList() {
        super(); //Invoke immediate parent class constructor.
    }

    /**
     * Adds the specified element to the ordered list while maintaining the order.
     *
     * @param element The element to be added to the ordered list.
     */
    @Override
    public void add(T element) {
        if (size() == list.length) {
            expandCapacity();
        }
        Comparable<T> temp = (Comparable<T>) element;
        int scan = 0;
        while (scan < super.count && temp.compareTo(list[scan]) > 0) {
            scan++;
        }
        for (int scan2 = super.count; scan2 > scan; scan2--) {
            list[scan2] = list[scan2 - 1];
        }
        list[scan] = element;
        super.modCount++;
        super.count++;
    }
}

