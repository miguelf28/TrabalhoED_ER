package org.Estruturas.ArrayBinarySearchTree;

import org.Estruturas.ArrayBinaryTree.ArrayBinaryTree;
import org.Estruturas.ArrayUnorderedList.ArrayUnorderedList;
import org.Estruturas.Exceptions.ElementNotFoundException;
import org.Estruturas.Exceptions.EmptyCollectionException;

import java.util.Iterator;

/**
 * Implementation of a Binary Search Tree (BST) using an array to store elements.
 *
 * @param <T> The type of elements stored in the tree.
 * @author 8190357 - Marcelo Barbosa
 * @author 8180325 - Miguel Fonseca
 */
public class ArrayBinarySearchTree<T> extends ArrayBinaryTree<T> implements BinarySearchTreeADT<T> {

    /**
     * Height of the tree.
     */
    protected int height;
    /**
     * Maximum occupied index in the array.
     */
    protected int maxIndex;

    /**
     * Creates a new instance of ArrayBinarySearchTree.
     */
    public ArrayBinarySearchTree() {
        super();
        height = 0;
        maxIndex = -1;
    }

    /**
     * Creates a new instance of ArrayBinarySearchTree with a specified element.
     *
     * @param element The element to be added to the tree.
     */
    public ArrayBinarySearchTree(T element) {
        super(element);
        height = 1;
        maxIndex = 0;
    }

    /**
     * Adds an element to the tree.
     *
     * @param element The element to be added to the tree.
     */
    @Override
    public void addElement(T element) {
        if (tree.length < maxIndex * 2 + 3)
            expandCapacity();
        Comparable<T> tempelement = (Comparable<T>) element;
        if (isEmpty()) {
            tree[0] = element;
            maxIndex = 0;
        } else {
            boolean added = false;
            int currentIndex = 0;
            while (!added) {
                if (tempelement.compareTo((tree[currentIndex])) < 0) {
                    /** go left */
                    if (tree[currentIndex * 2 + 1] == null) {
                        tree[currentIndex * 2 + 1] = element;
                        added = true;
                        if (currentIndex * 2 + 1 > maxIndex)
                            maxIndex = currentIndex * 2 + 1;
                    } else
                        currentIndex = currentIndex * 2 + 1;
                } else {
                    /** go right */
                    if (tree[currentIndex * 2 + 2] == null) {
                        tree[currentIndex * 2 + 2] = element;
                        added = true;
                        if (currentIndex * 2 + 2 > maxIndex)
                            maxIndex = currentIndex * 2 + 2;
                    } else
                        currentIndex = currentIndex * 2 + 2;
                }
            }
        }
        height = (int) (Math.log(maxIndex + 1) / Math.log(2)) + 1;
        count++;
    }

    /**
     * Removes the specified element from the tree.
     *
     * @param targetElement The element to be removed from the tree.
     * @return The removed element.
     * @throws ElementNotFoundException If the element is not found in the tree.
     * @throws EmptyCollectionException If the tree is empty.
     */
    @Override
    public T removeElement(T targetElement) throws ElementNotFoundException, EmptyCollectionException {
        T result = null;
        boolean found = false;

        if (isEmpty())
            return result;

        for (int i = 0; (i <= maxIndex) && !found; i++) {
            if ((tree[i] != null) && targetElement.equals(tree[i])) {
                found = true;
                result = tree[i];
                replace(i);
                count--;
            }
        }

        if (!found)
            throw new ElementNotFoundException("element not found in the binary tree");

        int temp = maxIndex;
        maxIndex = -1;
        for (int i = 0; i <= temp; i++)
            if (tree[i] != null)
                maxIndex = i;

        height = (int) (Math.log(maxIndex + 1) / Math.log(2)) + 1;
        return result;
    }

    /**
     * Removes all occurrences of the specified element from the tree.
     *
     * @param targetElement The element to be removed from the tree.
     * @throws ElementNotFoundException If the element is not found in the tree.
     * @throws EmptyCollectionException If the tree is empty.
     */
    @Override
    public void removeAllOccurrences(T targetElement) throws ElementNotFoundException, EmptyCollectionException {
        removeElement(targetElement);

        while (contains(targetElement))
            removeElement(targetElement);
    }

