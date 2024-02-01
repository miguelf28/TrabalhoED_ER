package org.Estruturas.LinkedList;

import org.Estruturas.Nodes.Node;

/**
 * A generic linked list implementation.
 *
 * @param <T> the type of elements stored in the linked list
 * @author 8190357 - Marcelo Barbosa
 * @author 8180325 - Miguel Fonseca
 */
public class LinkedList<T>{
    private Node<T> first;
    private int size;

    /**
     * Constructs an empty linked list.
     */
    public LinkedList() {
        first = null;
        size = 0;
    }

    /**
     * Returns the number of elements in the linked list.
     *
     * @return the number of elements in the linked list
     */
    public int size() {
        return size;
    }

    /**
     * Checks whether the linked list is empty.
     *
     * @return true if the linked list is empty, false otherwise
     */
    public boolean isEmpty() {
        return (size == 0);
    }

    /**
     * Adds an element to the beginning of the linked list.
     *
     * @param v the value to add to the linked list
     */
    public void addFirst(T v) {
        Node<T> newNode = new Node<>(v, first);
        first = newNode;
        size++;
    }

    /**
     * Adds an element to the end of the linked list.
     *
     * @param v the value to add to the linked list
     */
    public void addLast(T v) {
        Node<T> newNode = new Node<>(v, null);
        if (isEmpty()) {
            first = newNode;
        } else {
            Node<T> cur = first;
            while (cur.getNext() != null) {
                cur = cur.getNext();
            }
            cur.setNext(newNode);
        }
        size++;
    }

    /**
     * Returns the first element in the linked list.
     *
     * @return the first element in the linked list, or null if the list is empty
     */
    public T getFirst() {
        if (isEmpty()) {
            return null;
        }
        return first.getValue();
    }

    /**
     * Returns the last element in the linked list.
     *
     * @return the last element in the linked list, or null if the list is empty
     */
    public T getLast() {
        if (isEmpty()) {
            return null;
        }
        Node<T> cur = first;
        while (cur.getNext() != null) {
            cur = cur.getNext();
        }
        return cur.getValue();
    }

    /**
     * Removes the first element from the linked list.
     */
    public void removeFirst() {
        if (isEmpty()) {
            return;
        }
        first = first.getNext();
        size--;
    }


    /**
     * Removes the last element from the linked list.
     */
    public void removeLast() {
        if (isEmpty()) {
            return;
        }
        if (size == 1) {
            first = null;
        } else {
            Node<T> cur = first;
            for (int i = 0; i < size - 2; i++) {
                cur = cur.getNext();
            }
            cur.setNext(cur.getNext().getNext());
        }
        size--;
    }

    /**
     * Returns a string representation of the linked list.
     *
     * @return a string representation of the linked list
     */
    @Override
    public String toString() {
        String str = "{";
        Node<T> cur = first;
        while (cur != null) {
            str += cur.getValue();
            cur = cur.getNext();
            if (cur != null) {
                str += ",";
            }
        }
        str += "}";
        return str;
    }
}
