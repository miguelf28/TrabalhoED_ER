package org.CaptureTheFlag.Managements;

import org.CaptureTheFlag.Console.GameMenus;
import org.CaptureTheFlag.Models.Map.Map;
import org.CaptureTheFlag.Models.Bots.Bot;
import org.CaptureTheFlag.Models.Location.Location;
import org.Estruturas.ArrayUnorderedList.ArrayUnorderedList;
import org.Estruturas.Exceptions.EmptyCollectionException;
import org.Estruturas.Exceptions.InvalidElementException;

/**
 * The {@code GameSettings} class manages the configuration settings for a Capture The Flag game.
 * It allows players to import a map, generate a new map, and set up game parameters such as the number of locations, path type,
 * density of edges, and the number of bots per player.
 * The class also handles the creation of players, bots, and initiates the game by setting up flag positions and starting the game manager.
 * <p>
 * Note: The class assumes the existence of other management classes,  {@link PlayerManagement},
 * {@link Map}, {@link ImportExportMap}, and {@link GameManager}.
 * </p>
 *
 * @author 8190357 - Marcelo Barbosa
 * @author 8180325 - Miguel Fonseca
 */
public class GameSettings {
    /**
     * Configures and initiates the game settings. This method guides players through the process of setting up the game,
     * including importing or generating a map, specifying the number of bots, and initializing the game by setting up flag positions
     * and starting the game manager.
     * <p>
     * The method prompts the user to choose whether to import existing map data or generate a new map based on specified parameters
     * such as the number of locations, path type (bidirectional or unidirectional), and edge density.
     * If a new map is generated, the user can choose to export the map data to a JSON file.
     * </p>
     * <p>
     * After configuring the map, the method prompts the user to specify the number of bots that will play for each player.
     * It then creates and assigns bots to each player, sets up flag positions using the {@link PlayerManagement} class, and
     * starts the game using the {@link //GameManager} class.
     * </p>
     *
     * @throws EmptyCollectionException if the collection is empty.
     *
     * TODO Validar se o adicionar dos bots está correto ou se é melhor fazer numa outra forma
     *
     */
    public static void settings() throws EmptyCollectionException, InvalidElementException {
        PlayerManagement playerManagement = new PlayerManagement();
        Map<Location> map = new Map<>();
        ImportExportMap importExport = new ImportExportMap();
        MapManagement mapManagement = new MapManagement();
        //GameManager gameManager = new GameManager();

        int numLocations = 0;
        int numBotsPlayer1;
        int numBotsPlayer2;

        //Import Map Menu
        if (GameMenus.promptImportMap()) {
            importExport.importMapAndGenerateMap(map, mapManagement, "map.json");
            mapManagement.printAdjacencyMatrix();
        } else {
            numLocations = GameMenus.promptNumLocations(); //Num Locations Menu
            int pathType = GameMenus.promptPathType(); //Path Type Menu
            int density = GameMenus.promptEdgeDensity(); //Density Menu

            // Generate Map
            mapManagement.generateMap(numLocations, pathType == 1, density);
            mapManagement.printAdjacencyMatrix();

            //Export Map Menu
            if (GameMenus.promptExportMap()) {
                importExport.exportMapToJson(map, mapManagement, pathType == 1, "map.json");
                System.out.println("Mapa exportado com sucesso para 'map.json'.");
            }
        }

        //Bot number for each player menu
        numBotsPlayer1 = GameMenus.promptNumBots("Player 1");
        numBotsPlayer2 = GameMenus.promptNumBots("Player 2");

        ArrayUnorderedList<Bot> botsPlayer1 = new ArrayUnorderedList<>();
        ArrayUnorderedList<Bot> botsPlayer2 = new ArrayUnorderedList<>();

        for (int i = 0; i < numBotsPlayer1; i++) {
            Bot botForPlayer1 = new Bot(i + 1);
            botForPlayer1.setOwner(playerManagement.getPlayer1());
            botsPlayer1.addToRear(botForPlayer1);
        }

        for (int i = 0; i < numBotsPlayer2; i++) {
            Bot botForPlayer2 = new Bot(i + 1);
            botForPlayer2.setOwner(playerManagement.getPlayer2());
            botsPlayer2.addToRear(botForPlayer2);
        }

        playerManagement.getPlayer1().setBots(botsPlayer1);
        playerManagement.getPlayer2().setBots(botsPlayer2);

        playerManagement.setFlagPosition(map, playerManagement.getPlayer1(), playerManagement.getPlayer2());

        //gameManager.startGame(map, player1, player2);
    }
}
