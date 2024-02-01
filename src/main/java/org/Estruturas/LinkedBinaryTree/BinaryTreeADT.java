package org.Estruturas.LinkedBinaryTree;

import org.Estruturas.Exceptions.ElementNotFoundException;
import org.Estruturas.Exceptions.EmptyCollectionException;

import java.util.Iterator;

/**
 * The BinaryTreeADT interface represents the basic operations that any binary tree
 * data structure should support.
 *
 * @param <T> the type of elements in the binary tree
 * @author 8190357 - Marcelo Barbosa
 * @author 8180325 - Miguel Fonseca
 */
public interface BinaryTreeADT<T> {

    /**
     * Returns the root element of the binary tree.
     *
     * @return the root element of the binary tree
     */
    public T getRoot();

    /**
     * Checks if the binary tree is empty.
     *
     * @return true if the binary tree is empty, false otherwise
     */
    public boolean isEmpty();

    /**
     * Returns the number of elements in the binary tree.
     *
     * @return the number of elements in the binary tree
     */
    public int size();

    /**
     * Checks if the binary tree contains a specific element.
     *
     * @param targetElement the element to be checked for
     * @return true if the element is present, false otherwise
     * @throws ElementNotFoundException if the element is not found
     */
    public boolean contains(T targetElement) throws ElementNotFoundException;

    /**
     * Finds and returns a specific element in the binary tree.
     *
     * @param targetElement the element to be found
     * @return the found element
     * @throws ElementNotFoundException if the element is not found
     */
    public T find(T targetElement) throws ElementNotFoundException;

    /**
     * Returns a string representation of the binary tree.
     *
     * @return a string representation of the binary tree
     */
    public String toString();

    /**
     * Returns an iterator over the elements of the binary tree in in-order traversal.
     *
     * @return an iterator over the elements in in-order
     */
    public Iterator<T> iteratorInOrder();

    /**
     * Returns an iterator over the elements of the binary tree in pre-order traversal.
     *
     * @return an iterator over the elements in pre-order
     */
    public Iterator<T> iteratorPreOrder();

    /**
     * Returns an iterator over the elements of the binary tree in post-order traversal.
     *
     * @return an iterator over the elements in post-order
     */
    public Iterator<T> iteratorPostOrder();

    /**
     * Returns an iterator over the elements of the binary tree in level-order traversal.
     *
     * @return an iterator over the elements in level-order
     * @throws EmptyCollectionException if the binary tree is empty
     */
    public Iterator<T> iteratorLevelOrder() throws EmptyCollectionException;
}
