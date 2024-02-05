package org.CaptureTheFlag.Algorithms;

import org.CaptureTheFlag.Algorithms.Interface.IMovementAlgorithm;
import org.CaptureTheFlag.Models.Bots.Bot;
import org.CaptureTheFlag.Models.Location.Location;
import org.CaptureTheFlag.Models.Map.Map;
import org.CaptureTheFlag.Models.Player.Player;
import org.Estruturas.Exceptions.EmptyCollectionException;
import org.Estruturas.Network.Network;

import java.util.Iterator;


//TODO
public class MSTAlgorithm implements IMovementAlgorithm {
    @Override
    public String getName() {
        return "Minimum Spanning Tree ";
    }

    @Override
    public Location move(Map<Location> map, Bot bot, Player opponent) throws EmptyCollectionException {
        Location currentLocation = bot.getActualPosition();
        Location opponentLocation = opponent.getFlagPosition();

        System.out.println("\nLocalização Atual: " + currentLocation.getName());
        System.out.println("Localização da Bandeira do inimigo: " + opponentLocation.getName());

        Network<Location> network = map.mstNetwork();

        if (network instanceof Map) {
            Map<Location> mst = (Map<Location>) network;
            Iterator<Location> iterator = mst.iteratorBFS(currentLocation);

            System.out.print("Caminho MST:");
            while (iterator.hasNext()){
                System.out.print(" - " + iterator.next().getName());
            }

            iterator = mst.iteratorBFS(currentLocation);
            while (iterator.hasNext()) {
                Location nextLocation = iterator.next();
                System.out.print(" - " + nextLocation.getName());
                return nextLocation;
            }
        } else {
            System.out.println("O método mstNetwork não retornou um objeto do tipo Map.");
        }

        System.out.println("\nNão foi possível encontrar um caminho preferencial.");
        return currentLocation;
    }
}
