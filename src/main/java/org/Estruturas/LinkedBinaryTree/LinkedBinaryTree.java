package org.Estruturas.LinkedBinaryTree;

import org.Estruturas.ArrayUnorderedList.ArrayUnorderedList;
import org.Estruturas.Exceptions.ElementNotFoundException;
import org.Estruturas.Exceptions.EmptyCollectionException;
import org.Estruturas.Nodes.BinaryTreeNode;

import java.util.Iterator;

/**
 * The LinkedBinaryTree class implements the BinaryTreeADT interface and represents a
 * binary tree data structure where each node has at most two children, referred to as
 * the left and right subtrees.
 *
 * @param <T> the type of elements in the binary tree
 * @author 8190357 - Marcelo Barbosa
 * @author 8180325 - Miguel Fonseca
 */
public class LinkedBinaryTree<T> implements BinaryTreeADT<T> {
    protected int count;
    protected BinaryTreeNode<T> root;

    /**
     * Constructs an empty binary tree.
     */
    public LinkedBinaryTree() {
        count = 0;
        root = null;
    }

    /**
     * Constructs a binary tree with a single element as the root.
     *
     * @param element the element to be set as the root
     */
    public LinkedBinaryTree(T element) {
        count = 1;
        root = new BinaryTreeNode<T>(element);
    }

    /**
     * Constructs a binary tree with a specified element as the root and left and right subtrees.
     *
     * @param element      the element to be set as the root
     * @param leftSubtree  the left subtree
     * @param rightSubtree the right subtree
     */
    public LinkedBinaryTree(T element, LinkedBinaryTree<T> leftSubtree, LinkedBinaryTree<T> rightSubtree) {
        root = new BinaryTreeNode<T>(element);
        count = 1;

        if (leftSubtree != null) {
            count = count + leftSubtree.size();
            root.left = leftSubtree.root;
        } else
            root.left = null;

        if (rightSubtree != null) {
            count = count + rightSubtree.size();
            root.right = rightSubtree.root;
        } else
            root.right = null;
    }

    /**
     * Removes the left subtree of the root.
     */
    public void removeLeftSubtree() {
        if (root.left != null)
            count = count - root.left.numChildren() - 1;
        root.left = null;
    }

    /**
     * Removes the right subtree of the root.
     */
    public void removeRightSubtree() {
        if (root.right != null)
            count = count - root.right.numChildren() - 1;

        root.right = null;
    }

    /**
     * Removes all elements from the binary tree.
     */
    public void removeAllElements() {
        count = 0;
        root = null;
    }

    /**
     * Recursively finds a target element in the binary tree starting from the given node.
     *
     * @param targetElement the element to find
     * @param next the current node being examined
     * @return the node containing the target element, or null if not found
     */
    private BinaryTreeNode<T> findAgain(T targetElement, BinaryTreeNode<T> next) {
        if (next == null) {
            return null;
        }

        BinaryTreeNode<T> temp = findAgain(targetElement, next.left);
        if (next.element.equals(targetElement)) {
            return next;
        }

        if (temp == null) {
            temp = findAgain(targetElement, next.right);
        }
        return temp;
    }

    /**
     * Returns the element stored in the root of the binary tree.
     *
     * @return the element in the root
     */
    @Override
    public T getRoot() {
        return this.root.getElement();
    }

    /**
     * Checks if the binary tree is empty.
     *
     * @return true if the binary tree is empty, false otherwise
     */
    @Override
    public boolean isEmpty() {
        return (count == 0);
    }

    /**
     * Returns the number of elements in the binary tree.
     *
     * @return the number of elements in the binary tree
     */
    @Override
    public int size() {
        return count;
    }

    /**
     * Checks if the binary tree contains a specific element.
     *
     * @param targetElement the element to search for
     * @return true if the element is found, false otherwise
     * @throws ElementNotFoundException if the element is not found
     */
    @Override
    public boolean contains(T targetElement) throws ElementNotFoundException {
        T temp;
        boolean found = false;

        try {
            temp = find(targetElement);
            found = true;
        } catch (Exception | ElementNotFoundException ex) {
            found = false;
        }
        return found;
    }

