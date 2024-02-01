package org.Estruturas.Nodes;

import org.Estruturas.ArrayBinarySearchTree.BinarySearchTreeADT;
import org.Estruturas.Exceptions.ElementNotFoundException;
import org.Estruturas.Exceptions.EmptyCollectionException;
import org.Estruturas.LinkedBinaryTree.LinkedBinaryTree;

/**
 * LinkedBinarySearchTree represents a linked implementation of a binary search tree.
 *
 * @param <T> the type of element stored in the tree
 * @author 8190357 - Marcelo Barbosa
 * @author 8180325 - Miguel Fonseca
 */
public class LinkedBinarySearchTree<T> extends LinkedBinaryTree<T> implements BinarySearchTreeADT<T> {
    /**
     * Creates an empty binary search tree.
     */
    public LinkedBinarySearchTree() {
        super();
    }

    /**
     * Creates a binary search tree with the specified element as the root.
     *
     * @param element the element to become the root
     */
    public LinkedBinarySearchTree(T element) {
        super(element);
    }


    /**
     * Adds the specified element to the binary search tree in the appropriate
     * location based on the natural order of elements.
     *
     * @param element the element to be added to the tree
     */
    @Override
    public void addElement(T element) {
        BinaryTreeNode<T> temp = new BinaryTreeNode<T>(element);
        Comparable<T> comparableElement = (Comparable<T>) element;

        if (isEmpty())
            root = temp;
        else {
            BinaryTreeNode<T> current = root;
            boolean added = false;
            while (!added) {
                if (comparableElement.compareTo(current.element) < 0) {
                    if (current.left == null) {
                        current.left = temp;
                        added = true;
                    } else
                        current = current.left;
                } else {
                    if (current.right == null) {
                        current.right = temp;
                        added = true;
                    } else
                        current = current.right;
                }
            }
        }
        count++;
    }


    /**
     * Removes and returns the specified element from the binary search tree.
     *
     * @param targetElement the element to be removed from the tree
     * @return the removed element
     * @throws ElementNotFoundException if the target element is not found
     */
    @Override
    public T removeElement(T targetElement) throws ElementNotFoundException {
        T result = null;
        if (!isEmpty()) {
            if (((Comparable) targetElement).equals(root.element)) {
                result = root.element;
                root = replacement(root);
                count--;
            } else {
                BinaryTreeNode<T> current, parent = root;
                boolean found = false;
                if (((Comparable) targetElement).compareTo(root.element) < 0)
                    current = root.left;
                else
                    current = root.right;
                while (current != null && !found) {
                    if (targetElement.equals(current.element)) {
                        found = true;
                        count--;
                        result = current.element;

                        if (current == parent.left) {
                            parent.left = replacement(current);
                        } else {
                            parent.right = replacement(current);
                        }
                    } else {
                        parent = current;

                        if (((Comparable) targetElement).compareTo(current.element) < 0)
                            current = current.left;
                        else
                            current = current.right;
                    }
                } //while

                if (!found)
                    throw new ElementNotFoundException("binary search tree");
            }
        } // end outer if
        return result;
    }

    /**
     * Returns the node that will replace the specified node during a removal
     * operation for a binary search tree.
     *
     * @param node the node to be replaced
     * @return the node that will replace the specified node
     */
    protected BinaryTreeNode<T> replacement(BinaryTreeNode<T> node) {
        BinaryTreeNode<T> result = null;
        if ((node.left == null) && (node.right == null))
            result = null;

        else if ((node.left != null) && (node.right == null))
            result = node.left;

        else if ((node.left == null) && (node.right != null))
            result = node.right;

        else {
            BinaryTreeNode<T> current = node.right;
            BinaryTreeNode<T> parent = node;
            while (current.left != null) {
                parent = current;
                current = current.left;
            }
            if (node.right == current)
                current.left = node.left;

            else {
                parent.left = current.right;
                current.right = node.right;
                current.left = node.left;
            }
            result = current;
        }
        return result;
    }

    /**
     * Removes all occurrences of the specified element from the binary search tree.
     *
     * @param targetElement the element to be removed from the tree
     * @throws ElementNotFoundException if the target element is not found
     */
    @Override
    public void removeAllOccurrences(T targetElement) throws ElementNotFoundException {
        removeElement(targetElement);

        try {
            while (contains((T) targetElement))
                removeElement(targetElement);
        } catch (Exception ElementNotFoundException) {
        }
    }

    /**
     * Removes and returns the smallest element from the binary search tree.
     *
     * @return the smallest element in the tree
     * @throws EmptyCollectionException if the tree is empty
     */
    @Override
    public T removeMin() throws EmptyCollectionException {
        T result = null;

        if (isEmpty())
            throw new EmptyCollectionException("binary tree");
        else {
            if (root.left == null) {
                result = root.element;
                root = root.right;
            } else {
                BinaryTreeNode<T> parent = root;
                BinaryTreeNode<T> current = root.left;
                while (current.left != null) {
                    parent = current;
                    current = current.left;
                }
                result = current.element;
                parent.left = current.right;
            }
            count--;
        }
        return result;
    }

    /**
     * Removes and returns the largest element from the binary search tree.
     *
     * @return the largest element in the tree
     * @throws EmptyCollectionException if the tree is empty
     */
    @Override
    public T removeMax() throws EmptyCollectionException {
        T result = null;

        if (isEmpty())
            throw new EmptyCollectionException("binary tree");
        else {
            if (root.right == null) {
                result = root.element;
                root = root.left;
            } else {
                BinaryTreeNode<T> parent = root;
                BinaryTreeNode<T> current = root.right;

                while (current.right != null) {
                    parent = current;
                    current = current.right;
                }
                result = current.element;
                parent.right = current.left;
            }
            count--;
        }
        return result;
    }

    /**
     * Returns the smallest element in the binary search tree without removing it.
     *
     * @return the smallest element in the tree
     * @throws EmptyCollectionException if the tree is empty
     */
    @Override
    public T findMin() throws EmptyCollectionException {
        T result = null;

        if (isEmpty())
            throw new EmptyCollectionException("binary tree");
        else {
            BinaryTreeNode<T> current = root;

            while (current.left != null)
                current = current.left;

            result = current.element;
        }
        return result;
    }

    /**
     * Returns the largest element in the binary search tree without removing it.
     *
     * @return the largest element in the tree
     * @throws EmptyCollectionException if the tree is empty
     */
    @Override
    public T findMax() throws EmptyCollectionException {
        T result = null;

        if (isEmpty())
            throw new EmptyCollectionException("binary tree");
        else {
            BinaryTreeNode<T> current = root;

            while (current.right != null)
                current = current.right;

            result = current.element;
        }
        return result;
    }

    /**
     * Returns a string representation of the binary search tree using an in-order
     * traversal.
     *
     * @return a string representation of the binary search tree
     */
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        inOrderTraversal(root, result);
        return result.toString();
    }

    /**
     * Performs an in-order traversal of the binary search tree, appending the
     * elements to the specified StringBuilder.
     *
     * @param node   the current node in the traversal
     * @param result the StringBuilder to append elements
     */
    private void inOrderTraversal(BinaryTreeNode<T> node, StringBuilder result) {
        if (node != null) {
            inOrderTraversal(node.left, result);
            result.append(node.element).append(" ");
            inOrderTraversal(node.right, result);
        }
    }
}
