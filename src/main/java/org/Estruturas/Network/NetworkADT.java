package org.Estruturas.Network;

import org.Estruturas.Graph.GraphADT;

/**
 * NetworkADT represents the interface for a network, a special type of graph
 * where edges have associated weights.
 *
 * @param <T> the type of the elements stored in the network
 * @author 8190357 - Marcelo Barbosa
 * @author 8180325 - Miguel Fonseca
 */
public interface NetworkADT<T> extends GraphADT<T> {

    /**
     * Adds an edge between two vertices in the network with the specified weight.
     *
     * @param vertex1 the first vertex
     * @param vertex2 the second vertex
     * @param weight  the weight of the edge
     */
    public void addEdge (T vertex1, T vertex2, double weight);

    /**
     * Computes and returns the weight of the shortest path between two vertices
     * in the network.
     *
     * @param vertex1 the starting vertex
     * @param vertex2 the target vertex
     * @return the weight of the shortest path between the two vertices
     */
    public double shortestPathWeight(T vertex1, T vertex2);
}

