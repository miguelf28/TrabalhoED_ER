package org.Estruturas.ArrayBinarySearchTree;

import org.Estruturas.Exceptions.ElementNotFoundException;
import org.Estruturas.Exceptions.EmptyCollectionException;
import org.Estruturas.LinkedBinaryTree.BinaryTreeADT;

/**
 * Interface for a Binary Search Tree (BST) ADT.
 *
 * @param <T> The type of elements in the binary search tree.
 * @author 8190357 - Marcelo Barbosa
 * @author 8180325 - Miguel Fonseca
 */
public interface BinarySearchTreeADT<T> extends BinaryTreeADT<T> {

    /**
     * Adds the specified element to the proper location in this tree.
     *
     * @param element the element to be added to this tree
     */
    public void addElement (T element);

    /**
     * Removes and returns the specified element from this tree.
     *
     * @param targetElement the element to be removed from this tree
     * @return the element removed from this tree
     */
    public T removeElement (T targetElement) throws ElementNotFoundException, EmptyCollectionException;

    /**
     * Removes all occurences of the specified element from this tree.
     *
     * @param targetElement the element that the list will have all instances of
     * it removed
     */
    public void removeAllOccurrences (T targetElement) throws ElementNotFoundException, EmptyCollectionException;

    /**
     * Removes and returns the smallest element from this tree.
     *
     * @return the smallest element from this tree.
     * @throws org.Estruturas.Exceptions.EmptyCollectionException
     */
    public T removeMin() throws EmptyCollectionException;

    /**
     * Removes and returns the largest element from this tree.
     *
     * @return the largest element from this tree
     * @throws org.Estruturas.Exceptions.EmptyCollectionException
     */
    public T removeMax() throws EmptyCollectionException;

    /**
     * Returns a reference to the smallest element in this tree.
     *
     * @return a reference to the smallest element in this tree
     * @throws org.Estruturas.Exceptions.EmptyCollectionException
     */
    public T findMin() throws EmptyCollectionException;

    /**
     * Returns a reference to the largest element in this tree.
     *
     * @return a reference to the largest element in this tree
     * @throws org.Estruturas.Exceptions.EmptyCollectionException
     */
    public T findMax() throws EmptyCollectionException;
}
