package org.CaptureTheFlag.Algorithms;

import org.CaptureTheFlag.Algorithms.Interface.IMovementAlgorithm;
//import org.CaptureTheFlag.Managements.GameManager;
import org.CaptureTheFlag.Models.Map.Map;
import org.CaptureTheFlag.Models.Bots.Bot;
import org.CaptureTheFlag.Models.Location.Location;
import org.CaptureTheFlag.Models.Player.Player;
import org.Estruturas.Exceptions.EmptyCollectionException;

import java.util.Iterator;
import java.util.Set;

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

        //Set<Location> visitedLocations = bot.getVisitedLocations();
        Location currentLocation = bot.getActualPosition();
        Location opponentLocation = opponent.getFlagPosition();

        System.out.println("\nLocalização Atual: " + currentLocation.getName());
        System.out.println("Localização da Bandeira do inimigo: " + opponentLocation.getName());
        //System.out.println("Visitados: " + visitedLocations);

        Iterator<Location> iterator = map.iteratorShortestPath(currentLocation, opponentLocation);
        System.out.print("Caminho Shortest Path:");
        while (iterator.hasNext()){
            System.out.print(" - " + iterator.next().getName());
        }
        iterator = map.iteratorShortestPath(currentLocation, opponentLocation);
        Location nextLocation = null;
       //visitedLocations.add(currentLocation);

        while (iterator.hasNext()) {
            nextLocation = iterator.next();
            /*
            if (!visitedLocations.contains(nextLocation)) {
                if (GameManager.isValidMove(nextLocation, bot, opponent)) {
                    if (nextLocation.equals(opponentLocation)) {
                        System.out.println("\nChegou à localização do oponente: " + opponentLocation.getName());
                        bot.setActualPosition(opponentLocation);
                        visitedLocations.add(opponentLocation);
                        return opponentLocation;
                    } else {
                        System.out.println("\nChegou à localização: " + nextLocation.getName());
                        bot.setActualPosition(nextLocation);
                        visitedLocations.add(nextLocation);
                        return nextLocation;
                    }
                } else {
                    System.out.println("\nMovimento para " + nextLocation.getName() + " não é possível.");
                    return currentLocation;
                }
            }*/
        }
        System.out.println("\nNão foi possível encontrar um caminho para o destino.");
        return currentLocation;


    }


}