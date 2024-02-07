package org.Estruturas.Graph;

import org.Estruturas.ArrayUnorderedList.ArrayUnorderedList;
import org.Estruturas.Exceptions.EmptyCollectionException;
import org.Estruturas.LinkedQueue.LinkedQueue;
import org.Estruturas.LinkedStack.LinkedStack;

import java.util.Iterator;

/**
 * Represents an implementation of a graph using an adjacency matrix.
 *
 * @param <T> the type of elements in the graph
 * @author 8190357 - Marcelo Barbosa
 * @author 8180325 - Miguel Fonseca
 */
public class Graph<T> implements GraphADT<T> {
    protected final int DEFAULT_CAPACITY = 100;
    protected int numVertices; // number of vertices in the graph
    protected boolean[][] adjMatrix; // adjacency matrix
    protected T[] vertices; // values of vertices

    /**
     * Default constructor to create a graph with the default capacity.
     */
    public Graph() {
        numVertices = 0;
        this.adjMatrix = new boolean[DEFAULT_CAPACITY][DEFAULT_CAPACITY];
        this.vertices = (T[]) new Object[DEFAULT_CAPACITY];
    }

    /**
     * Adds a new vertex to the graph.
     *
     * @param vertex the vertex to be added
     */
    @Override
    public void addVertex(T vertex) {
        if (this.numVertices == this.vertices.length) {
            expandCapacity();
        }
        this.vertices[this.numVertices] = vertex;
        for (int i = 0; i <= this.numVertices; i++) {
            this.adjMatrix[this.numVertices][i] = false;
            this.adjMatrix[i][this.numVertices] = false;
        }
        this.numVertices++;
    }

    /**
     * Removes a vertex from the graph.
     *
     * @param vertex the vertex to be removed
     */
    @Override
    public void removeVertex(T vertex) {
        int index = this.getIndex(vertex);
        int i;

        if (this.indexIsValid(index)) {

            while (index < this.numVertices) {

                // shifting the rows to left side
                for (i = 0; i < this.numVertices; ++i) {
                    this.adjMatrix[i][index] = this.adjMatrix[i][index + 1];
                }

                // shifting the columns upwards
                for (i = 0; i < this.numVertices; ++i) {
                    this.adjMatrix[index][i] = this.adjMatrix[index + 1][i];
                }

                index++;
            }

            this.numVertices--;
        }
    }

    /**
     * Adds an edge between two vertices in the graph.
     *
     * @param vertex1 the first vertex
     * @param vertex2 the second vertex
     */
    @Override
    public void addEdge(T vertex1, T vertex2) {
        this.addEdge(getIndex(vertex1), getIndex(vertex2));
    }

    /**
     * This method adds an edge between two vertices in the graph. It sets the
     * corresponding positions in the adjacency matrix to true, indicating the
     * presence of an edge between the vertices.
     *
     * @param index1 the index of the first vertex
     * @param index2 the index of the second vertex
     */
    protected void addEdge(int index1, int index2) {
        if (indexIsValid(index1) && indexIsValid(index2)) {
            this.adjMatrix[index1][index2] = true;
            this.adjMatrix[index2][index1] = true;
        }
    }

    /**
     * Removes an edge between two vertices in the graph.
     *
     * @param vertex1 the first vertex
     * @param vertex2 the second vertex
     */
    @Override
    public void removeEdge(T vertex1, T vertex2) {
        int index1 = this.getIndex(vertex1);
        int index2 = this.getIndex(vertex2);

        if (this.indexIsValid(index1) && this.indexIsValid(index2)) {
            this.adjMatrix[index1][index2] = false;
            this.adjMatrix[index2][index1] = false;
        }
    }

    /**
     * This method returns an iterator that performs a breadth-first traversal
     * starting at the specified vertex. It uses a queue to traverse the graph
     * in breadth-first order and maintains a boolean array to mark visited vertices.
     *
     * @param startVertex the starting vertex for the traversal
     * @return an iterator for the breadth-first traversal
     */
    @Override
    public Iterator iteratorBFS(T startVertex) {
        return this.iteratorBFS(this.getIndex(startVertex));
    }

