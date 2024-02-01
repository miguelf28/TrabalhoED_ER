package org.Estruturas.Nodes;

/**
 * BinaryTreeNode represents a node in a binary tree.
 *
 * @param <T> the type of element stored in the node
 * @author 8190357 - Marcelo Barbosa
 * @author 8180325 - Miguel Fonseca
 */
public class BinaryTreeNode<T> {
    /**
     * The element stored in the node.
     */
    public T element;
    /**
     * Reference to the left and right children of the node.
     */
    public BinaryTreeNode<T> left, right;

    /**
     * Creates a new binary tree node with the specified element.
     *
     * @param obj the element to be stored in the node
     */
    public BinaryTreeNode(T obj) {
        element = obj;
        left = null;
        right = null;
    }

    /**
     * Returns the number of children of this node.
     *
     * @return the number of children of this node
     */
    public int numChildren() {
        int children = 0;

        if (left != null) {
            children = 1 + left.numChildren();
        }
        if (right != null) {
            children = children + 1 + right.numChildren();
        }
        return children;
    }

    /**
     * Returns the element stored in the node.
     *
     * @return the element stored in the node
     */
    public T getElement() {
        return this.element;
    }

    /**
     * Returns the left child of the node.
     *
     * @return the left child of the node
     */
    public BinaryTreeNode getLeft() {
        return this.left;
    }

    /**
     * Returns the right child of the node.
     *
     * @return the right child of the node
     */
    public BinaryTreeNode getRight() {
        return this.right;
    }

    /**
     * Sets the element of the node.
     *
     * @param element the element to be set
     */
    public void setElement(T element) {
        this.element = element;
    }

    /**
     * Sets the left child of the node.
     *
     * @param node the left child to be set
     */
    public void setLeft(BinaryTreeNode node) {
        this.left = node;
    }

    /**
     * Sets the right child of the node.
     *
     * @param node the right child to be set
     */
    public void setRight(BinaryTreeNode node) {
        this.right = node;
    }
}
