package org.CaptureTheFlag.Models.Bots;

import org.CaptureTheFlag.Algorithms.BFSAlgorithm;
import org.CaptureTheFlag.Algorithms.DFSAlgorithm;
import org.CaptureTheFlag.Algorithms.ShortestPathAlgorithm;
import org.CaptureTheFlag.Algorithms.Interface.IMovementAlgorithm;
import org.CaptureTheFlag.Models.Location.Location;
import org.CaptureTheFlag.Models.Player.Player;

/**
 * The {@code Bot} class represents a bot in a Capture The Flag game. Bots have an ID, an actual position on the map,
 * a movement algorithm, and an owner player. They can move based on their assigned movement algorithm.
 * The class also keeps track of visited locations and provides methods to check algorithm types and determine if the bot
 * has reached the opponent's field.
 *
 * @author 8190357 - Marcelo Barbosa
 * @author 8180325 - Miguel Fonseca
 */
public class Bot {
    private int id;
    private Location actualPosition;
    private IMovementAlgorithm IMovementAlgorithm;
    private Player owner;


    /**
     * Gets the player who owns the bot.
     *
     * @return The owner player of the bot.
     */
    public Player getOwner() {
        return owner;
    }

    /**
     * Sets the player who owns the bot.
     *
     * @param owner The new owner player for the bot.
     */
    public void setOwner(Player owner) {
        this.owner = owner;
    }

    /**
     * Constructs a new {@code Bot} object with the specified ID.
     *
     * @param id The unique identifier for the bot.
     */
    public Bot(int id) {
        this.id = id;
    }

    /**
     * Constructs a new {@code Bot} object with the specified ID and actual position.
     *
     * @param id              The unique identifier for the bot.
     * @param actualPosition The initial actual position of the bot.
     */
    public Bot(int id, Location actualPosition) {
        this.id = id;
        this.actualPosition = actualPosition;
    }

    /**
     * Constructs a new {@code Bot} object with the specified owner player.
     *
     * @param owner The player who owns the bot.
     */
    public Bot(Player owner) {
        this.owner = owner;
    }

    /**
     * Constructs a new {@code Bot} object with the specified ID, actual position, and movement algorithm.
     *
     * @param id                The unique identifier for the bot.
     * @param actualPosition   The initial actual position of the bot.
     * @param IMovementAlgorithm The movement algorithm assigned to the bot.
     */
    public Bot(int id, Location actualPosition, IMovementAlgorithm IMovementAlgorithm) {
        this.id = id;
        this.actualPosition = actualPosition;
        this.IMovementAlgorithm = IMovementAlgorithm;
    }

    /**
     * Gets the unique identifier of the bot.
     *
     * @return The ID of the bot.
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the unique identifier of the bot.
     *
     * @param id The new ID for the bot.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets the actual position of the bot on the map.
     *
     * @return The actual position of the bot.
     */
    public Location getActualPosition() {
        return actualPosition;
    }

    /**
     * Sets the actual position of the bot on the map.
     *
     * @param actualPosition The new actual position for the bot.
     */
    public void setActualPosition(Location actualPosition) {
        this.actualPosition = actualPosition;
    }

    /**
     * Gets the movement algorithm assigned to the bot.
     *
     * @return The movement algorithm assigned to the bot.
     */
    public IMovementAlgorithm getMovementAlgorithm() {
        return IMovementAlgorithm;
    }

    /**
     * Sets the movement algorithm for the bot.
     *
     * @param IMovementAlgorithm The new movement algorithm for the bot.
     */
    public void setMovementAlgorithm(IMovementAlgorithm IMovementAlgorithm) {
        this.IMovementAlgorithm = IMovementAlgorithm;
    }

    /**
     * Gets the set of visited locations by the bot.
     *
     * @return The set of visited locations.
     */

    /**
     * Gets the type of movement algorithm assigned to the bot.
     *
     * @return A string representing the type of movement algorithm.
     */
    public String getAlgorithmType() {
        if (IMovementAlgorithm instanceof ShortestPathAlgorithm) {
            return "Shortest Path Algorithm";
        } else if (IMovementAlgorithm instanceof BFSAlgorithm) {
            return "BFS Algorithm";
        } else if (IMovementAlgorithm instanceof DFSAlgorithm) {
            return "DFS Algorithm";
        } else {
            return "Unknown Algorithm";
        }
    }

    /**
     * Checks if the bot has reached the opponent's field.
     *
     * @param opponentPlayer The opponent player.
     * @return {@code true} if the bot has reached the opponent's field, {@code false} otherwise.
     */
    public boolean hasReachedOpponentField(Player opponentPlayer) {
        Location botPosition = getActualPosition();
        Location opponentField = opponentPlayer.getFlagPosition();
        return botPosition.equals(opponentField);
    }

    /**
     * Returns a string representation of the {@code Bot} object, including its ID, movement algorithm type,
     * and actual position.
     *
     * @return A string containing information about the bot.
     */
    @Override
    public String toString() {
        return  "\nBot "+ id +
                " / Movement Algorithm = " + getAlgorithmType() +
                " / Actual Bot Position = " + actualPosition ;
    }
}