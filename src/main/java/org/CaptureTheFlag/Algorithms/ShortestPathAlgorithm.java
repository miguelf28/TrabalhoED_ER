package org.CaptureTheFlag.Algorithms;

import org.CaptureTheFlag.Algorithms.Interface.IMovementAlgorithm;
import org.CaptureTheFlag.Models.Bots.Bot;
import org.CaptureTheFlag.Models.Location.Location;
import org.CaptureTheFlag.Models.Map.Map;
import org.CaptureTheFlag.Models.Player.Player;
import org.Estruturas.Exceptions.EmptyCollectionException;

import java.util.Iterator;

/**
 * The {@code ShortestPathAlgorithmI} class represents an implementation of the movement algorithm for bots in a Capture The Flag game.
 * This algorithm calculates the shortest path to move a bot from its current location to a specified destination.
 * The class implements the {@link IMovementAlgorithm} interface, providing the logic for the {@code move} method.
 *
 * @author 8190357 - Marcelo Barbosa
 * @author 8180325 - Miguel Fonseca
 */
public class ShortestPathAlgorithm implements IMovementAlgorithm {

    @Override
    public String getName() {
        return "ShortestPath";
    }

    /**
     * Calculates the shortest path to move the bot from its current location to the opponent's field.
     *
     * @param map      The map containing locations.
     * @param bot      The bot that is moving.
     * @param opponent The opponent player.
     * @return The next location for the bot to move to in order to reach the opponent's field.
     * @throws EmptyCollectionException if the collection is empty.
     */

    public Location move(Map<Location> map, Bot bot, Player opponent) throws EmptyCollectionException {
        Location currentLocation = bot.getActualPosition();
        Location opponentLocation = opponent.getFlagPosition();

        System.out.println("Localização Atual: " + currentLocation.getName());
        System.out.println("Localização da Bandeira do inimigo: " + opponentLocation.getName());

        Iterator<Location> iterator = map.iteratorShortestPath(currentLocation, opponentLocation);
        System.out.print("Caminho Shortest Path:");
        while (iterator.hasNext()){
            System.out.print(" - " + iterator.next().getName());
        }

        iterator = map.iteratorShortestPath(currentLocation, opponentLocation);
        while (iterator.hasNext()) {
            Location nextLocation = iterator.next();
            return nextLocation;
        }

        System.out.println("\nNão foi possível encontrar um caminho para o destino.");
        return currentLocation;
    }
}