    /**
     * Finds and returns a specific element in the binary tree.
     *
     * @param targetElement the element to find
     * @return the element if found
     * @throws ElementNotFoundException if the element is not found
     */
    @Override
    public T find(T targetElement) throws ElementNotFoundException {
        BinaryTreeNode<T> current = findAgain(targetElement, root);
        if (current == null) {
            throw new ElementNotFoundException("binary tree");
        }
        return (current.element);
    }

    /**
     * Returns an iterator for traversing the binary tree in an in-order fashion.
     *
     * @return an iterator for in-order traversal
     */
    @Override
    public Iterator<T> iteratorInOrder() {
        ArrayUnorderedList<T> tempList = new ArrayUnorderedList<T>();
        inorder(root, tempList);

        return tempList.iterator();
    }

    /**
     * Helper method for in-order traversal.
     *
     * @param node the current node in the traversal
     * @param tempList the list to store the elements in order
     */
    protected void inorder(BinaryTreeNode<T> node, ArrayUnorderedList<T> tempList) {
        if (node != null) {
            inorder(node.left, tempList);
            tempList.addToRear(node.element);
            inorder(node.right, tempList);
        }
    }

    /**
     * Returns an iterator for traversing the binary tree in a pre-order fashion.
     *
     * @return an iterator for pre-order traversal
     */
    @Override
    public Iterator<T> iteratorPreOrder() {
        ArrayUnorderedList<T> tempList = new ArrayUnorderedList<T>();
        preorder(root, tempList);

        return tempList.iterator();
    }

    /**
     * Helper method for pre-order traversal.
     *
     * @param node the current node in the traversal
     * @param tempList the list to store the elements in pre-order
     */
    protected void preorder(BinaryTreeNode<T> node, ArrayUnorderedList<T> tempList) {
        if (node != null) {
            tempList.addToRear(node.element);
            preorder(node.left, tempList);
            preorder(node.right, tempList);
        }
    }

    /**
     * Returns an iterator for traversing the binary tree in a post-order fashion.
     *
     * @return an iterator for post-order traversal
     */
    @Override
    public Iterator<T> iteratorPostOrder() {
        ArrayUnorderedList<T> tempList = new ArrayUnorderedList<T>();
        postorder(root, tempList);

        return tempList.iterator();
    }

    /**
     * Helper method for post-order traversal.
     *
     * @param node the current node in the traversal
     * @param tempList the list to store the elements in post-order
     */
    protected void postorder(BinaryTreeNode<T> node, ArrayUnorderedList<T> tempList) {
        if (node != null) {
            postorder(node.left, tempList);
            postorder(node.right, tempList);
            tempList.addToRear(node.element);
        }
    }

    /**
     * Returns an iterator for traversing the binary tree in a level-order fashion.
     *
     * @return an iterator for level-order traversal
     * @throws EmptyCollectionException if the binary tree is empty
     */
    @Override
    public Iterator<T> iteratorLevelOrder() throws EmptyCollectionException {
        ArrayUnorderedList<BinaryTreeNode<T>> nodes = new ArrayUnorderedList<BinaryTreeNode<T>>();
        ArrayUnorderedList<T> tempList = new ArrayUnorderedList<T>();
        BinaryTreeNode<T> current;

        nodes.addToRear(root);

        while (!nodes.isEmpty()) {
            current = (BinaryTreeNode<T>) (nodes.removeFirst());
            if (current != null) {
                tempList.addToRear(current.element);
                if (current.left != null)
                    nodes.addToRear(current.left);
                if (current.right != null)
                    nodes.addToRear(current.right);
            } else
                tempList.addToRear(null);
        }
        return tempList.iterator();
    }
}
