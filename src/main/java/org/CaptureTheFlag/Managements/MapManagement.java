package org.CaptureTheFlag.Managements;

import org.CaptureTheFlag.Models.Location.Location;
import org.CaptureTheFlag.Models.Location.LocationNames;
import org.CaptureTheFlag.Models.Map.Map;
import org.Estruturas.ArrayList.ArrayList;
import org.Estruturas.Exceptions.EmptyCollectionException;
import org.Estruturas.Exceptions.InvalidElementException;

import java.util.Random;

public class MapManagement {
    Map<Location> map = new Map<>();

    Random random = new Random();


    /**
     * Chooses names for the locations based on the number of names needed.
     *
     * @param intNames The number of names to choose.
     * @return The chosen names.
     */
    private ArrayList<LocationNames> chooseNames(int intNames) throws InvalidElementException {
        ArrayList<LocationNames> chosenNames = new ArrayList<>();

        if (intNames > LocationNames.values().length) {
            throw new InvalidElementException("Número de nomes solicitados excede o número de valores no enum.");
        }

        for (int i = 0; i < intNames; i++) {
            chosenNames.add(LocationNames.values()[i]);
        }

        return chosenNames;
    }

    /**
     * Generates a random map with the specified number of locations, bidirectional paths, and density.
     * This is one of the most important classes of this game, as it generates everything on the map
     *
     * @param numLocations       The number of locations to generate in the map.
     * @param bidirectionalPaths Flag indicating whether the paths should be bidirectional.
     * @param density            The density of the paths in the map.
     */
    public void generateMap(Map<Location> map, int numLocations, boolean bidirectionalPaths, int density) throws InvalidElementException, EmptyCollectionException {
        // Lista de nomes de locais disponíveis
        ArrayList<LocationNames> availableNames = chooseNames(numLocations);

        // Adiciona os vértices ao mapa
        for (int i = 0; i < numLocations; i++) {
            Location location = new Location(i + 1, availableNames.get(i).toString());
            map.addVertex(location);
        }

        // Adiciona as arestas (caminhos) entre os vértices
        for (int i = 0; i < numLocations; i++) {
            for (int j = i + 1; j < numLocations; j++) {
                if (random.nextInt(100) < density) {
                    try {
                        int distance = (int) (random.nextDouble() * 14 + 1);
                        map.addEdge(i, j, distance);
                        //System.out.println("Aresta adicionada: (" + i + ", " + j + ") com distância: " + distance);
                        if (bidirectionalPaths) {
                            map.addEdge(j, i, distance);
                            //System.out.println("Aresta bidirecional adicionada: (" + j + ", " + i + ") com distância: " + distance);
                        }
                    } catch (IndexOutOfBoundsException e) {
                        System.out.println("Erro ao adicionar aresta: " + e.getMessage());
                    }
                }
            }
        }

        // Exibe o número de vértices e densidade das arestas geradas
        System.out.println("Número de vértices no mapa: " + map.getNumVertices());
        System.out.println("Densidade das arestas: " + density);
    }

    /**
     * Prints the adjacency matrix representation of the map.
     */
    public void printAdjacencyMatrix(Map<Location> map, boolean bidirectionalPaths) {
        System.out.println("Adjacency Matrix:");

        int numVertices = map.getNumVertices();
        Location[] vertices = map.getVertices();

        // Imprime os cabeçalhos das colunas com os nomes dos vértices
        System.out.print("        ");
        for (Location vertex : vertices) {
            System.out.printf("%-9s", vertex.getName());
        }
        System.out.println();

        // Imprime a matriz de adjacência
        double[][] costs = map.getCost();
        for (int i = 0; i < numVertices; i++) {
            System.out.printf("%-9s", vertices[i].getName());
            for (int j = 0; j < numVertices; j++) {
                if (bidirectionalPaths || i < j) {
                    if (costs[i][j] == Double.MAX_VALUE) {
                        System.out.printf("%-9s", "-");
                    } else {
                        System.out.printf("%-9d", (int) costs[i][j]);
                    }
                } else {
                    System.out.print("         ");
                }
            }
            System.out.println();
        }
    }
}
