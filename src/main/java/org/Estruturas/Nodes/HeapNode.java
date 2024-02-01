package org.Estruturas.Nodes;

/**
 * HeapNode represents a node in a binary heap.
 *
 * @param <T> the type of element stored in the node
 * @author 8190357 - Marcelo Barbosa
 * @author 8180325 - Miguel Fonseca
 */
public class HeapNode<T> extends BinaryTreeNode<T> {

    /**
     * Reference to the parent node in the heap.
     */
    protected HeapNode<T> parent;

    /**
     * Creates a new heap node with the specified element.
     *
     * @param obj the element to be stored in the node
     */
    public HeapNode(T obj) {
        super(obj);
        parent = null;
    }

    /**
     * Returns the parent node in the heap.
     *
     * @return the parent node in the heap
     */
    public HeapNode<T> getParent() {
        return parent;
    }

    /**
     * Sets the parent node in the heap.
     *
     * @param parent the parent node to be set
     */
    public void setParent(HeapNode<T> parent) {
        this.parent = parent;
    }
}
