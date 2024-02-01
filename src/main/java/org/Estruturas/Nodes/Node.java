package org.Estruturas.Nodes;
/**
 * Node represents a node in a linked structure.
 *
 * @param <T> the type of element stored in the node
 * @author 8190357 - Marcelo Barbosa
 * @author 8180325 - Miguel Fonseca
 */
public class Node<T> {
    private T value;
    private Node<T> next;

    /**
     * Creates a node with the specified value and reference to the next node.
     *
     * @param value the value of the node
     * @param next  the reference to the next node
     */
    public Node(T value, Node<T> next) {
        this.value = value;
        this.next = next;
    }

    /**
     * Creates a node with the specified value and no reference to the next node.
     *
     * @param value the value of the node
     */
    public Node(T value) {
        this.value = value;
    }

    /**
     * Returns the value of the node.
     *
     * @return the value of the node
     */
    public T getValue() {
        return value;
    }

    /**
     * Sets the value of the node.
     *
     * @param value the new value for the node
     */
    public void setValue(T value) {
        this.value = value;
    }


    /**
     * Returns the reference to the next node.
     *
     * @return the reference to the next node
     */
    public Node<T> getNext() {
        return next;
    }

    /**
     * Sets the reference to the next node.
     *
     * @param next the new reference to the next node
     */
    public void setNext(Node<T> next) {
        this.next = next;
    }
}
