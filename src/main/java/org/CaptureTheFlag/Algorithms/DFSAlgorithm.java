package org.CaptureTheFlag.Algorithms;

import org.CaptureTheFlag.Algorithms.Interface.IMovementAlgorithm;
import org.CaptureTheFlag.Management.GameManager;
import org.CaptureTheFlag.Map.Map;
import org.CaptureTheFlag.Models.Bots.Bot;
import org.CaptureTheFlag.Models.Location.Location;
import org.CaptureTheFlag.Models.Player.Player;
import org.Estruturas.Exceptions.EmptyCollectionException;

import java.util.Iterator;
import java.util.Set;

/**
 * The {@code DFSAlgorithmI} class represents an implementation of the Depth-First Search (DFS) movement algorithm for bots in a Capture The Flag game.
 * This algorithm explores as far as possible along each branch before backtracking, attempting to reach the opponent's field.
 * The class implements the {@link IMovementAlgorithm} interface, providing the logic for the {@code move} method.
 *
 * @author 8190357 - Marcelo Barbosa
 * @author 8180325 - Miguel Fonseca
 */
public class DFSAlgorithm implements IMovementAlgorithm {

    @Override
    public String getName() {
        return "DFS";
    }

    /**
     * Calculates the next move for the bot using the Breadth-First Search (BFS) algorithm.
     *
     * @param map      The map containing locations.
     * @param bot      The moving bot.
     * @param opponent The opponent player.
     * @return The next location for the bot to move to based on the BFS algorithm.
     */
    @Override
    public Location move(Map<Location> map, Bot bot, Player opponent) throws EmptyCollectionException {
        Set<Location> visitedLocations = bot.getVisitedLocations();
        Location currentLocation = bot.getActualPosition();
        Location opponentLocation = opponent.getFlagPosition();

        System.out.println("\nLocalização Atual: " + currentLocation.getName());
        System.out.println("Localização da Bandeira do inimigo: " + opponentLocation.getName());
        //System.out.println("Visitados: " + visitedLocations);

        Iterator<Location> iterator = map.iteratorDFS(currentLocation);
        System.out.print("Caminho DFS:");
        while (iterator.hasNext()) {
            System.out.print(" - "+iterator.next().getName());
        }

        iterator = map.iteratorDFS(currentLocation);
        iterator.next();

        System.out.print("\nCaminho Percorrido: ");
        while (iterator.hasNext()) {
            Location nextLocation = iterator.next();
            System.out.print(nextLocation);

            if (visitedLocations.contains(nextLocation)) {
                continue;
            }
            
            visitedLocations.add(currentLocation);
            if (GameManager.isValidMove(nextLocation, bot, opponent)) {

                if (nextLocation.equals(opponentLocation)) {
                    System.out.println("\nChegou à localização do oponente: " + opponentLocation.getName());
                    bot.setActualPosition(opponentLocation);
                    visitedLocations.add(nextLocation);
                    return opponentLocation;
                } else {
                    System.out.println("\nChegou à localização: " + nextLocation.getName());
                    visitedLocations.add(currentLocation);
                    bot.setActualPosition(nextLocation);
                    visitedLocations.add(nextLocation);
                }
                return nextLocation;
            } else {
                System.out.println("Fica na mesma localização: " + currentLocation.getName());
                return currentLocation;
            }
        }
        System.out.println("Não foi possível encontrar um caminho para o destino.");
        return currentLocation;
    }
}