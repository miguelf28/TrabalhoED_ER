package org.CaptureTheFlag.Algorithms;

import org.CaptureTheFlag.Algorithms.Interface.IMovementAlgorithm;
import org.CaptureTheFlag.Models.Bots.Bot;
import org.CaptureTheFlag.Models.Location.Location;
import org.CaptureTheFlag.Models.Map.Map;
import org.CaptureTheFlag.Models.Player.Player;
import org.Estruturas.ArrayUnorderedList.ArrayUnorderedList;
import org.Estruturas.Network.Network;

import java.util.Iterator;


//TODO
public class MSTAlgorithm implements IMovementAlgorithm {
    @Override
    public String getName() {
        return "Minimum Spanning Tree";
    }

    @Override
    public Location move(Map<Location> map, Bot bot, Player opponent) {
        Location currentLocation = bot.getActualPosition();
        Location opponentLocation = opponent.getFlagPosition();

        System.out.println("Localização Atual: " + currentLocation.getName());
        System.out.println("Localização Alvo: " + opponentLocation.getName());
        //System.out.println(map);
        // Calcula a MST do mapa
        Network<Location> mst = map.mstNetwork();

        // Encontra o próximo destino na MST
        Location nextDestination = findNextDestinationInMST(currentLocation, mst, map);

        // Imprime o caminho percorrido na MST
        //printPath(currentLocation, nextDestination, mst);

        return nextDestination;
    }

    private Location findNextDestinationInMST(Location currentPosition, Network<Location> mst, Map<Location> map) {
        // Obtém os vértices da MST a partir do mapa
        Location[] vertices = map.getVertices();

        // Encontra o índice do vértice atual na MST
        int currentIndex = -1;
        for (int i = 0; i < vertices.length; i++) {
            if (vertices[i].equals(currentPosition)) {
                currentIndex = i;
                break;
            }
        }

        // Verifica se o bot está no último vértice da MST
        if (currentIndex == vertices.length - 1) {
            // Se estiver, o bot permanece na posição atual
            return currentPosition;
        } else {
            // Caso contrário, o bot avança para o próximo vértice na MST
            return vertices[currentIndex + 1];
        }
    }

    private void printPath(Location start, Location end, Network<Location> mst) {
        // Realiza uma busca em largura na MST para encontrar o caminho entre start e end
        ArrayUnorderedList<Location> path = new ArrayUnorderedList<>();
        Iterator<Location> iterator = mst.iteratorBFS(start);
        iterator.next();


        // Adiciona os vértices visitados ao caminho
        while (iterator.hasNext()) {
            Location vertex = iterator.next();
            path.addToRear(vertex);
            if (vertex.equals(end)) {
                break; // Para quando encontrar o destino
            }
        }

        // Imprime o caminho
        System.out.print("Caminho na Minimum Spanning Tree: ");
        for (Location location : path) {
            System.out.print(location.getName() + " -> ");
        }
        System.out.println(end.getName());
    }
}



