package org.CaptureTheFlag.Algorithms;

import org.CaptureTheFlag.Algorithms.Interface.IMovementAlgorithm;
import org.CaptureTheFlag.Models.Bots.Bot;
import org.CaptureTheFlag.Models.Location.Location;
import org.CaptureTheFlag.Models.Map.Map;
import org.CaptureTheFlag.Models.Player.Player;
import org.Estruturas.Exceptions.EmptyCollectionException;

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

        // Criação do grafo ponderado usando o algoritmo MST
        Map<Location> mstGraph = (Map<Location>) map.mstNetwork();

        // Caminho de MST da localização atual do bot até a bandeira do oponente
        Iterator<Location> mstPathIterator = mstGraph(currentLocation);
        mstPathIterator.next();  // Ignorando o primeiro nó (a localização atual do bot)

        System.out.println("\nLocalização Atual: " + currentLocation.getName());
        System.out.println("Localização da Bandeira do inimigo: " + opponentLocation.getName());

        System.out.print("Caminho MST: ");
        while (mstPathIterator.hasNext()) {
            Location nextLocation = mstPathIterator.next();
            System.out.print(" - " + nextLocation.getName());

            // Realiza o movimento do bot para o próximo nó no caminho MST
            //bot.move(nextLocation);
        }

        // Retorna a última localização no caminho MST (deve ser a localização da bandeira do oponente)
        return mstPathIterator.hasNext() ? mstPathIterator.next() : null;
    }

    private Iterator<Location> mstGraph(Location currentLocation) {
        return null;
    }
}
