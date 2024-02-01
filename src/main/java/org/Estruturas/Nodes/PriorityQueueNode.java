package org.Estruturas.Nodes;

/**
 * PriorityQueueNode represents a node in a priority queue.
 *
 * @param <T> the type of element stored in the node
 * @author 8190357 - Marcelo Barbosa
 * @author 8180325 - Miguel Fonseca
 */
public class PriorityQueueNode<T> implements Comparable<PriorityQueueNode> {
    private static int nextOrder = 0;
    private int priority;
    private int order;
    private T element;


    /**
     * Creates a PriorityQueueNode with the specified element and priority.
     *
     * @param element  the element of the node
     * @param priority the priority of the node
     */
    public PriorityQueueNode(T element, int priority) {
        this.element = element;
        this.priority = priority;
        this.order = nextOrder;
        nextOrder++;
    }

    /**
     * Returns the element of the node.
     *
     * @return the element of the node
     */
    public T getElement() {
        return this.element;
    }

    /**
     * Returns the priority of the node.
     *
     * @return the priority of the node
     */
    public int getPriority() {
        return this.priority;
    }

    /**
     * Returns the order of the node.
     *
     * @return the order of the node
     */
    public int getOrder() {
        return this.order;
    }

    /**
     * Returns a string representation of the node.
     *
     * @return a string representation of the node
     */
    public String toString() {
        String temp = (this.element.toString() + this.priority + this.order);
        return temp;
    }

    /**
     * Compares this node with another PriorityQueueNode based on priority and order.
     *
     * @param obj the node to compare with
     * @return a negative integer, zero, or a positive integer as this node is less than,
     *         equal to, or greater than the specified node
     */
    @Override
    public int compareTo(PriorityQueueNode obj) {
        int result;
        PriorityQueueNode<T> temp = obj;
        if (this.priority > temp.getPriority()) {
            result = 1;
        } else if (this.priority < temp.getPriority()) {
            result = -1;
        } else if (this.order > temp.getOrder()) {
            result = 1;
        } else {
            result = -1;
        }
        return result;
    }
}
