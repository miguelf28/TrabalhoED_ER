package org.CaptureTheFlag.Algorithms.Interface;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

public class MSTIterator<T> implements Iterator<T> {
    private T[] vertices;
    private boolean[] visited;
    private double[][] cost;
    private int[] parent;
    private Queue<T> queue;

    public MSTIterator(T[] vertices, boolean[] visited, double[][] cost, int[] parent) {
        this.vertices = vertices;
        this.visited = visited;
        this.cost = cost;
        this.parent = parent;
        this.queue = new LinkedList<>();
        initializeQueue();
    }

    private void initializeQueue() {
        for (int i = 0; i < vertices.length; i++) {
            if (parent[i] != -1) {
                queue.offer(vertices[i]);
            }
        }
    }

    @Override
    public boolean hasNext() {
        return !queue.isEmpty();
    }

    @Override
    public T next() {
        T nextVertex = queue.poll();
        visited[getIndex(nextVertex)] = true;
        int parentIndex = parent[getIndex(nextVertex)];
        if (parentIndex != -1 && !visited[parentIndex]) {
            queue.offer(vertices[parentIndex]);
        }
        return nextVertex;
    }

    private int getIndex(T vertex) {
        for (int i = 0; i < vertices.length; i++) {
            if (vertices[i].equals(vertex)) {
                return i;
            }
        }
        return -1;
    }
}