package org.CaptureTheFlag.Models.Map;

import org.CaptureTheFlag.Models.Location.Location;
import org.CaptureTheFlag.Models.Location.LocationNames;
import org.CaptureTheFlag.Models.Path.Path;
import org.Estruturas.ArrayUnorderedList.ArrayUnorderedList;
import org.Estruturas.Network.Network;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.util.HashSet;
import java.util.Set;

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
public class Map<T> extends Network<T> {
    private ArrayList<Path> paths = new ArrayList<>();
    private ArrayList<Path> pathsInverse = new ArrayList<>();
    private final Set<LocationNames> usedNames;
    Random random = new Random();


    /**
     * Constructs an empty map.
     */
    public Map() {
        super();
        this.usedNames = new HashSet<>();
    }

    /**
     * Retrieves the locations in the map.
     *
     * @return An array list of locations in the map.
     */
    public ArrayUnorderedList<Location> getLocations() {
        ArrayUnorderedList<Location> temp = new ArrayUnorderedList<>();
        for (T vertices : vertices) {
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
    private Location getLocationByName(String name) {
        for (T vertex : vertices) {
            Location location = (Location) vertex;
            if (location.getName().equals(name)) {
                return location;
            }
        }
        return null;
    }

    /**
     * Retrieves the network associated with the map.
     *
     * @return The network associated with the map.
     */
    public Network<T> getNetwork() {
        return this;
    }

    /**
     * Retrieves the index of a location in the map.
     *
     * @param location The location to find the index of.
     * @return The index of the location, or -1 if not found.
     */
    private int getIndexOfLocation(Location location) {
        for (int i = 0; i < vertices.length; i++) {
            if (vertices[i].equals(location)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Retrieves the paths in the map.
     *
     * @return An array list of paths in the map.
     */
    public ArrayList<Path> getPaths() {
        return paths;
    }

    /**
     * Retrieves the inverse paths in the map.
     *
     * @return An array list of inverse paths in the map.
     */
    public ArrayList<Path> getPathsInverse() {
        return pathsInverse;
    }


    /**
     * Chooses names for the locations based on the number of names needed.
     *
     * @param intNames The number of names to choose.
     * @return The chosen name.
     */
    private LocationNames chooseNames(int intNames) {
        LocationNames[] namesLocations = LocationNames.values();
        Set<LocationNames> availableNames = new HashSet<>(Set.of(namesLocations));
        availableNames.removeAll(usedNames);

        if (availableNames.isEmpty()) {
            return null;
        }
        LocationNames chosenName = availableNames.iterator().next();
        usedNames.add(chosenName);
        return chosenName;
    }

    /**
     * Generates a random map with the specified number of locations, bidirectional paths, and density.
     * This is one of the most important classes of this game, as it generates everything on the map
     *
     * @param numLocations       The number of locations to generate in the map.
     * @param bidirectionalPaths Flag indicating whether the paths should be bidirectional.
     * @param density            The density of the paths in the map.
     */
    public void generateMap(int numLocations, boolean bidirectionalPaths, int density) {
        for (int i = 0; i < numLocations; i++) {
            Location location = new Location(i + 1, chooseNames(i + 1).toString());
            addVertex((T) location);
        }

        for (int i = 0; i < numLocations; i++) {
            for (int j = i + 1; j < numLocations; j++) {
                if (random.nextInt(100) < density) {
                    try {
                        int distance = (int) (random.nextDouble() * 14 + 1);
                        addEdge(i, j, distance);
                        Path path = new Path((Location) vertices[i], (Location) vertices[j], distance);
                        paths.add(path);

                        if (bidirectionalPaths) {
                            addEdge(j, i, distance);
                            Path inverse = new Path((Location) vertices[j], (Location) vertices[i], distance);
                            pathsInverse.add(inverse);
                        }

                    } catch (IndexOutOfBoundsException e) {
                        System.out.println("Erro ao adicionar aresta: " + e.getMessage());
                    }
                }
            }
        }
    }

    /**
     * Prints the adjacency matrix representation of the map.
     */
    public void printAdjacencyMatrix() {
        System.out.println("Matriz de Adjacência:");
        System.out.print("        ");
        for (int i = 0; i < numVertices; i++) {
            System.out.printf("%-9s", ((Location) vertices[i]).getName());
        }
        System.out.println();

        boolean hasBidirectionalPaths = !pathsInverse.isEmpty();

        for (int i = 0; i < numVertices; i++) {
            System.out.printf("%-9s", ((Location) vertices[i]).getName());
            for (int j = 0; j < numVertices; j++) {
                if (hasBidirectionalPaths || i < j) {
                    if (this.cost[i][j] == Double.MAX_VALUE) {
                        System.out.printf("%-9s", "-");
                    } else {
                        System.out.printf("%-9d", (int) this.cost[i][j]);
                    }
                } else {
                    System.out.print("         ");
                }
            }
            System.out.println();
        }
    }
}
