package org.CaptureTheFlag.Models.Player;

import org.CaptureTheFlag.Models.Bots.Bot;
import org.CaptureTheFlag.Models.Location.Location;
import org.Estruturas.ArrayUnorderedList.ArrayUnorderedList;

/**
 * Represents a player in the Capture The Flag game.
 * <p>
 * Each player has a unique identifier, a player name, a list of bots associated with them,
 * and the current position of their flag.
 * </p>
 *
 * @author 8190357 - Marcelo Barbosa
 * @author 8180325 - Miguel Fonseca
 */
public class Player {
    private int id;
    private String playerName;
    private ArrayUnorderedList<Bot> bots;
    private Location flagPosition;
    private int lastMovedBotIndex;


    /**
     * Default constructor for the Player class.
     */
    public Player() {
    }

    /**
     * Parameterized constructor for the Player class.
     *
     * @param id          The unique identifier of the player.
     * @param playerName  The name of the player.
     */
    public Player(int id, String playerName) {
        this.id = id;
        this.playerName = playerName;
        this.bots = new ArrayUnorderedList<>();
    }

    /**
     * Retrieves the unique identifier of the player.
     *
     * @return The unique identifier of the player.
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the unique identifier of the player.
     *
     * @param id The unique identifier to set.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Retrieves the list of bots associated with the player.
     *
     * @return The list of bots associated with the player.
     */
    public ArrayUnorderedList<Bot> getBots() {
        return bots;
    }

    /**
     * Sets the list of bots associated with the player.
     *
     * @param bots The list of bots to set.
     */
    public void setBots(ArrayUnorderedList<Bot> bots) {
        this.bots = bots;
    }

    /**
     * Retrieves the current position of the player's flag.
     *
     * @return The current position of the player's flag.
     */
    public Location getFlagPosition() {
        return flagPosition;
    }

    /**
     * Sets the current position of the player's flag.
     *
     * @param flagPosition The flag position to set.
     */
    public void setFlagPosition(Location flagPosition) {
        this.flagPosition = flagPosition;
    }

    /**
     * Retrieves the name of the player.
     *
     * @return The name of the player.
     */
    public String getPlayerName() {
        return playerName;
    }

    /**
     * Sets the name of the player.
     *
     * @param playerName The name to set.
     */
    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    /**
     * Retrieves the index of the last moved bot for the current player.
     *
     * @return The index of the last moved bot.
     */
    public int getLastMovedBotIndex() {
        return lastMovedBotIndex;
    }

    /**
     * Sets the index of the last moved bot for the current player.
     *
     * @param lastMovedBotIndex The index to set.
     */
    public void setLastMovedBotIndex(int lastMovedBotIndex) {
        this.lastMovedBotIndex = lastMovedBotIndex;
    }


    /**
     * Generates a string representation of the Player object.
     *
     * @return A string representation of the Player object.
     */
    @Override
    public String toString() {
        return "--------- Player " + id + " ---------" +
                "\nPlayer Name = " + playerName +
                "\n--------- Bots ---------" + bots +
                "\nFlag Position = " + flagPosition +
                "\n-------------------------";
    }
}
