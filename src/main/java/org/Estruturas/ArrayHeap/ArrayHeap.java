package org.Estruturas.ArrayHeap;

import org.Estruturas.ArrayBinaryTree.ArrayBinaryTree;
import org.Estruturas.Exceptions.EmptyCollectionException;
import org.Estruturas.LinkedHeap.HeapADT;

/**
 * Represents a heap implemented as an array-based binary tree.
 *
 * @param <T> The type of elements stored in the heap.
 * @author 8190357 - Marcelo Barbosa
 * @author 8180325 - Miguel Fonseca
 */
public class ArrayHeap<T> extends ArrayBinaryTree<T> implements HeapADT<T> {

    /**
     * Constructs an empty heap.
     */
    public ArrayHeap() {
        super();
    }

    /**
     * Adds the specified element to this heap.
     *
     * @param obj The element to be added to the heap.
     */
    @Override
    public void addElement(T obj) {
        if (count == tree.length)
            expandCapacity();
        tree[count] = obj;
        count++;
        if (count > 1)
            heapifyAdd();
    }

    /**
     * Removes and returns the minimum element from this heap.
     *
     * @return The minimum element in the heap.
     * @throws EmptyCollectionException if the heap is empty.
     */
    @Override
    public T removeMin() throws EmptyCollectionException {
        if (isEmpty())
            throw new EmptyCollectionException("Empty Heap");
        T minElement = tree[0];
        tree[0] = tree[count - 1];
        heapifyRemove();
        count--;

        return minElement;
    }

    /**
     * Returns the minimum element in this heap without removing it.
     *
     * @return The minimum element in the heap.
     * @throws EmptyCollectionException if the heap is empty.
     */
    @Override
    public T findMin() throws EmptyCollectionException {
        if (isEmpty()) {
            throw new EmptyCollectionException("Empty Heap");
        }
        return tree[0];
    }

    /**
     * Returns a string representation of this heap.
     *
     * @return A string representation of the heap.
     */
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < count; i++) {
            result.append(tree[i]).append(" ");
        }
        return result.toString().trim();
    }

    /**
     * Adjusts the heap after adding an element to maintain the heap property.
     */
    private void heapifyAdd() {
        T temp;
        int next = count - 1;

        temp = tree[next];

        while ((next != 0) && (((Comparable) temp).compareTo
                (tree[(next - 1) / 2]) < 0)) {
            tree[next] = tree[(next - 1) / 2];
            next = (next - 1) / 2;
        }
        tree[next] = temp;
    }

    /**
     * Adjusts the heap after removing an element to maintain the heap property.
     */
    private void heapifyRemove() {
        T temp;
        int node = 0;
        int left = 1;
        int right = 2;
        int next;

        if ((tree[left] == null) && (tree[right] == null))
            next = count;
        else if (tree[left] == null)
            next = right;
        else if (tree[right] == null)
            next = left;
        else if (((Comparable) tree[left]).compareTo(tree[right]) < 0)
            next = left;
        else
            next = right;
        temp = tree[node];
        while ((next < count) && (((Comparable) tree[next]).compareTo
                (temp) < 0)) {
            tree[node] = tree[next];
            node = next;
            left = 2 * node + 1;
            right = 2 * (node + 1);
            if ((tree[left] == null) && (tree[right] == null))
                next = count;
            else if (tree[left] == null)
                next = right;
            else if (tree[right] == null)
                next = left;
            else if (((Comparable) tree[left]).compareTo(tree[right]) < 0)
                next = left;
            else
                next = right;
        }
        tree[node] = temp;
    }
}
