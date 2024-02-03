package org.CaptureTheFlag.Models.Map;

import org.CaptureTheFlag.Models.Location.Location;
import org.CaptureTheFlag.Models.Path.Path;
import org.Estruturas.ArrayUnorderedList.ArrayUnorderedList;
import org.Estruturas.Network.Network;

/**
 * Represents a map in the Capture The Flag game.
 * <p>
 * The map is a network of locations connected by paths. It can be imported from a JSON file or generated randomly.
 * </p>
 *
 * @param <T> the type of elements stored in the map (assumed to be of type Location)
 * @author 8190357 - Marcelo Barbosa
 * @author 8180325 - Miguel Fonseca
 */
public class Map<T extends Location> extends Network<T> {

    /**
     * Constructs an empty map.
     */
    public Map() {
        super();
        vertices = (T[]) new Location[this.DEFAULT_CAPACITY];
    }

    /**
     * Retrieves the locations in the map.
     *
     * @return An array list of locations in the map.
     */
    public ArrayUnorderedList<Location> getLocations() {
        ArrayUnorderedList<Location> temp = new ArrayUnorderedList<>();
        for (Location vertices : vertices) {
            if (vertices != null) {
                temp.addToRear((Location) vertices);
            }
        }
        return temp;
    }

    /**
     * Retrieves the location with the specified name.
     *
     * @param name The name of the location to retrieve.
     * @return The location with the specified name, or null if not found.
     */
    public Location getLocationByName(String name) {
        for (Location vertex : vertices) {
            Location location = (Location) vertex;
            if (location.getName().equals(name)) {
                return location;
            }
        }
        return null;
    }

    /**
     * Retrieves the index of a location in the map.
     *
     * @param location The location to find the index of.
     * @return The index of the location, or -1 if not found.
     */
    public int getIndexOfLocation(Location location) {
        for (int i = 0; i < vertices.length; i++) {
            if (vertices[i].equals(location)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Retrieves the vertices in the map.
     *
     * @return An array list of vertices in the map.
     */
    public Location[] getVertices() {
        Location[] locations = (Location[]) new Location[numVertices];
        System.arraycopy(vertices, 0, locations, 0, numVertices);
        return locations;
    }

    public int getNumVertices(){
        return this.numVertices;
    }

    public double[][] getCost(){
        return this.cost;
    }

    /**
     * Retorna os caminhos (edges) no mapa.
     *
     * @return Um array não ordenado de caminhos.
     */
    public ArrayUnorderedList<Path> getEdges() {
        ArrayUnorderedList<Path> edgesList = new ArrayUnorderedList<>();

        // Percorra a matriz de adjacência para encontrar os caminhos
        for (int i = 0; i < numVertices; i++) {
            for (int j = 0; j < numVertices; j++) {
                if (cost[i][j] != Double.MAX_VALUE) {
                    edgesList.addToRear(new Path(vertices[i], vertices[j], (int) cost[i][j]));
                }
            }
        }

        return edgesList;
    }


    /**
     * Retrieves the adjacent vertices of a given location.
     *
     * @param location The location for which to retrieve the adjacent vertices.
     * @return An array list of adjacent vertices of the given location.
     */
    public ArrayUnorderedList<Location> getAdjacentVertices(Location location) {
        ArrayUnorderedList<Location> adjacentVertices = new ArrayUnorderedList<>();

        int locationIndex = getIndexOfLocation(location);
        if (locationIndex != -1) {
            for (int i = 0; i < numVertices; i++) {
                if (cost[locationIndex][i] != Double.MAX_VALUE && !vertices[i].equals(location)) {
                    adjacentVertices.addToRear(vertices[i]);
                }
            }
        }
        return adjacentVertices;
    }
}
