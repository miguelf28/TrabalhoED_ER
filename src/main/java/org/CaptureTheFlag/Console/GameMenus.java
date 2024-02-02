package org.CaptureTheFlag.Console;

import java.util.Scanner;

public class GameMenus {
    static Scanner scanner = new Scanner(System.in);    /**
     * Prompts the user to choose whether to import existing map data or generate a new map.
     *
     * @return true if importing, false if generating a new map.
     */
    public static boolean promptImportMap() {
        System.out.print("\nDeseja importar os Dados do Mapa [y] or [n]: ");
        while (!scanner.hasNext()) {
            System.out.println("Por favor, insira um caracter válido.");
            System.out.print("\nDeseja importar os Dados do Mapa [y] or [n]: ");
            scanner.next();
        }
        String str = scanner.next();
        return str.equalsIgnoreCase("y") || str.equalsIgnoreCase("Y");
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
            System.out.print("\n" + playerName + ", quantos bots vão jogar (1 a 5): ");
            while (!scanner.hasNextInt()) {
                System.out.println("Por favor, insira um número válido.");
                System.out.print("\n" + playerName + ", quantos bots vão jogar (1 a 5): ");
                scanner.next();
            }
            numBots = scanner.nextInt();
            if (numBots < 1 || numBots > 5) {
                System.out.println("Número de bots inválido. Escolha entre 1 e 5 bots.");
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
        while (!scanner.hasNext()) {
            System.out.println("Por favor, insira uma opção válida.");
            System.out.print("\nDeseja exportar o mapa [y] or [n]: ");
            scanner.next();
        }
        String exportChoice = scanner.next();
        return exportChoice.equalsIgnoreCase("y");
    }
}