    /**
     * This method returns an iterator that performs a breadth-first traversal
     * starting at the vertex with the specified index. It uses a queue to traverse the graph
     * in breadth-first order and maintains a boolean array to mark visited vertices.
     *
     * @param startIndex the index of the starting vertex for the traversal
     * @return an iterator for the breadth-first traversal
     */
    protected Iterator<T> iteratorBFS(int startIndex) {
        Integer x = null;
        LinkedQueue<Integer> traversalQueue = new LinkedQueue<>();
        ArrayUnorderedList<T> resultList = new ArrayUnorderedList<>();

        if (!indexIsValid(startIndex)) {
            return resultList.iterator();
        }

        boolean[] visited = new boolean[this.numVertices];

        for (int i = 0; i < this.numVertices; i++) {
            visited[i] = false;
        }

        traversalQueue.enqueue(startIndex);
        visited[startIndex] = true;

        while (!traversalQueue.isEmpty()) {
            try {
                x = traversalQueue.dequeue();
            } catch (EmptyCollectionException ex) {
                System.out.println(ex);
            }
            resultList.addToRear(this.vertices[x]);

            for (int i = 0; i < this.numVertices; i++) {
                if (this.adjMatrix[x][i] && !visited[i]) {
                    traversalQueue.enqueue(i);
                    visited[i] = true;
                }
            }
        }
        return resultList.iterator();
    }

    /**
     * This method returns an iterator that performs a depth-first traversal
     * starting at the specified vertex. It uses a stack to traverse the graph
     * in depth-first order and maintains a boolean array to mark visited vertices.
     *
     * @param startVertex the starting vertex for the traversal
     * @return an iterator for the depth-first traversal
     */
    @Override
    public Iterator iteratorDFS(T startVertex) {
        return this.iteratorDFS(this.getIndex(startVertex));
    }

