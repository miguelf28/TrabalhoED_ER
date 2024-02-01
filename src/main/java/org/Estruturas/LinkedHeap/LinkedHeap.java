package org.Estruturas.LinkedHeap;

import org.Estruturas.Exceptions.EmptyCollectionException;
import org.Estruturas.LinkedBinaryTree.LinkedBinaryTree;
import org.Estruturas.Nodes.HeapNode;

/**
 * Implementation of a linked heap data structure.
 *
 * @param <T> the type of elements stored in the heap
 * @author 8190357 - Marcelo Barbosa
 * @author 8180325 - Miguel Fonseca
 */
public class LinkedHeap<T> extends LinkedBinaryTree<T> implements HeapADT<T> {

    /**
     * Reference to the last node in the heap.
     */
    public HeapNode<T> lastNode;

    /**
     * Creates an empty linked heap.
     */
    public LinkedHeap() {
        super();
    }

    /**
     * Adds the specified element to this heap.
     *
     * @param obj the element to be added to the heap
     */
    @Override
    public void addElement(T obj) {
        HeapNode<T> node = new HeapNode<T>(obj);
        if (root == null)
            root = node;
        else {
            HeapNode<T> next_parent = getNextParentAdd();
            if (next_parent.left == null)
                next_parent.left = node;
            else
                next_parent.right = node;

            node.setParent(next_parent);
        }
        lastNode = node;
        count++;
        if (count > 1)
            heapifyAdd();
    }

    /**
     * Removes and returns the smallest element in this heap.
     *
     * @return the smallest element in the heap
     * @throws EmptyCollectionException if the heap is empty
     */
    @Override
    public T removeMin() throws EmptyCollectionException {
        if (isEmpty())
            throw new EmptyCollectionException("Empty Heap");
        T minElement = root.element;
        if (count == 1) {
            root = null;
            lastNode = null;
        } else {
            HeapNode<T> next_last = getNewLastNode();
            if (lastNode.getParent().left == lastNode)
                lastNode.getParent().left = null;
            else
                lastNode.getParent().right = null;
            root.element = lastNode.element;
            lastNode = next_last;
            heapifyRemove();
        }
        count--;

        return minElement;
    }

    /**
     * Returns the smallest element in this heap without removing it.
     *
     * @return the smallest element in the heap
     * @throws EmptyCollectionException if the heap is empty
     */
    @Override
    public T findMin() {
        return this.root.getElement();
    }

    /**
     * Returns a string representation of this heap.
     *
     * @return a string representation of this heap
     */
    @Override
    public String toString() {
        return traverse((HeapNode<T>) root);
    }

    /**
     * Traverses the heap in the specified order and returns a string representation.
     *
     * @param node the starting node for traversal
     * @return a string representation of the heap
     */
    private String traverse(HeapNode<T> node) {
        if (node == null) {
            return "";
        }

        String left = traverse((HeapNode<T>) node.left);
        String right = traverse((HeapNode<T>) node.right);

        return left + node.getElement() + " " + right;
    }

    /**
     * Returns the next parent node in the heap for adding a new element.
     *
     * @return the next parent node for adding a new element
     */
    private HeapNode<T> getNextParentAdd() {
        HeapNode<T> result = lastNode;
        while ((result != root) &&
                (result.getParent().left != result))
            result = result.getParent();
        if (result != root)
            if (result.getParent().right == null)
                result = result.getParent();
            else {
                result = (HeapNode<T>) result.getParent().right;
                while (result.left != null)
                    result = (HeapNode<T>) result.left;
            }
        else
            while (result.left != null)
                result = (HeapNode<T>) result.left;

        return result;
    }

    /**
     * Restores the heap property after adding a new element.
     */
    private void heapifyAdd() {
        T temp;
        HeapNode<T> next = lastNode;

        temp = next.element;

        while ((next != root) && (((Comparable)temp).compareTo
                (next.getParent().element) < 0))
        {
            next.element = next.getParent().element;
            next = next.getParent();
        }
        next.element = temp;
    }

    /**
     * Returns the new last node after removing the smallest element.
     *
     * @return the new last node after removing the smallest element
     */
    private HeapNode<T> getNewLastNode() {
        HeapNode<T> result = lastNode;
        while ((result != root) && (result.getParent().left == result))
            result = result.getParent();

        if (result != root)
            result = (HeapNode<T>) result.getParent().left;
        while (result.right != null)
            result = (HeapNode<T>) result.right;
        return result;
    }

    /**
     * Restores the heap property after removing the smallest element.
     */
    private void heapifyRemove() {
        T temp;
        HeapNode<T> node = (HeapNode<T>) root;
        HeapNode<T> left = (HeapNode<T>) node.left;
        HeapNode<T> right = (HeapNode<T>) node.right;
        HeapNode<T> next;

        if ((left == null) && (right == null))
            next = null;
        else if (left == null)
            next = right;
        else if (right == null)
            next = left;
        else if (((Comparable) left.element).compareTo(right.element) < 0)
            next = left;
        else
            next = right;
        temp = node.element;
        while ((next != null) && (((Comparable) next.element).compareTo(temp) < 0)) {
            node.element = next.element;
            node = next;
            left = (HeapNode<T>) node.left;
            right = (HeapNode<T>) node.right;

            if ((left == null) && (right == null))
                next = null;
            else if (left == null)
                next = right;
            else if (right == null)
                next = left;
            else if (((Comparable) left.element).compareTo(right.element) < 0)
                next = left;
            else
                next = right;
        }
        node.element = temp;
    }
}