    /**
     * Removes and returns the smallest element in the tree.
     *
     * @return The smallest element in the tree.
     * @throws EmptyCollectionException If the tree is empty.
     */
    @Override
    public T removeMin() throws EmptyCollectionException {
        T result = null;

        if (isEmpty())
            throw new EmptyCollectionException("binary tree");
        else {
            int currentIndex = 1;
            int previousIndex = 0;
            while (tree[currentIndex] != null && currentIndex <= tree.length) {
                previousIndex = currentIndex;
                currentIndex = currentIndex * 2 + 1;
            } //while
            result = tree[previousIndex];
            replace(previousIndex);
        } //else

        count--;

        return result;
    }

    /**
     * Removes and returns the largest element in the tree.
     *
     * @return The largest element in the tree.
     * @throws EmptyCollectionException If the tree is empty.
     */
    @Override
    public T removeMax() throws EmptyCollectionException {
        T result = null;

        if (isEmpty())
            throw new EmptyCollectionException("binary tree");
        else {
            int currentIndex = 2;
            int previousIndex = 0;
            while (tree[currentIndex] != null && currentIndex <= maxIndex) {
                previousIndex = currentIndex;
                currentIndex = currentIndex * 2 + 2;
            }
            result = tree[previousIndex];
            replace(previousIndex);
        }
        count--;
        return result;
    }

    /**
     * Finds and returns the smallest element in the tree without removing it.
     *
     * @return The smallest element in the tree.
     * @throws EmptyCollectionException If the tree is empty.
     */
    @Override
    public T findMin() throws EmptyCollectionException {
        T result = null;

        if (isEmpty())
            throw new EmptyCollectionException("binary tree");
        else {
            int currentIndex = 0;
            while ((currentIndex * 2 + 1 <= maxIndex) && (tree[currentIndex * 2 + 1] != null))
                currentIndex = currentIndex * 2 + 1;
            result = tree[currentIndex];
        }
        return result;
    }

    /**
     * Finds and returns the largest element in the tree without removing it.
     *
     * @return The largest element in the tree.
     * @throws EmptyCollectionException If the tree is empty.
     */
    @Override
    public T findMax() throws EmptyCollectionException {
        T result = null;

        if (isEmpty())
            throw new EmptyCollectionException("binary tree");
        else {
            int currentIndex = 0;
            while ((currentIndex * 2 + 2 <= maxIndex) && (tree[currentIndex * 2 + 2] != null))
                currentIndex = currentIndex * 2 + 2;
            result = tree[currentIndex];
        }
        return result;
    }

