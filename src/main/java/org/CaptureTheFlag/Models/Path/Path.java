package org.CaptureTheFlag.Models.Path;

import org.CaptureTheFlag.Models.Location.Location;

/**
 * Represents a path between two locations in the Capture The Flag game.
 * <p>
 * Each path consists of an origin location, a destination location, and the distance between them.
 * </p>
 *
 * @author 8190357 - Marcelo Barbosa
 * @author 8180325 - Miguel Fonseca
 */
public class Path {
    private Location origin;
    private Location destination;
    private int distance;

    /**
     * Constructs a Path object with the specified origin, destination, and distance.
     *
     * @param origin      The starting location of the path.
     * @param destination The ending location of the path.
     * @param distance    The distance between the origin and destination.
     */
    public Path(Location origin, Location destination, int distance) {
        this.origin = origin;
        this.destination = destination;
        this.distance = distance;
    }

    /**
     * Retrieves the origin location of the path.
     *
     * @return The origin location of the path.
     */
    public Location getOrigin() {
        return origin;
    }

    /**
     * Sets the origin location of the path.
     *
     * @param origin The origin location to set.
     */
    public void setOrigin(Location origin) {
        this.origin = origin;
    }

    /**
     * Retrieves the destination location of the path.
     *
     * @return The destination location of the path.
     */
    public Location getDestination() {
        return destination;
    }

    /**
     * Sets the destination location of the path.
     *
     * @param destination The destination location to set.
     */
    public void setDestination(Location destination) {
        this.destination = destination;
    }

    /**
     * Retrieves the distance between the origin and destination.
     *
     * @return The distance between the origin and destination.
     */
    public int getDistance() {
        return distance;
    }

    /**
     * Sets the distance between the origin and destination.
     *
     * @param distance The distance to set.
     */
    public void setDistance(int distance) {
        this.distance = distance;
    }

    /**
     * Generates a string representation of the Path object.
     *
     * @return A string representation of the Path object.
     */
    @Override
    public String toString() {
        return "Path{ origin=" + origin + ", destination=" + destination + ", distance=" + distance + '}';
    }
}
