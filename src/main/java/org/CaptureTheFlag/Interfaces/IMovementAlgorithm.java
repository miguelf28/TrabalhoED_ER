package org.CaptureTheFlag.Interfaces;

import org.CaptureTheFlag.Map.Map;
import org.CaptureTheFlag.Models.Bots.Bot;
import org.CaptureTheFlag.Models.Location.Location;
import org.CaptureTheFlag.Models.Player.Player;
import org.Estruturas.Exceptions.EmptyCollectionException;

/**
 * The {@code IMovementAlgorithm} interface defines the contract for implementing movement algorithms for bots in a Capture The Flag game.
 * Classes that implement this interface must provide a concrete implementation of the {@code move} method,
 * which determines the next location to which a bot should move based on the current game state.
 *
 * @author 8190357 - Marcelo Barbosa
 * @author 8180325 - Miguel Fonseca
 */
public interface IMovementAlgorithm {
    /**
     * Gets the name of the movement algorithm.
     *
     * @return The name of the movement algorithm.
     */
    String getName();
    /**
     * Determines the next location to which a bot should move based on the current game state.
     *
     * @param map      The map containing locations.
     * @param bot      The bot that is moving.
     * @param opponent The opponent player.
     * @return The next location for the bot to move to.
     * @throws EmptyCollectionException if the collection is empty.
     */
    Location move(Map<Location> map, Bot bot, Player opponent) throws EmptyCollectionException;
}
