package org.Estruturas.Nodes;

/**
 * DoubleNode represents a node in a doubly linked list.
 *
 * @param <T> the type of element stored in the node
 * @author 8190357 - Marcelo Barbosa
 * @author 8180325 - Miguel Fonseca
 */
public class DoubleNode<T> {
    /**
     * The value stored in the node.
     */
    private T value;

    /**
     * Reference to the previous node in the list.
     */
    private DoubleNode<T> prev;

    /**
     * Reference to the next node in the list.
     */
    private DoubleNode<T> next;

    /**
     * Creates a new doubly linked list node with the specified value, previous, and next nodes.
     *
     * @param value the value to be stored in the node
     * @param prev  the previous node in the list
     * @param next  the next node in the list
     */
    public DoubleNode(T value, DoubleNode<T> prev, DoubleNode<T> next) {
        this.value = value;
        this.prev = prev;
        this.next = next;
    }

    /**
     * Creates a new doubly linked list node with the specified value.
     *
     * @param value the value to be stored in the node
     */
    public DoubleNode(T value) {
        this.value = value;
    }

    /**
     * Returns the value stored in the node.
     *
     * @return the value stored in the node
     */
    public T getValue() {
        return value;
    }

    /**
     * Sets the value of the node.
     *
     * @param value the value to be set
     */
    public void setValue(T value) {
        this.value = value;
    }

    /**
     * Returns the previous node in the list.
     *
     * @return the previous node in the list
     */
    public DoubleNode<T> getPrev() {
        return prev;
    }

    /**
     * Sets the previous node in the list.
     *
     * @param prev the previous node to be set
     */
    public void setPrev(DoubleNode<T> prev) {
        this.prev = prev;
    }

    /**
     * Returns the next node in the list.
     *
     * @return the next node in the list
     */
    public DoubleNode<T> getNext() {
        return next;
    }

    /**
     * Sets the next node in the list.
     *
     * @param next the next node to be set
     */
    public void setNext(DoubleNode<T> next) {
        this.next = next;
    }
}

