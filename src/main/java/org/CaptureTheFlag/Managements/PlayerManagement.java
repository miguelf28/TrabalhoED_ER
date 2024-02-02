package org.CaptureTheFlag.Managements;

import org.CaptureTheFlag.Models.Map.Map;
import org.CaptureTheFlag.Models.Bots.Bot;
import org.CaptureTheFlag.Models.Location.Location;
import org.CaptureTheFlag.Models.Player.Player;
import org.Estruturas.ArrayUnorderedList.ArrayUnorderedList;
import org.Estruturas.Exceptions.EmptyCollectionException;
import org.CaptureTheFlag.Console.PlayerConsole;

/**
 * The {@code PlayerManagement} class manages the setup of flag positions for players in a Capture The Flag game.
 * It allows players to choose flag positions on a given map, ensuring that the chosen positions are valid and not occupied by bots.
 *
 * @author 8190357 - Marcelo Barbosa
 * @author 8180325 - Miguel Fonseca
 */
public class PlayerManagement {
    PlayerConsole playerConsole = new PlayerConsole();
    Player player1 = new Player(1, "Player 1");
    Player player2 = new Player(2, "Player 2");

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
     * Constructor for the {@code PlayerManagement} class.
     */
    public PlayerManagement() {
        this.playerConsole = playerConsole;
    }


    /**
     * Allows players to choose flag positions on the map, ensuring the positions are valid and not occupied by bots.
     *
     * @param map     The map containing available locations.
     * @param player1 The first player.
     * @param player2 The second player.
     * @throws EmptyCollectionException if the collection is empty.
     */
    public void setFlagPosition(Map<Location> map, Player player1, Player player2) throws EmptyCollectionException {
        playerConsole.displayAvailableLocations(map);

        int player1Choice;
        do {
            System.out.print("\nJogador 1, escolha a posição da bandeira (digite o número correspondente): ");
            player1Choice = playerConsole.getPlayerChoice();

            if (!playerConsole.isValidChoice(player1Choice, map.getLocations().size())) {
                System.out.println("Escolha inválida. Digite um número correspondente a uma posição disponível.");
            } else {
                Location player1Location = map.getLocations().get(player1Choice - 1);
                player1.setFlagPosition(player1Location);

                for (Bot bot : player1.getBots()) {
                    bot.setActualPosition(player1.getFlagPosition());
                }

                System.out.println("Flag do Jogador 1: " + player1Location.getName());
            }
        } while (!playerConsole.isValidChoice(player1Choice, map.getLocations().size()));

        int player2Choice;
        do {
            System.out.print("\nJogador 2, escolha a posição da bandeira (digite o número correspondente): ");
            player2Choice = playerConsole.getPlayerChoice();

            if (!playerConsole.isValidChoice(player2Choice, map.getLocations().size())) {
                System.out.println("Escolha inválida. Digite um número correspondente a uma posição disponível.");
            } else {
                Location player2Location = map.getLocations().get(player2Choice - 1);

                if (playerConsole.isOccupiedByBot(player2Location, player1.getBots())) {
                    System.out.println("Escolha inválida. A posição está ocupada por um bot do Jogador 1.");
                } else {
                    player2.setFlagPosition(player2Location);

                    for (Bot bot : player2.getBots()) {
                        bot.setActualPosition(player2.getFlagPosition());
                    }

                    System.out.println("Flag do Jogador 2: " + player2Location.getName());
                }
            }
        } while (!playerConsole.isValidChoice(player2Choice, map.getLocations().size())
                || playerConsole.isOccupiedByBot(map.getLocations().get(player2Choice - 1), player1.getBots()));
    }


    /**
     * Checks if a bot is occupying a specific position on the map.
     *
     * @param position The position to check.
     * @param bots     The list of bots to check against.
     * @return {@code true} if a bot is occupying the position, {@code false} otherwise.
     */
    private static boolean isBotOccupyingPosition(Location position, ArrayUnorderedList<Bot> bots) {
        for (Bot bot : bots) {
            if (bot.getActualPosition().equals(position)) {
                return true;
            }
        }
        return false;
    }
}

