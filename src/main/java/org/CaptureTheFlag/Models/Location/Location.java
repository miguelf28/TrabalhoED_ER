package org.CaptureTheFlag.Models.Location;

/**
 * The {@code Location} class represents a location on the game map in a Capture The Flag game.
 * Each location has a unique identifier (ID) and a name.
 *
 * @author 8190357 - Marcelo Barbosa
 * @author 8180325 - Miguel Fonseca
 */
public class Location {
    private int id;
    private String name;

    /**
     * Constructs a new {@code Location} object with the specified ID and name.
     *
     * @param id   The unique identifier for the location.
     * @param name The name of the location.
     */
    public Location(int id, String name) {
        this.id = id;
        this.name = name;
    }

    /**
     * Gets the unique identifier of the location.
     *
     * @return The ID of the location.
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the unique identifier of the location.
     *
     * @param id The new ID for the location.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets the name of the location.
     *
     * @return The name of the location.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the location.
     *
     * @param name The new name for the location.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns a string representation of the {@code Location} object.
     *
     * @return A string containing the name and ID of the location.
     */
    @Override
    public String toString() {
        return "{ name = " + name + " / id = " + id + " }";
    }
}