    /**
     * This method returns an iterator that performs a depth-first traversal
     * starting at the vertex with the specified index. It uses a stack to traverse the graph
     * in depth-first order and maintains a boolean array to mark visited vertices.
     *
     * @param startIndex the index of the starting vertex for the traversal
     * @return an iterator for the depth-first traversal
     */
    protected Iterator<T> iteratorDFS(int startIndex) {
        Integer x = null;
        boolean found;
        LinkedStack<Integer> traversalStack = new LinkedStack<>();
        ArrayUnorderedList<T> resultList = new ArrayUnorderedList<>();
        boolean[] visited = new boolean[this.numVertices];

        if (!indexIsValid(startIndex)) {
            return resultList.iterator();
        }

        for (int i = 0; i < this.numVertices; i++) {
            visited[i] = false;
        }

        traversalStack.push(startIndex);
        resultList.addToRear(this.vertices[startIndex]);
        visited[startIndex] = true;

        while (!traversalStack.isEmpty()) {
            try {
                x = traversalStack.peek();
            } catch (EmptyCollectionException e) {
                throw new RuntimeException(e);
            }
            found = false;

            for (int i = 0; (i < this.numVertices) && !found; i++) {
                if (this.adjMatrix[x][i] && !visited[i]) {
                    traversalStack.push(i);
                    resultList.addToRear(this.vertices[i]);
                    visited[i] = true;
                    found = true;
                }
            }
            if (!found && !traversalStack.isEmpty()) {
                try {
                    traversalStack.pop();
                } catch (EmptyCollectionException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return resultList.iterator();
    }

    /**
     * Returns an iterator that performs a traversal to find the
     * shortest path from the start vertex to the target vertex.
     *
     * @param startVertex  the starting vertex for the traversal
     * @param targetVertex the target vertex to reach
     * @return an iterator for the shortest path traversal
     */
    @Override
    public Iterator iteratorShortestPath(T startVertex, T targetVertex) {
        return this.iteratorShortestPath(this.getIndex(startVertex), this.getIndex(targetVertex));
    }

    /**
     * Returns an iterator over the vertices of the shortest path from the start
     * vertex to the target vertex, using breadth-first search.
     *
     * @param startIndex the index of the start vertex
     * @param endIndex   the index of the target vertex
     * @return an iterator over the vertices of the shortest path
     */
    protected Iterator iteratorShortestPath(int startIndex, int endIndex) {
        Integer x = null;
        LinkedQueue<Integer> traversalQueue = new LinkedQueue<>();
        ArrayUnorderedList<T> resultList = new ArrayUnorderedList<>();
        int[] res = new int[this.numVertices];
        boolean found = false;

        if (!indexIsValid(startIndex)) {
            return resultList.iterator();
        }

        boolean[] visited = new boolean[this.numVertices];

        for (int i = 0; i < this.numVertices; i++) {
            visited[i] = false;
        }

        traversalQueue.enqueue(startIndex);
        visited[startIndex] = true;

        while (!traversalQueue.isEmpty() && !found) {
            try {
                x = traversalQueue.dequeue();
            } catch (EmptyCollectionException e) {
                throw new RuntimeException(e);
            }

            for (int i = 0; i < this.numVertices; i++) {
                if (this.adjMatrix[x][i] && !visited[i]) {
                    traversalQueue.enqueue(i);
                    res[i] = x;
                    visited[i] = true;
                    if (i == endIndex) {
                        found = true;
                        break;
                    }
                }
            }
        }
        int current = endIndex;

        if (found) {
            while (current != startIndex) {
                resultList.addToFront(this.vertices[current]);
                current = res[current];
            }
            resultList.addToFront(this.vertices[current]);
        } else {
            resultList.addToFront(this.vertices[startIndex]);
        }

        return resultList.iterator();
    }

    /**
     * Checks if the graph is empty.
     *
     * @return true if the graph is empty, false otherwise
     */
    @Override
    public boolean isEmpty() {
        return this.numVertices == 0;
    }

    /**
     * Checks if the graph is connected.
     *
     * @return true if the graph is connected, false otherwise
     */
    @Override
    public boolean isConnected() {
        Iterator iter = this.iteratorBFS(0);
        int total = 0;

        while (iter.hasNext()) {
            total++;
            iter.next();
        }

        return total == this.numVertices;
    }

    /**
     * Returns the number of vertices in the graph.
     *
     * @return the number of vertices in the graph
     */
    @Override
    public int size() {
        return this.numVertices;
    }

    /**
     * Returns a string representation of the graph.
     *
     * @return a string representation of the graph
     */
    public String toString() {
        String matrix = "";

        matrix += " |y";
        for (int i = 0; i < this.numVertices; i++) {
            matrix += " " + i;
        }

        matrix += "\nx+";

        for (int i = 0; i < this.numVertices; i++) {
            matrix += "--";
        }

        for (int i = 0; i < this.numVertices; i++) {
            matrix += "\n" + i + "|";
            for (int j = 0; j < this.numVertices; j++) {
                if (this.adjMatrix[i][j]) {
                    matrix += " 1";
                } else {
                    matrix += " 0";
                }
            }
        }
        return matrix;
    }

    /**
     * Expands the capacity of the graph.
     */
    protected void expandCapacity() {
        T[] newVertices = (T[]) (new Object[this.vertices.length * 2]);
        boolean[][] newMatrix = new boolean[this.DEFAULT_CAPACITY][this.DEFAULT_CAPACITY];

        System.arraycopy(this.vertices, 0, newVertices, 0, this.numVertices);
        System.arraycopy(this.adjMatrix, 0, newMatrix, 0, this.numVertices);

        this.vertices = newVertices;
        this.adjMatrix = newMatrix;
    }

    /**
     * Returns the index of a given vertex.
     *
     * @param vertex1 the vertex to find the index for
     * @return the index of the vertex, or an invalid index if not found
     */
    protected int getIndex(T vertex1) {
        int index = this.numVertices; //invalid index

        for (int i = 0; i < this.numVertices; i++) {
            if (this.vertices[i].equals(vertex1)) {
                index = i;
                break;
            }
        }

        return index;
    }

    /**
     * Checks if a given index is valid.
     *
     * @param index the index to check
     * @return true if the index is valid, false otherwise
     */
    protected boolean indexIsValid(int index) {
        return (index >= 0 && index < this.numVertices);
    }

    /**
     * Returns an iterator over the vertices in the graph.
     *
     * @return an iterator over the vertices in the graph
     */
    public Iterator<T> vertices() {
        ArrayUnorderedList<T> list = new ArrayUnorderedList<>();

        for (int i = 0; i < this.numVertices; i++) {
            list.addToRear(this.vertices[i]);
        }

        return list.iterator();
    }
}