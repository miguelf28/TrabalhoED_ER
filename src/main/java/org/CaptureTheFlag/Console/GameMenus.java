package org.CaptureTheFlag.Console;

import org.CaptureTheFlag.Algorithms.BFSAlgorithm;
import org.CaptureTheFlag.Algorithms.DFSAlgorithm;
import org.CaptureTheFlag.Algorithms.Interface.IMovementAlgorithm;
import org.CaptureTheFlag.Algorithms.MSTAlgorithm;
import org.CaptureTheFlag.Algorithms.ShortestPathAlgorithm;
import org.Estruturas.ArrayOrderedList.ArrayOrderedList;
import org.Estruturas.Exceptions.EmptyCollectionException;

import java.util.Scanner;

public class GameMenus {
    static Scanner scanner = new Scanner(System.in);
    private static ArrayOrderedList<Character> availableAlgorithmsPlayer1 = new ArrayOrderedList<>();
    private static ArrayOrderedList<Character> availableAlgorithmsPlayer2 = new ArrayOrderedList<>();

    /**
     * Prompts the user to choose whether to import existing map data or generate a new map.
     *
     * @return true if importing, false if generating a new map.
     */
    public static boolean promptImportMap() {
        System.out.print("\nDeseja importar os Dados do Mapa [y] or [n]: ");
        while (!scanner.hasNext("[ynYN]")) {
            System.out.println("Por favor, insira a opção correta (y ou n).");
            System.out.print("\nDeseja importar os Dados do Mapa [y] or [n]: ");
            scanner.next();
        }
        String str = scanner.next();
        return str.equalsIgnoreCase("y");
    }


    /**
     * Prompts the user for the number of locations on the map.
     *
     * @return The number of locations.
     */
    public static int promptNumLocations() {
        int numLocations;
        do {
            System.out.print("\nDigite a quantidade de localizações no mapa (10 - 30): ");
            while (!scanner.hasNextInt()) {
                System.out.println("Por favor, insira um número válido.");
                System.out.print("\nDigite a quantidade de localizações no mapa (10 - 30): ");
                scanner.next();
            }
            numLocations = scanner.nextInt();
            if (numLocations < 5 || numLocations > 30) {
                System.out.println("Quantidade de localizacoes inválida. Escolha entre 10 e 30.");
            }
        } while (numLocations < 5 || numLocations > 30);
        return numLocations;
    }

    /**
     * Prompts the user for the type of paths (bidirectional or unidirectional).
     *
     * @return 1 for bidirectional, 0 for unidirectional.
     */
    public static int promptPathType() {
        int pathType;
        do {
            System.out.print("\nDigite o tipo dos caminhos (1 - Bidirecionais, 0 - Unidirecionais): ");
            while (!scanner.hasNextInt()) {
                System.out.println("Por favor, insira um número válido.");
                System.out.print("\nDigite o tipo dos caminhos (1 - Bidirecionais, 0 - Unidirecionais): ");
                scanner.next();
            }
            pathType = scanner.nextInt();
            if (pathType != 0 && pathType != 1) {
                System.out.println("Tipo de caminho inválido. Deve ser 1 (Bidirecional) ou 0 (Unidirecional).");
            }
        } while (pathType != 0 && pathType != 1);
        return pathType;
    }

    public static int promptNumBots(String playerName) {
        int numBots;
        do {
            String playerColor = playerName.equals("JOGADOR 1") ? "\u001B[31m" : "\u001B[32m";

            System.out.print("\n" + playerColor + playerName + "\u001B[0m quantos BOTS vão jogar (1 a 5): " );
            while (!scanner.hasNextInt()) {
                System.out.println("Por favor, insira um número válido.");
                System.out.print("\n" + playerColor + playerName + "\u001B[0m quantos BOTS vão jogar (1 a 5): " );
                scanner.next();
            }
            numBots = scanner.nextInt();
            if (numBots < 1 || numBots > 5) {
                System.out.println("Escolha entre 1 a 5 bots.");
            }
        } while (numBots < 1 || numBots > 5);
        return numBots;
    }


