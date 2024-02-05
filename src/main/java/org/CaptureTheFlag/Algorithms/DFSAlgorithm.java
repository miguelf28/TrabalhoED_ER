package org.CaptureTheFlag.Algorithms;

import org.CaptureTheFlag.Algorithms.Interface.IMovementAlgorithm;
import org.CaptureTheFlag.Models.Bots.Bot;
import org.CaptureTheFlag.Models.Location.Location;
import org.CaptureTheFlag.Models.Map.Map;
import org.CaptureTheFlag.Models.Player.Player;

import java.util.Iterator;

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
    public Location move(Map<Location> map, Bot bot, Player opponent) {
        Location currentLocation = bot.getActualPosition();
        Location opponentLocation = opponent.getFlagPosition();

        System.out.println("Localização Atual: " + currentLocation.getName());
        System.out.println("Localização Alvo: " + opponentLocation.getName());

        Iterator<Location> iterator = map.iteratorDFS(currentLocation);
        System.out.print("Caminho DFS:");
        while (iterator.hasNext()) {
            System.out.print(" - "+iterator.next().getName());
        }

        iterator = map.iteratorBFS(currentLocation);
        while (iterator.hasNext()) {
            Location nextLocation = iterator.next();
            return nextLocation;
        }

        System.out.println("\nNão foi possível encontrar um caminho para o destino.");
        return currentLocation;
    }

}