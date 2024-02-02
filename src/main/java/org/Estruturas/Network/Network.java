package org.Estruturas.Network;

import org.Estruturas.Exceptions.EmptyCollectionException;
import org.Estruturas.Graph.Graph;
import org.Estruturas.LinkedHeap.LinkedHeap;
import org.Estruturas.LinkedQueue.LinkedQueue;
import org.Estruturas.LinkedStack.LinkedStack;

/**
 * Represents a network, a subclass of the graph, with weighted edges.
 *
 * @param <T> the type of elements stored in the network
 * @author 8190357 - Marcelo Barbosa
 * @author 8180325 - Miguel Fonseca
 */
public class Network<T> extends Graph<T> implements NetworkADT<T> {
    /**
     * 2D array to store edge weights between vertices.
     */
    protected double[][] cost;

    /**
     * Constructs an empty network.
     */
    public Network() {
        super();
        this.cost = new double[this.DEFAULT_CAPACITY][this.DEFAULT_CAPACITY];
        for (int i = 0; i < this.DEFAULT_CAPACITY; i++) {
            for (int j = 0; j < this.DEFAULT_CAPACITY; j++) {
                this.cost[i][j] = Double.MAX_VALUE;
            }
        }
    }

    /**
     * Adds a weighted edge between two vertices with the specified weight.
     *
     * @param vertex1 the first vertex
     * @param vertex2 the second vertex
     * @param weight  the weight of the edge
     */
    @Override
    public void addEdge(T vertex1, T vertex2, double weight) {
        this.addEdge(super.getIndex(vertex1), super.getIndex(vertex2), weight);
    }

    /**
     * Adds a weighted edge between two vertices with the specified weight.
     *
     * @param index1 the index of the first vertex
     * @param index2 the index of the second vertex
     * @param weight the weight of the edge
     */
    public void addEdge(int index1, int index2, double weight) {
        if (super.indexIsValid(index1) && super.indexIsValid(index2)) {
            super.addEdge(index1, index2);
            this.cost[index1][index2] = weight;
            this.cost[index2][index1] = weight;
        }

    }

    /**
     * Gets the weight of the shortest path between two vertices.
     *
     * @param vertex1 the starting vertex
     * @param vertex2 the target vertex
     * @return the weight of the shortest path
     */
    @Override
    public double shortestPathWeight(T vertex1, T vertex2) {
        return shortestPathWeight(this.getIndex(vertex1), this.getIndex(vertex2));
    }

    /**
     * Gets the shortest path between two vertices as a stack of vertices.
     *
     * @param vertex1 the starting vertex
     * @param vertex2 the target vertex
     * @return a stack representing the shortest path
     */
    public LinkedStack<T> shortestPath(T vertex1, T vertex2) {
        return this.shortestPath(this.getIndex(vertex1), this.getIndex(vertex2));
    }

    /**
     * Gets the weight of the shortest path between two vertices.
     *
     * @param startIndex  the index of the starting vertex
     * @param targetIndex the index of the target vertex
     * @return the weight of the shortest path
     */
    protected double shortestPathWeight(int startIndex, int targetIndex) {

        double path_array[] = new double[this.size()]; // The output array. dist[i] will hold
        // the shortest distance from src to i

        // spt (shortest path set) contains vertices that have shortest path
        boolean sptSet[] = new boolean[this.size()];

        // Initially all the distances are INFINITE and stpSet[] is set to false
        for (int i = 0; i < this.size(); i++) {
            path_array[i] = Double.MAX_VALUE;
            sptSet[i] = false;
        }

        // Path between vertex and itself is always 0
        path_array[startIndex] = 0;
        // now find the shortest path for all vertices
        for (int count = 0; count < this.size() - 1; count++) {
            // call minDistance method to find the vertex with min distance
            int u = minDistance(path_array, sptSet);
            // the current vertex u is processed
            sptSet[u] = true;
            // process adjacent nodes of the current vertex
            for (int v = 0; v < this.size(); v++) // if vertex v not in sptset then update it
            {
                if (!sptSet[v] && this.adjMatrix[u][v] != false && path_array[u]
                        != Integer.MAX_VALUE && path_array[u]
                        + this.cost[u][v] < path_array[v]) {
                    path_array[v] = path_array[u] + this.cost[u][v];
                }
            }
        }

        return path_array[targetIndex];
    }

    /**
     * Gets the shortest path between two vertices as a stack of vertices.
     *
     * @param startIndex  the index of the starting vertex
     * @param targetIndex the index of the target vertex
     * @return a stack representing the shortest path
     */
    protected LinkedStack<T> shortestPath(int startIndex, int targetIndex) {
        double[] distance = new double[this.numVertices];
        int[] previous = new int[this.numVertices];
        LinkedQueue<Integer> queue = new LinkedQueue<>();
        LinkedStack<T> path = new LinkedStack<>();

        //prepare distance array
        for (int i = 0; i < this.numVertices; i++) {
            distance[i] = Double.MAX_VALUE;
            queue.enqueue(i);
            previous[i] = -1;
        }
        distance[startIndex] = 0;

        while (!queue.isEmpty()) {
            int u = 0;
            try {
                u = queue.dequeue();
            } catch (EmptyCollectionException ex) {
                System.out.println(ex);
            }

            //exit condition
            if (previous[u] != -1 || u == targetIndex) {
                while (u != -1) {
                    path.push(this.vertices[u]);
                    u = previous[u];
                }
                break;
            }

            //find the shortest vertex
            for (int i = 0; i < this.numVertices; i++) {
                if (this.cost[u][i] != Double.MAX_VALUE) {
                    double tempDist = distance[u] + this.cost[u][i];
                    if (tempDist < distance[i]) {
                        distance[i] = tempDist;
                        previous[i] = u;
                    }
                }
            }
        }
        return path;
    }

