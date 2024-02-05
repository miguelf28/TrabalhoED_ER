package org.Estruturas.ArrayList;

import org.Estruturas.Exceptions.EmptyCollectionException;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Represents a dynamic array-based implementation of a list.
 *
 * @param <T> The type of elements stored in the list.
 * @author 8190357 - Marcelo Barbosa
 * @author 8180325 - Miguel Fonseca
 */
public class ArrayList<T> implements ListADT<T> {

    protected T[] list;
    protected int count;
    protected int modCount;
    private final int CAPACITY = 100;

    /**
     * Constructs an empty list with a default capacity.
     */
    public ArrayList() {
        count = 0;
        modCount = 0;
        list = (T[]) (new Object[CAPACITY]);
    }

    /**
     * Constructs an empty list with the specified initial capacity.
     *
     * @param initialCapacity The initial capacity of the list.
     */
    public ArrayList(int initialCapacity) {
        count = 0;
        list = (T[]) (new Object[initialCapacity]);
    }

    public void clear() {
        for (int i = 0; i < count; i++) {
            list[i] = null;
        }
        count = 0;
        modCount++;
    }

    /**
     * Adds the specified element to the end of the list.
     *
     * @param element The element to be added to the list.
     */
    public void add(T element) {
        if (count == list.length) {
            expandCapacity();
        }
        list[count] = element;
        count++;
        modCount++;
    }

    /**
     * Removes and returns the first element in the list.
     *
     * @return The first element in the list.
     * @throws EmptyCollectionException if the list is empty.
     */
    @Override
    public T removeFirst() throws EmptyCollectionException {
        if (isEmpty()) {
            throw new EmptyCollectionException("List");
        }
        T toRemove = list[0];
        this.count--;
        //Shift the elements
        for (int i = 0; i < count; i++) {
            list[i] = list[i + 1];
        }
        list[count] = null;
        this.modCount++;
        return toRemove;
    }

    /**
     * Removes and returns the last element in the list.
     *
     * @return The last element in the list.
     * @throws EmptyCollectionException if the list is empty.
     */
    @Override
    public T removeLast() throws EmptyCollectionException {
        if (isEmpty()) {
            throw new EmptyCollectionException("List");
        }
        count--;
        T toRemove = list[count];
        list[count] = null;
        this.modCount++;
        return toRemove;
    }

    /**
     * Removes and returns the specified element from the list.
     *
     * @param element The element to be removed from the list.
     * @return The removed element.
     * @throws EmptyCollectionException if the list is empty.
     */
    @Override
    public T remove(T element) throws EmptyCollectionException {
        if (isEmpty()) {
            throw new EmptyCollectionException("List");
        }
        T toRemove = null;
        int index = findElement(element);
        if (index > -1) {
            toRemove = list[index];
            count--;
            //Shift the appropriate elements
            for (int i = index; i < count; i++) {
                list[i] = list[i + 1];
            }
            list[count] = null;
            this.modCount++;
        }
        return toRemove;
    }

    /**
     * Increases the capacity of the list by creating a new array with
     * twice the capacity of the current one and copying the elements.
     * This method is called when the current list is full.
     */
    protected void expandCapacity() {
        T[] newList = (T[]) (new Object[list.length * 2]);
        System.arraycopy(list, 0, newList, 0, list.length);
        list = newList;
    }

    /**
     * Finds the index of the first occurrence of the specified element
     * in the list. If the element is not found, returns -1.
     *
     * @param element The element to find in the list.
     * @return The index of the element, or -1 if not found.
     */
    private int findElement(T element) {
        int num = -1;
        for (int i = 0; i < this.count; i++) {
            if (this.list[i].equals(element)) {
                num = i;
                break;
            }
        }
        return num;
    }

    /**
     * Returns a reference to the element at the front of the list without removing it.
     *
     * @return The first element in the list.
     * @throws EmptyCollectionException if the list is empty.
     */
    @Override
    public T first() throws EmptyCollectionException {
        if (isEmpty()) {
            throw new EmptyCollectionException("Empty List");
        }
        return this.list[0];
    }

