package org.Estruturas.DoubleLinkedList;

import org.Estruturas.Exceptions.ElementNotFoundException;
import org.Estruturas.Exceptions.EmptyCollectionException;
import org.Estruturas.Nodes.DoubleNode;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Represents a doubly linked list.
 *
 * @param <T> the generic type of elements in the list
 * @author 8190357 - Marcelo Barbosa
 * @author 8180325 - Miguel Fonseca
 */
public class DoubleLinkedList<T> implements ListADT<T> {
    protected DoubleNode<T> front, rear;
    protected int count;
    protected int modCount;

    /**
     * Constructs an empty doubly linked list.
     */
    public DoubleLinkedList() {
        rear = null;
        front = null;
        count = 0;
    }

    /**
     * Adds the specified element to the end of the list.
     *
     * @param element the element to be added to the list
     */
    public void add(T element) {
        DoubleNode<T> newNode = new DoubleNode<>(element);
        if (isEmpty()) {
            front = newNode;
        } else {
            newNode.setPrev(rear);
            rear.setNext(newNode);
        }
        rear = newNode;
        count++;
    }

    /**
     * Removes and returns the last element from the list.
     *
     * @return the last element from the list
     * @throws EmptyCollectionException if the list is empty
     */
    @Override
    public T removeLast() throws EmptyCollectionException {
        T result;

        if (isEmpty()) {
            throw new EmptyCollectionException("list");
        }
        result = rear.getValue();
        rear = rear.getPrev();

        if (rear == null) {
            front = null;
        } else {
            rear.setNext(null);
        }
        count--;
        modCount++;
        return result;
    }

    /**
     * Removes and returns the first element from the list.
     *
     * @return the first element from the list
     * @throws EmptyCollectionException if the list is empty
     */
    @Override
    public T removeFirst() throws EmptyCollectionException {
        if (isEmpty()) {
            throw new EmptyCollectionException("list");
        }
        T result = front.getValue();
        front = front.getNext();

        if (front == null) {
            rear = null;
        } else {
            front.setPrev(null);
        }
        count--;
        modCount++;
        return result;
    }

    /**
     * Removes the specified element from the list.
     *
     * @param element the element to be removed from the list
     * @return the removed element
     * @throws EmptyCollectionException    if the list is empty
     * @throws ElementNotFoundException   if the specified element is not found in the list
     */
    @Override
    public T remove(T element) throws EmptyCollectionException, ElementNotFoundException {
        T result;
        DoubleNode<T> nodeptr = find(element);

        if (nodeptr == null) {
            throw new ElementNotFoundException("list");
        }
        result = nodeptr.getValue();
        // check to see if front or rear
        if (nodeptr == front) {
            result = this.removeFirst();
        } else if (nodeptr == rear) {
            result = this.removeLast();
        } else {
            nodeptr.getNext().setPrev(nodeptr.getPrev());
            nodeptr.getPrev().setNext(nodeptr.getNext());
            count--;
        }
        modCount++;
        return result;
    }

    /**
     * Returns a reference to the first element in the list.
     *
     * @return the first element in the list
     * @throws EmptyCollectionException if the list is empty
     */
    @Override
    public T first() throws EmptyCollectionException {
        if (isEmpty()) {
            throw new EmptyCollectionException("list");
        }
        return front.getValue();
    }

    /**
     * Returns a reference to the last element in the list.
     *
     * @return the last element in the list
     * @throws EmptyCollectionException if the list is empty
     */
    @Override
    public T last() throws EmptyCollectionException {
        if (isEmpty()) {
            throw new EmptyCollectionException("list");
        }

        return rear.getValue();
    }

    /**
     * Checks if the list contains the specified element.
     *
     * @param target the element to be checked for existence in the list
     * @return true if the list contains the specified element, false otherwise
     */
    @Override
    public boolean contains(T target) {
        return (find(target) != null);
    }

    /**
     * Finds and returns the node containing the specified target element in the list.
     *
     * @param target the target element to be found in the list
     * @return the node containing the specified target element, or {@code null} if not found
     */
    private DoubleNode<T> find(T target) {
        boolean found = false;
        DoubleNode<T> traverse = front;
        DoubleNode<T> result = null;

        if (!isEmpty()) {
            while (!found && traverse != null) {
                if (target.equals(traverse.getValue())) {
                    found = true;
                } else {
                    traverse = traverse.getNext();
                }
            }
        }
        if (found) {
            result = traverse;
        }
        return result;
    }

    /**
     * Checks if the list is empty.
     *
     * @return true if the list is empty, false otherwise
     */
    @Override
    public boolean isEmpty() {
        return (count == 0);
    }

    /**
     * Returns the number of elements in the list.
     *
     * @return the number of elements in the list
     */
    @Override
    public int size() {
        return count;
    }

    /**
     * Returns an iterator for the elements in the list.
     *
     * @return an iterator for the elements in the list
     */
    @Override
    public Iterator<T> iterator() {
        return new Iter(front, count);
    }

    /**
     * Returns a string representation of the list.
     *
     * @return a string representation of the list
     */
    @Override
    public String toString() {
        String result = "";
        DoubleNode<T> traverse = front;

        while (traverse != null) {
            result = result + (traverse.getValue()).toString() + "\n";
            traverse = traverse.getNext();
        }
        return result;
    }

    /**
     * Represents an iterator for the doubly linked list.
     */
    private class Iter implements Iterator<T> {

        private DoubleNode<T> cursor;  // the current position
        private int expectedModCount;
        private boolean canRemove;

        /**
         * Sets up this iterator using the specified items.
         *
         * @param list the front node of the list
         * @param size the size of the list
         */
        public Iter(DoubleNode<T> list, int size) {
            cursor = list;
            this.expectedModCount = modCount;
            this.canRemove = false;
        }

        /**
         * Returns true if there is another element in the list after the current position.
         *
         * @return true if there is another element, false otherwise
         */
        @Override
        public boolean hasNext() {
            return (cursor != null);
        }

        /**
         * Returns the next element in the list and advances the cursor position.
         *
         * @return the next element in the list
         * @throws ConcurrentModificationException if the list was modified during iteration
         * @throws NoSuchElementException           if there are no more elements in the iteration
         */
        @Override
        public T next() {
            if (modCount != expectedModCount) {
                throw new ConcurrentModificationException();
            }
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            T result = cursor.getValue();
            cursor = cursor.getNext();
            this.canRemove = true;
            return result;
        }

        /**
         * Removes the last element returned by the iterator from the underlying collection.
         *
         * @throws ConcurrentModificationException if the list was modified during iteration
         * @throws IllegalStateException           if the {@code next} method has not yet been called,
         *                                         or the {@code remove} method has already been called
         *                                         after the last call to the {@code next} method
         */
        @Override
        public void remove() {
            if (modCount != expectedModCount) {
                throw new ConcurrentModificationException();
            }
            if (!this.canRemove) {
                throw new IllegalStateException();
            }
            canRemove = false;
            try {
                DoubleLinkedList.this.remove((T) cursor.getValue());
            } catch (EmptyCollectionException | ElementNotFoundException ex) {
                throw new IllegalStateException();
            }
            cursor = cursor.getNext();
            expectedModCount++;
        }
    }
}