package org.Estruturas.Graph;

import java.util.Iterator;

/**
 * The GraphADT interface represents the basic operations that any graph
 * data structure should support.
 *
 * @param <T> the type of elements in the graph
 * @author 8190357 - Marcelo Barbosa
 * @author 8180325 - Miguel Fonseca
 */
public interface GraphADT<T> {
    /**
     * Adds a vertex to the graph.
     *
     * @param vertex the vertex to be added
     */
    public void addVertex(T vertex);

    /**
     * Removes a vertex from the graph.
     *
     * @param vertex the vertex to be removed
     */
    public void removeVertex(T vertex);

    /**
     * Adds an edge between two vertices in the graph.
     *
     * @param vertex1 the first vertex
     * @param vertex2 the second vertex
     */
    public void addEdge(T vertex1, T vertex2);

    /**
     * Removes the edge between two vertices in the graph.
     *
     * @param vertex1 the first vertex
     * @param vertex2 the second vertex
     */
    public void removeEdge(T vertex1, T vertex2);

    /**
     * Returns an iterator over the vertices of the graph in breadth-first order
     * starting from a specified vertex.
     *
     * @param startVertex the starting vertex for the traversal
     * @return an iterator over the vertices in breadth-first order
     */
    public Iterator iteratorBFS(T startVertex);

    /**
     * Returns an iterator over the vertices of the graph in depth-first order
     * starting from a specified vertex.
     *
     * @param startVertex the starting vertex for the traversal
     * @return an iterator over the vertices in depth-first order
     */
    public Iterator iteratorDFS(T startVertex);

    /**
     * Returns an iterator over the vertices of the shortest path from the start
     * vertex to the target vertex.
     *
     * @param startVertex  the starting vertex
     * @param targetVertex the target vertex
     * @return an iterator over the vertices of the shortest path
     */
    public Iterator iteratorShortestPath(T startVertex, T targetVertex);

    /**
     * Checks if the graph is empty.
     *
     * @return true if the graph is empty, false otherwise
     */
    public boolean isEmpty();

    /**
     * Checks if the graph is connected.
     *
     * @return true if the graph is connected, false otherwise
     */
    public boolean isConnected();

    /**
     * Returns the number of vertices in the graph.
     *
     * @return the number of vertices in the graph
     */
    public int size();

    /**
     * Returns a string representation of the graph.
     *
     * @return a string representation of the graph
     */
    public String toString();
}
