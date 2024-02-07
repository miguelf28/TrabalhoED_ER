package org.CaptureTheFlag.Models.Bots;

import org.CaptureTheFlag.Algorithms.BFSAlgorithm;
import org.CaptureTheFlag.Algorithms.DFSAlgorithm;
import org.CaptureTheFlag.Algorithms.MSTAlgorithm;
import org.CaptureTheFlag.Algorithms.ShortestPathAlgorithm;
import org.CaptureTheFlag.Algorithms.Interface.IMovementAlgorithm;
import org.CaptureTheFlag.Models.Location.Location;
import org.CaptureTheFlag.Models.Player.Player;
import org.Estruturas.ArrayList.ArrayList;

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
    private Location targetPosition;
    private IMovementAlgorithm IMovementAlgorithm;
    private Player owner;
    private boolean carryingFlag = false;
    private boolean movedThisRound = false;
    ArrayList<Location> visitedLocations = new ArrayList<>();


    /**
     * Constructs a new {@code Bot} object with the specified ID.
     *
     * @param id The unique identifier for the bot.
     */
    public Bot(int id) {
        this.id = id;
        visitedLocations = new ArrayList<>();
    }

    /**
     * Constructs a new {@code Bot} object with the specified ID and actual position.
     *
     * @param id             The unique identifier for the bot.
     * @param actualPosition The initial actual position of the bot.
     */
    public Bot(int id, Location actualPosition) {
        this.id = id;
        this.actualPosition = actualPosition;
    }

    public Bot() {
        visitedLocations = new ArrayList<>();
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
     * @param id                 The unique identifier for the bot.
     * @param actualPosition     The initial actual position of the bot.
     * @param IMovementAlgorithm The movement algorithm assigned to the bot.
     */
    public Bot(int id, Location actualPosition, IMovementAlgorithm IMovementAlgorithm) {
        this.id = id;
        this.actualPosition = actualPosition;
        this.IMovementAlgorithm = IMovementAlgorithm;
        this.visitedLocations = new ArrayList<>();
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

    public Location getTargetPosition() {
        return targetPosition;
    }

    public void setTargetPosition(Location targetPosition) {
        this.targetPosition = targetPosition;
    }

    public boolean isCarryingFlag() {
        return carryingFlag;
    }

    public void setCarryingFlag(boolean carryingFlag) {
        this.carryingFlag = carryingFlag;
    }

    public boolean hasMovedThisRound() {
        return movedThisRound;
    }

    public void setMovedThisRound(boolean moved) {
        this.movedThisRound = moved;
    }

    public Location returnFlagToBase(Location playerBase) {
        Location returnPosition = null;
        if (isCarryingFlag()) {
            returnPosition = playerBase;
            setCarryingFlag(false);
        }
        return returnPosition;
    }

    public ArrayList<Location> getVisitedLocations() {
        return visitedLocations;
    }

    public void setVisitedLocations(ArrayList<Location> visitedLocations) {
        this.visitedLocations = visitedLocations;
    }

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
        } else if (IMovementAlgorithm instanceof MSTAlgorithm) {
            return "MST Algorithm";
        } else {
            return "Unknown Algorithm";
        }
    }

    /**
     * Returns a string representation of the {@code Bot} object, including its ID, movement algorithm type,
     * and actual position.
     *
     * @return A string containing information about the bot.
     */
    @Override
    public String toString() {
        return "\nBot " + id +
                " / Movement Algorithm = " + getAlgorithmType() +
                " / Actual Bot Position = " + actualPosition;
    }
}