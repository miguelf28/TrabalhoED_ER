package org.CaptureTheFlag.Algorithms;

import org.CaptureTheFlag.Algorithms.Interface.IMovementAlgorithm;
import org.CaptureTheFlag.Models.Bots.Bot;
import org.CaptureTheFlag.Models.Location.Location;
import org.CaptureTheFlag.Models.Map.Map;
import org.CaptureTheFlag.Models.Player.Player;
import org.Estruturas.Network.Network;

/**
 * MSTAlgorithm implements the IMovementAlgorithm interface to provide movement strategies
 * <p>
 * based on the Minimum Spanning Tree (MST) algorithm.
 */
public class MSTAlgorithm implements IMovementAlgorithm {

    /**
     * Retrieves the name of the movement algorithm.
     *
     * @return The name of the algorithm, which is "Minimum Spanning Tree".
     */
    @Override
    public String getName() {
        return "Minimum Spanning Tree";
    }

    /**
     * Determines the next location to move to based on the Minimum Spanning Tree algorithm.
     *
     * @param map      The map containing locations and connections.
     * @param bot      The bot whose movement is being determined.
     * @param opponent The opponent player.
     * @return The next location the bot should move to.
     */
    @Override
    public Location move(Map<Location> map, Bot bot, Player opponent) {
        Location currentLocation = bot.getActualPosition();
        Location opponentLocation = opponent.getFlagPosition();

        System.out.println("Current Location: " + currentLocation.getName());
        System.out.println("Target Location: " + opponentLocation.getName());

        Network<Location> mst = map.mstNetwork();

        if (mst == null) {
            System.out.println("The MST was not properly created.");
            return null;
        }

        // Da erro com o mst, logo usamos o map sendo assim não ve o camino mais rapido (com menos peso nas arestas)
        //Location nextDestination = mst.nextVertexInMST(currentLocation);
        Location nextDestination = map.nextVertexInMST(currentLocation);
        return nextDestination;
    }
}