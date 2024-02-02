package org.CaptureTheFlag.Algorithms;

import org.CaptureTheFlag.Algorithms.Interface.IMovementAlgorithm;
//import org.CaptureTheFlag.Managements.GameManager;
import org.CaptureTheFlag.Models.Map.Map;
import org.CaptureTheFlag.Models.Bots.Bot;
import org.CaptureTheFlag.Models.Location.Location;
import org.CaptureTheFlag.Models.Player.Player;

import java.util.Iterator;
import java.util.Set;

/**
 * The {@code BFSAlgorithmI} class represents an implementation of the Breadth-First Search (BFS) movement algorithm for bots in a Capture The Flag game.
 * This algorithm explores all the vertices at the current level before moving on to the next level, attempting to reach the opponent's field.
 * The class implements the {@link IMovementAlgorithm} interface, providing the logic for the {@code move} method.
 *
 * @author 8190357 - Marcelo Barbosa
 * @author 8180325 - Miguel Fonseca
 */
public class BFSAlgorithm implements IMovementAlgorithm {

    @Override
    public String getName() {
        return "BFS";
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
    public Location move(Map<Location> map, Bot bot, Player opponent) {
        Set<Location> visitedLocations = bot.getVisitedLocations();
        Location currentLocation = bot.getActualPosition();
        Location opponentLocation = opponent.getFlagPosition();

        System.out.println("\nLocalização Atual: " + currentLocation.getName());
        System.out.println("Localização da Bandeira do inimigo: " + opponentLocation.getName());
        //System.out.println("Visitados: " + visitedLocations);

        Iterator<Location> iterator = map.iteratorBFS(currentLocation);
        System.out.print("Caminho BFS:");
        while (iterator.hasNext()) {
            System.out.print(" - "+iterator.next().getName());
        }
        iterator = map.iteratorBFS(currentLocation);
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
                    //visitedLocations.add(currentLocation);
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