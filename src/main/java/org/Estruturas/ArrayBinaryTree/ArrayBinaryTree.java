package org.Estruturas.ArrayBinaryTree;

import org.Estruturas.ArrayUnorderedList.ArrayUnorderedList;
import org.Estruturas.Exceptions.ElementNotFoundException;
import org.Estruturas.Exceptions.EmptyCollectionException;
import org.Estruturas.LinkedBinaryTree.BinaryTreeADT;

import java.util.Iterator;

/**
 * Represents an array-based implementation of a binary tree.
 *
 * @param <T> The type of elements stored in the binary tree.
 * @author 8190357 - Marcelo Barbosa
 * @author 8180325 - Miguel Fonseca
 */
public class ArrayBinaryTree<T> implements BinaryTreeADT<T> {
    /**
     * The number of elements in the binary tree.
     */
    protected int count;

    /**
     * The array representing the binary tree.
     */
    protected T[] tree;
    /**
     * The initial capacity of the array.
     */
    private final int capacity = 50;

    /**
     * Creates an empty binary tree.
     */
    public ArrayBinaryTree() {
        count = 0;
        tree = (T[]) new Object[capacity];
    }

    /**
     * Creates a binary tree with the specified element as its root.
     *
     * @param element the element which will become the root
     *                of the new tree
     */
    public ArrayBinaryTree(T element) {
        count = 1;
        tree = (T[]) new Object[capacity];
        tree[0] = element;
    }

    /**
     * Expands the capacity of the array-based binary tree.
     */
    protected void expandCapacity() {
        T[] temp = (T[]) new Object[tree.length * 2];
        for (int ct = 0; ct < tree.length; ct++) {
            temp[ct] = tree[ct];
        }
        tree = temp;
    }

    /**
     * Returns the element at the root of this binary tree.
     * This operation is not supported in an array-based binary tree.
     *
     * @return The element at the root of this binary tree.
     * @throws UnsupportedOperationException if the operation is not supported
     */
    @Override
    public T getRoot() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * Checks if this binary tree is empty.
     *
     * @return true if the binary tree is empty, false otherwise.
     */
    @Override
    public boolean isEmpty() {
        return (count == 0);
    }

    /**
     * Returns the number of elements in this binary tree.
     *
     * @return The number of elements in this binary tree.
     */
    @Override
    public int size() {
        return count;
    }

    /**
     * Checks if this binary tree contains the specified element.
     *
     * @param targetElement The element to be checked.
     * @return true if the element is found in the binary tree, false otherwise.
     */
    @Override
    public boolean contains(T targetElement) {
        boolean found = false;

        for (int ct = 0; ct < count && !found; ct++) {
            if (targetElement.equals(tree[ct])) {
                found = true;
            }
        }
        return found;
    }

    /**
     * Returns a reference to the specified target element if it is
     * found in this binary tree. Throws a NoSuchElementException if
     * the specified target element is not found in the binary tree.
     *
     * @param targetElement the element being sought in the tree
     * @return true if the element is in the tree
     * @throws ElementNotFoundException if an element not found
     *                                  exception occurs
     */
    @Override
    public T find(T targetElement) throws ElementNotFoundException {
        T temp = null;
        boolean found = false;

        for (int ct = 0; ct < count && !found; ct++) {
            if (targetElement.equals(tree[ct])) {
                found = true;
                temp = tree[ct];
            }
        }
        if (!found) {
            throw new ElementNotFoundException("binary tree");
        }
        return temp;
    }

    /**
     * Performs an inorder traversal on this binary tree by
     * calling an overloaded, recursive inorder method
     * that starts with the root.
     *
     * @return an iterator over the binary tree
     */

    @Override
    public Iterator<T> iteratorInOrder() {
        ArrayUnorderedList<T> templist = new ArrayUnorderedList<>();
        inorder(0, templist);
        return templist.iterator();
    }

    /**
     * Performs a recursive inorder traversal.
     *
     * @param node     the node used in the traversal
     * @param templist the temporary list used in the traversal
     */
    protected void inorder(int node, ArrayUnorderedList<T> templist) {
        if (node < tree.length) {
            if (tree[node] != null) {
                inorder((node + 1) * 2 - 1, templist);
                templist.addToRear(tree[node]);
                inorder((node + 1) * (2 + 1) - 1, templist);
            }
        }
    }

    /**
     * Returns an iterator that traverses the elements of this binary tree in pre-order.
     *
     * @return An iterator over the binary tree in pre-order.
     */
    @Override
    public Iterator<T> iteratorPreOrder() {
        ArrayUnorderedList<T> templist = new ArrayUnorderedList<>();
        preorder(0, templist);
        return templist.iterator();
    }

    /**
     * Performs a recursive pre-order traversal.
     *
     * @param node     The node used in the traversal.
     * @param templist The temporary list used in the traversal.
     */
    protected void preorder(int node, ArrayUnorderedList<T> templist) {
        if (node < tree.length) {
            if (tree[node] != null) {
                templist.addToRear(tree[node]);
                inorder((node + 1) * 2 - 1, templist);
                inorder((node + 1) * (2 + 1) - 1, templist);
            }
        }
    }

    /**
     * Returns an iterator that traverses the elements of this binary tree in post-order.
     *
     * @return An iterator over the binary tree in post-order.
     */
    @Override
    public Iterator<T> iteratorPostOrder() {
        ArrayUnorderedList<T> templist = new ArrayUnorderedList<>();
        postorder(0, templist);
        return templist.iterator();
    }

    /**
     * Performs a recursive post-order traversal.
     *
     * @param node     The node used in the traversal.
     * @param templist The temporary list used in the traversal.
     */
    protected void postorder(int node, ArrayUnorderedList<T> templist) {
        if (node < tree.length) {
            if (tree[node] != null) {
                inorder((node + 1) * 2 - 1, templist);
                inorder((node + 1) * (2 + 1) - 1, templist);
                templist.addToRear(tree[node]);
            }
        }
    }

    /**
     * Returns an iterator that traverses the elements of this binary tree in level order.
     *
     * @return An iterator over the binary tree in level order.
     * @throws EmptyCollectionException if the tree is empty
     */
    @Override
    public Iterator<T> iteratorLevelOrder() throws EmptyCollectionException {
        ArrayUnorderedList<T> templist = new ArrayUnorderedList<>();
        for (int ct = 0; ct < count; ct++) {
            templist.addToRear(tree[ct]);
        }
        return templist.iterator();
    }
}