    /**
     * Replaces a tree node at the specified position.
     *
     * @param targetIndex The index of the node to be replaced.
     * @throws EmptyCollectionException If the collection is empty.
     */
    protected void replace(int targetIndex) throws EmptyCollectionException {
        int currentIndex, parentIndex, temp, oldIndex, newIndex;
        ArrayUnorderedList<Integer> oldlist = new ArrayUnorderedList<Integer>();
        ArrayUnorderedList<Integer> newlist = new ArrayUnorderedList<Integer>();
        ArrayUnorderedList<Integer> templist = new ArrayUnorderedList<Integer>();
        Iterator<Integer> oldIt, newIt;

        // if target node has no children
        if ((targetIndex * 2 + 1 >= tree.length) || (targetIndex * 2 + 2 >= tree.length))
            tree[targetIndex] = null;
            // if target node has no children
        else if ((tree[targetIndex * 2 + 1] == null) && (tree[targetIndex * 2 + 2] == null))
            tree[targetIndex] = null;
            // if target node only has a left child
        else if ((tree[targetIndex * 2 + 1] != null) && (tree[targetIndex * 2 + 2] == null)) {
            // fill newlist
            currentIndex = targetIndex * 2 + 1;
            templist.addToRear(Integer.valueOf(currentIndex));
            while (!templist.isEmpty()) {
                currentIndex = ((Integer) templist.removeFirst()).intValue();
                newlist.addToRear(Integer.valueOf(currentIndex));
                if ((currentIndex * 2 + 2) <= (Math.pow(2, height) - 2)) {
                    templist.addToRear(Integer.valueOf(currentIndex * 2 + 1));
                    templist.addToRear(Integer.valueOf(currentIndex * 2 + 2));
                }
            }
            // fill oldlist
            currentIndex = targetIndex;
            templist.addToRear(Integer.valueOf(currentIndex));
            while (!templist.isEmpty()) {
                currentIndex = templist.removeFirst().intValue();
                oldlist.addToRear(Integer.valueOf(currentIndex));
                if ((currentIndex * 2 + 2) <= (Math.pow(2, height) - 2)) {
                    templist.addToRear(Integer.valueOf(currentIndex * 2 + 1));
                    templist.addToRear(Integer.valueOf(currentIndex * 2 + 2));
                }
            }
            // do replacement
            oldIt = oldlist.iterator();
            newIt = newlist.iterator();
            while (newIt.hasNext()) {
                oldIndex = oldIt.next();
                newIndex = newIt.next();
                tree[oldIndex] = tree[newIndex];
                tree[newIndex] = null;
            }
        }
        else if ((tree[targetIndex * 2 + 1] == null) && (tree[targetIndex * 2 + 2] != null)) {
            // fill newlist
            currentIndex = targetIndex * 2 + 2;
            templist.addToRear(Integer.valueOf(currentIndex));
            while (!templist.isEmpty()) {
                currentIndex = templist.removeFirst().intValue();
                newlist.addToRear(Integer.valueOf(currentIndex));
                if ((currentIndex * 2 + 2) <= (Math.pow(2, height) - 2)) {
                    templist.addToRear(Integer.valueOf(currentIndex * 2 + 1));
                    templist.addToRear(Integer.valueOf(currentIndex * 2 + 2));
                }
            }
            // fill oldlist
            currentIndex = targetIndex;
            templist.addToRear(Integer.valueOf(currentIndex));
            while (!templist.isEmpty()) {
                currentIndex = templist.removeFirst().intValue();
                oldlist.addToRear(Integer.valueOf(currentIndex));
                if ((currentIndex * 2 + 2) <= (Math.pow(2, height) - 2)) {
                    templist.addToRear(Integer.valueOf(currentIndex * 2 + 1));
                    templist.addToRear(Integer.valueOf(currentIndex * 2 + 2));
                }
            }
            // do replacement
            oldIt = oldlist.iterator();
            newIt = newlist.iterator();
            while (newIt.hasNext()) {
                oldIndex = oldIt.next();
                newIndex = newIt.next();
                tree[oldIndex] = tree[newIndex];
                tree[newIndex] = null;
            }
        }
        // target node has two children
        else {
            currentIndex = targetIndex * 2 + 2;

            while (tree[currentIndex * 2 + 1] != null) {
                currentIndex = currentIndex * 2 + 1;
            }
            tree[targetIndex] = tree[currentIndex];
            // the index of the root of the subtree to be replaced
            int currentRoot = currentIndex;
            // if currentIndex has a right child
            if (tree[currentRoot * 2 + 2] != null) {
                // fill newlist
                currentIndex = currentRoot * 2 + 2;
                templist.addToRear(Integer.valueOf(currentIndex));
                while (!templist.isEmpty()) {
                    currentIndex = (templist.removeFirst()).intValue();
                    newlist.addToRear(Integer.valueOf(currentIndex));
                    if ((currentIndex * 2 + 2) <= (Math.pow(2, height) - 2)) {
                        templist.addToRear(Integer.valueOf(currentIndex * 2 + 1));
                        templist.addToRear(Integer.valueOf(currentIndex * 2 + 2));
                    }
                }
                // fill oldlist
                currentIndex = currentRoot;
                templist.addToRear(Integer.valueOf(currentIndex));
                while (!templist.isEmpty()) {
                    currentIndex = (templist.removeFirst()).intValue();
                    oldlist.addToRear(Integer.valueOf(currentIndex));
                    if ((currentIndex * 2 + 2) <= (Math.pow(2, height) - 2)) {
                        templist.addToRear(Integer.valueOf(currentIndex * 2 + 1));
                        templist.addToRear(Integer.valueOf(currentIndex * 2 + 2));
                    }
                }
                // do replacement
                oldIt = oldlist.iterator();
                newIt = newlist.iterator();
                while (newIt.hasNext()) {
                    oldIndex = oldIt.next();
                    newIndex = newIt.next();
                    tree[oldIndex] = tree[newIndex];
                    tree[newIndex] = null;
                }
            } else
                tree[currentRoot] = null;
        }
    }

    /**
     * Returns a string representation of the tree in order.
     *
     * @return A string representation of the tree in order.
     */
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        inOrderTraversal(0, result);
        return result.toString();
    }

    /**
     * Performs an in-order traversal of the tree starting from the specified index.
     *
     * @param index  The starting index for the traversal.
     * @param result The StringBuilder to build the string representation.
     */
    private void inOrderTraversal(int index, StringBuilder result) {
        if (index <= maxIndex && tree[index] != null) {
            inOrderTraversal(index * 2 + 1, result);
            result.append(tree[index]).append(" ");
            inOrderTraversal(index * 2 + 2, result);
        }
    }
}