    /**
     * Returns a reference to the element at the rear of the list without removing it.
     *
     * @return The last element in the list.
     * @throws EmptyCollectionException if the list is empty.
     */
    @Override
    public T last() throws EmptyCollectionException {
        if (isEmpty()) {
            throw new EmptyCollectionException("Empty List");
        }
        return this.list[this.count - 1];

    }

    /**
     * Returns true if the list contains the specified element.
     *
     * @param target The element to check for existence in the list.
     * @return true if the element is in the list, false otherwise.
     * @throws EmptyCollectionException if the list is empty.
     */
    @Override
    public boolean contains(T target) throws EmptyCollectionException {
        if (isEmpty()) {
            throw new EmptyCollectionException("Empty List");
        }
        return (findElement(target) > -1);
    }

    /**
     * Returns true if the list is empty and false otherwise.
     *
     * @return true if the list is empty, false otherwise.
     */
    @Override
    public boolean isEmpty() {
        return (this.count == 0);
    }

    /**
     * Returns the number of elements currently in the list.
     *
     * @return The number of elements in the list.
     */
    @Override
    public int size() {
        return this.count;
    }

    /**
     * Returns an iterator for the elements currently in the list.
     *
     * @return An iterator for the elements in the list.
     */
    @Override
    public Iterator<T> iterator() {
        return new Iter();
    }

    /**
     * Returns a string representation of this list.
     *
     * @return A string representation of the list.
     */
    @Override
    public String toString() {
        String str = "";
        for (int i = 0; i < count; i++) {
            str += list[i].toString() + "\n";
        }
        return str;
    }

    /**
     * Inner class representing an iterator for the elements in the list.
     *
     * @param <T> The type of elements iterated by the iterator.
     */
    private class Iter<T> implements Iterator<T> {

        private int cursor;
        private int expectedModCount;
        private boolean canRemove;

        /**
         * Constructs an iterator with an initial state.
         */
        public Iter() {
            this.cursor = 0;
            this.expectedModCount = modCount;
            this.canRemove = false;
        }

        /**
         * Returns true if this iterator has at least one more element
         * to deliver in the iteration.
         *
         * @return true if there is another element, false otherwise.
         */
        @Override
        public boolean hasNext() {
            return (this.cursor != size());
        }

        /**
         * Returns the next element in the iteration. If there are no
         * more elements in this iteration, a NoSuchElementException is thrown.
         *
         * @return The next element in the iteration.
         * @throws NoSuchElementException if there are no more elements.
         */
        @Override
        public T next() {
            if (modCount != expectedModCount) {
                throw new ConcurrentModificationException();
            }
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            this.cursor++;
            this.canRemove = true;
            return (T) list[this.cursor - 1];
        }

        /**
         * Removes the last element returned by this iterator from the list.
         * Can only be called once per call to next().
         *
         * @throws ConcurrentModificationException if the list was modified
         *                                         after the iterator was created.
         * @throws NoSuchElementException          if next() has not been called,
         *                                         or remove() was already called after the last call to next().
         * @throws IllegalStateException           if remove() is called before next().
         */
        @Override
        public void remove() {
            if (modCount != expectedModCount) {
                throw new ConcurrentModificationException();
            }
            if (!canRemove) {
                throw new IllegalStateException();
            }
            canRemove = false;
            --cursor;
            try {
                ArrayList.this.remove(list[cursor]);
            } catch (EmptyCollectionException ex) {

            }
            expectedModCount++;
        }
    }

    /**
     * Returns the element at the specified index in the list.
     *
     * @param index The index of the element to retrieve.
     * @return The element at the specified index.
     * @throws EmptyCollectionException if the list is empty.
     */
    public T get(int index) throws EmptyCollectionException {
        if (index < 0 || index >= count) {
            throw new EmptyCollectionException("Index fora dos limites!");
        }
        return list[index];
    }
}