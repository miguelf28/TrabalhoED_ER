package org.CaptureTheFlag.Managements;

import org.CaptureTheFlag.Console.GameConsole;
import org.CaptureTheFlag.Models.Map.ImportExportMap;
import org.CaptureTheFlag.Models.Map.Map;
import org.CaptureTheFlag.Models.Bots.Bot;
import org.CaptureTheFlag.Models.Location.Location;
import org.CaptureTheFlag.Models.Player.Player;
import org.Estruturas.ArrayUnorderedList.ArrayUnorderedList;
import org.Estruturas.Exceptions.EmptyCollectionException;

import static org.CaptureTheFlag.Console.GameConsole.promptNumBots;

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
     * The first player object.
     */
    static Player player1 = new Player(1, "Player 1");
    /**
     * The second player object.
     */
    static Player player2 = new Player(2, "Player 2");

    /**
     * Gets the first player object.
     *
     * @return The first player object.
     */
    public Player getPlayer1() {
        return player1;
    }

    public Player getPlayer2() {
        return player2;
    }

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
    public static void settings() throws EmptyCollectionException {
        PlayerManagement playerManagement = new PlayerManagement();
        Map<Location> map = new Map<>();
        ImportExportMap importExport = new ImportExportMap();
        //GameManager gameManager = new GameManager();

        int numLocations = 0;
        int numBotsPlayer1;
        int numBotsPlayer2;

        //Import Map Menu
        if (GameConsole.promptImportMap()) {
            importExport.importMapAndGenerateMap("map.json");
            map.printAdjacencyMatrix();
        } else {
            numLocations = GameConsole.promptNumLocations(); //Num Locations Menu
            int pathType = GameConsole.promptPathType(); //Path Type Menu
            int density = GameConsole.promptEdgeDensity(); //Density Menu

            // Generate Map
            map.generateMap(numLocations, pathType == 1, density);
            map.printAdjacencyMatrix();

            //Export Map Menu
            if (GameConsole.promptExportMap()) {
                importExport.exportMapToJson(map, pathType == 1, density, "map.json");
                System.out.println("Mapa exportado com sucesso para 'map.json'.");
            }
        }

        //Bot number for each player menu
        numBotsPlayer1 = promptNumBots("Player 1");
        numBotsPlayer2 = promptNumBots("Player 2");

        ArrayUnorderedList<Bot> botsPlayer1 = new ArrayUnorderedList<>();
        ArrayUnorderedList<Bot> botsPlayer2 = new ArrayUnorderedList<>();

        for (int i = 0; i < numBotsPlayer1; i++) {
            Bot botForPlayer1 = new Bot(i + 1);
            botForPlayer1.setOwner(player1);
            botsPlayer1.addToRear(botForPlayer1);
        }

        for (int i = 0; i < numBotsPlayer2; i++) {
            Bot botForPlayer2 = new Bot(i + 1);
            botForPlayer2.setOwner(player2);
            botsPlayer2.addToRear(botForPlayer2);
        }

        player1.setBots(botsPlayer1);
        player2.setBots(botsPlayer2);

        playerManagement.setFlagPosition(map, player1, player2);

        //gameManager.startGame(map, player1, player2);
    }
}