    /**
     * Prompts the user for the edge density.
     *
     * @return The edge density.
     */
    public static int promptEdgeDensity() {
        int density;
        do {
            System.out.print("\nDigite a densidade das arestas (1 a 100): ");
            while (!scanner.hasNextInt()) {
                System.out.println("Por favor, insira um número válido.");
                System.out.print("\nDigite a densidade das arestas (1 a 100): ");
                scanner.next();
            }
            density = scanner.nextInt();
            if (density < 1 || density > 100) {
                System.out.println("Densidade inválida. Deve ser entre 1 e 100.");
            }
        } while (density < 1 || density > 100);
        return density;
    }

    /**
     * Prompts the user whether to export the map.
     *
     * @return true if exporting, false otherwise.
     */
    public static boolean promptExportMap() {
        System.out.print("\nDeseja exportar o mapa [y] or [n]: ");
        while (!scanner.hasNext("[ynYN]")) {
            System.out.println("Por favor, insira uma opção válida (y ou n).");
            System.out.print("\nDeseja exportar o mapa [y] or [n]: ");
            scanner.next();
        }
        String exportChoice = scanner.next();
        return exportChoice.equalsIgnoreCase("y");
    }


    public static IMovementAlgorithm promptMovementOptions(int playerId, int botId) throws EmptyCollectionException {
        ArrayOrderedList<Character> playerList = (playerId == 1) ? availableAlgorithmsPlayer1 : availableAlgorithmsPlayer2;

        if (playerList.isEmpty()) {
            initializeAvailableAlgorithms(playerList);
        }

        IMovementAlgorithm selectedAlgorithm = null;

        while (true) {
            String playerColor = (playerId == 1) ? "\u001B[31m" : "\u001B[32m";

            System.out.println("\n" + playerColor + "JOGADOR " + playerId + " " + "\u001B[0m" +
                    "| Escolha o algoritmo de movimentação para o BOT " + botId + ":");

            System.out.println("Opções disponíveis:");
            for (char option : playerList) {
                System.out.println(option + ") " + getAlgorithmName(option));
            }
            System.out.println();

            System.out.print("Escolha: ");
            char choice = scanner.next().charAt(0);

            char lowerCaseChoice = Character.toLowerCase(choice);
            if (playerList.contains(lowerCaseChoice)) {
                playerList.remove(lowerCaseChoice);

                switch (lowerCaseChoice) {
                    case 'a':
                        selectedAlgorithm = new ShortestPathAlgorithm();
                        break;
                    case 'b':
                        selectedAlgorithm = new BFSAlgorithm();
                        break;
                    case 'c':
                        selectedAlgorithm = new DFSAlgorithm();
                        break;
                    case 'd':
                        selectedAlgorithm = new MSTAlgorithm();
                        break;
                    default:
                        System.out.println("Opção inválida. Por favor, escolha uma opção válida.");
                }
                break;
            } else {
                System.out.println("Opção inválida ou já escolhida. Por favor, escolha uma opção válida.");
            }
        }
        return selectedAlgorithm;
    }

    private static void initializeAvailableAlgorithms(ArrayOrderedList<Character> list) {
        list.add('a');
        list.add('b');
        list.add('c');
        list.add('d');
    }

    static {
        initializeAvailableAlgorithms(availableAlgorithmsPlayer1);
        initializeAvailableAlgorithms(availableAlgorithmsPlayer2);
    }
    private static String getAlgorithmName(char choice) {
        switch (choice) {
            case 'a':
                return "Caminho Mais Curto (Shortest Path)";
            case 'b':
                return "Travessia em Largura (BFS)";
            case 'c':
                return "Travessia em Profundidade (DFS)";
            case 'd':
                return "Arvore geradora de custo mínimo (MST)";
            default:
                return "Sem algoritmos";
        }
    }
}