    /**
     * Finds the minimum spanning tree (MST) of the network.
     *
     * @return a network representing the MST
     */
    public Network mstNetwork() {
        int x, y;
        int index;
        double weight = 0;
        int[] edge = new int[2];
        LinkedHeap<Double> minHeap = new LinkedHeap<>();
        Network<T> resultGraph = new Network<>();
        if (this.isEmpty() || !this.isConnected()) {
            return resultGraph;
        }
        resultGraph.cost = new double[numVertices][numVertices];
        for (int i = 0; i < this.numVertices; i++) {
            for (int j = 0; j < this.numVertices; j++) {
                resultGraph.cost[i][j] = Double.MAX_VALUE;
            }
        }
        resultGraph.vertices = (T[]) (new Object[this.numVertices]);
        boolean[] visited = new boolean[this.numVertices];
        for (int i = 0; i < this.numVertices; i++) {
            visited[i] = false;
        }
        edge[0] = 0;
        resultGraph.vertices[0] = this.vertices[0];
        resultGraph.numVertices++;
        visited[0] = true;

        for (int i = 0; i < this.numVertices; i++) {
            if (this.adjMatrix[0][i] == true) {
                minHeap.addElement(this.cost[0][i]);
            }
        }
        while ((resultGraph.size() < this.size()) && !minHeap.isEmpty()) {
            do {
                try {
                    weight = (minHeap.removeMin());
                    edge = getEdgeWithWeightOf(weight, visited);
                } catch (EmptyCollectionException ex) {
                    System.out.println(ex);
                }
            } while (!indexIsValid(edge[0]) || !indexIsValid(edge[1]));
            x = edge[0];
            y = edge[1];
            if (!visited[x]) {
                index = x;
            } else {
                index = y;
            }

            resultGraph.vertices[index] = this.vertices[index];
            visited[index] = true;
            resultGraph.cost[x][y] = this.cost[x][y];
            resultGraph.cost[y][x] = this.cost[y][x];

            minHeap = new LinkedHeap();

            for (int i = 0; i < this.numVertices; i++) {
                if (!visited[i] && (this.cost[i][index] < Double.MAX_VALUE)) {
                    edge[0] = index;
                    edge[1] = i;
                    minHeap.addElement(this.cost[index][i]);
                }
            }
        }
        resultGraph.numVertices = this.numVertices;
        return resultGraph;
    }

    /**
     * Returns a string representation of the network.
     *
     * @return a string representation of the network
     */
    @Override
    public String toString() {
        String matrix = "";

        for (int i = 0; i < this.numVertices; i++) {
            matrix += i + " - " + this.vertices[i].toString() + "\n";
        }

        matrix += " |y ";
        for (int i = 0; i < this.numVertices; i++) {
            matrix += i + "    ";
        }

        matrix += "\nx+";

        for (int i = 0; i < this.numVertices; i++) {
            matrix += "-----";
        }

        for (int i = 0; i < this.numVertices; i++) {
            matrix += "\n" + i + "|";

            for (int j = 0; j < this.numVertices; j++) {
                if (this.cost[i][j] == Double.MAX_VALUE) {
                    matrix += "     ";
                } else {
                    matrix += " " + this.cost[i][j];
                }
            }

        }

        return matrix;
    }

    /**
     * Finds the index of the vertex with the minimum distance value,
     * from the set of vertices not yet included in the shortest path tree.
     *
     * @param path_array the array of distance values
     * @param sptSet    the set of vertices included in the shortest path tree
     * @return the index of the vertex with the minimum distance value
     */
    private int minDistance(double path_array[], boolean sptSet[]) {
        // Initialize min value
        double min = Double.MAX_VALUE;
        int min_index = -1;

        for (int v = 0; v < this.size(); v++) {
            if (sptSet[v] == false && path_array[v] <= min) {
                min = path_array[v];
                min_index = v;
            }
        }

        return min_index;
    }

    /**
     * Finds the indices of the vertices forming an edge with a specific weight,
     * where one vertex is visited, and the other is not.
     *
     * @param weight  the weight of the edge
     * @param visited an array indicating whether a vertex is visited or not
     * @return an array containing the indices of the vertices forming the edge
     */
    private int[] getEdgeWithWeightOf(double weight, boolean[] visited) {

        for (int i = 0; i < this.numVertices; i++) {
            if (visited[i]) {
                for (int j = 0; j < this.numVertices; j++) {
                    if (!visited[j]) {
                        if (this.cost[i][j] == weight) {
                            return new int[]{i, j};
                        }
                    }

                }
            }

        }
        return null;
    }
}