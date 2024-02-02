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

}
