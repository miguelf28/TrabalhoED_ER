package org.CaptureTheFlag.Managements;

import org.CaptureTheFlag.Algorithms.Interface.IMovementAlgorithm;
import org.CaptureTheFlag.Models.Bots.Bot;
import org.CaptureTheFlag.Models.Location.Location;
import org.CaptureTheFlag.Models.Map.Map;
import org.CaptureTheFlag.Models.Player.Player;
import org.Estruturas.ArrayList.ArrayList;
import org.Estruturas.Exceptions.EmptyCollectionException;

import java.util.Random;

import static org.CaptureTheFlag.GUI.Miscellaneous.*;

/**
 * The GameManager class manages the game flow and bot movements in the Capture the Flag game.
 */
public class GameManager {
    private static boolean flagCaptured = false;
    static final int maxRounds = 75;
    static String endgame = null;

    /**
     * Starts the game with the provided map and players.
     *
     * @param map     The map of the game.
     * @param player1 The first player.
     * @param player2 The second player.
     * @throws EmptyCollectionException If an empty collection is encountered.
     */
    public static void startGame(Map<Location> map, Player player1, Player player2) throws EmptyCollectionException {
        Random random = new Random();
        int round = 1;
        boolean gameOver = false;
        int firstToPlay = random.nextInt(2) + 1;
        System.out.println(firstToPlay);
        System.out.println("\n############### START ############### ");
        while (!gameOver) {

            System.out.println("\n--------------------- Round " + round + " ---------------------");

            if (firstToPlay == 1) {
                System.out.println(blueColor + "JOGADOR 1 " + resetColor + "[BASE: " + player1.getFlagPosition().getName() + "]");
                moveBots(player1, player2, map);
                if (checkGameStatus(player1, player2)) {
                    gameOver = true;
                    break;
                }
                System.out.println("\n..  ..  ..  ..  ..  ..  ..  ..  ..  ..  ..  ..  ..  ");
                System.out.println(purpleColor + "JOGADOR 2 " + resetColor + "[BASE: " + player2.getFlagPosition().getName() + "]");
                moveBots(player2, player1, map);
                if (checkGameStatus(player1, player2)) {
                    gameOver = true;
                    break;
                }

            } else {
                System.out.println(purpleColor + "JOGADOR 2 " + resetColor + "[BASE: " + player2.getFlagPosition().getName() + "]");
                moveBots(player2, player1, map);
                if (checkGameStatus(player1, player2)) {
                    gameOver = true;
                    break;
                }
                System.out.println("\n..  ..  ..  ..  ..  ..  ..  ..  ..  ..  ..  ..  ..  ");
                System.out.println(blueColor + "JOGADOR 1 " + resetColor + "[BASE: " + player1.getFlagPosition().getName() + "]");
                moveBots(player1, player2, map);
                if (checkGameStatus(player1, player2)) {
                    gameOver = true;
                    break;
                }
            }

            // Verifica se atingiu o número máximo de rodadas
            if (round >= maxRounds) {
                System.out.println("\n##### Após " + maxRounds + " rondas, o jogo empatou ##### ");
                gameOver = true;
            }

            round++;
            System.out.println("----------------------------------------------------");
        }
    }

    /**
     * Moves the bots of the current player according to the game rules.
     *
     * @param currentPlayer  The player whose bots are being moved.
     * @param opponentPlayer  The opponent player.
     * @param map             The map of the game.
     * @throws EmptyCollectionException If an empty collection is encountered.
     */
    private static void moveBots(Player currentPlayer, Player opponentPlayer, Map<Location> map) throws EmptyCollectionException {
        int lastMovedBotIndex = currentPlayer.getLastMovedBotIndex();
        Bot botToMove = currentPlayer.getBots().get(lastMovedBotIndex);

        ArrayList<Location> visitedLocations = botToMove.getVisitedLocations();
        if (visitedLocations.isEmpty() || !visitedLocations.contains(currentPlayer.getFlagPosition())) {
            visitedLocations.add(botToMove.getActualPosition());
        }
        System.out.println("Visitados: \n" + visitedLocations);


        if (botToMove.isCarryingFlag()) {
            System.out.println("BOT " + botToMove.getId() + yellowColor + " [Tem a bandeira inimiga] " + resetColor);
        } else {
            System.out.println("BOT " + botToMove.getId());
        }

        botToMove.setMovedThisRound(true);
        IMovementAlgorithm algorithm = botToMove.getMovementAlgorithm();
        Location currentPosition = botToMove.getActualPosition();
        Location newPosition = null;

        if (!flagCaptured) {
            if (botToMove.isCarryingFlag()) {
                newPosition = algorithm.move(map, botToMove, currentPlayer);
            } else {
                newPosition = algorithm.move(map, botToMove, opponentPlayer);
            }
        } else {
            newPosition = algorithm.move(map, botToMove, currentPlayer);
        }

        if (isValidMove(newPosition, botToMove, opponentPlayer, visitedLocations)) {
            visitedLocations.add(newPosition);
            moveBotToNewPosition(botToMove, currentPosition, newPosition, currentPlayer, opponentPlayer, visitedLocations);

        } else {
            findAndMoveToValidPosition(map, botToMove, currentPlayer, opponentPlayer, visitedLocations);
        }

        lastMovedBotIndex = (lastMovedBotIndex + 1) % currentPlayer.getBots().size();
        currentPlayer.setLastMovedBotIndex(lastMovedBotIndex);
    }

    /**
     * Finds a valid position for the bot to move to and moves it accordingly.
     *
     * @param map               The map of the game.
     * @param botToMove         The bot to move.
     * @param currentPlayer     The current player.
     * @param opponentPlayer    The opponent player.
     * @param visitedLocations  The list of visited locations.
     * @throws EmptyCollectionException If an empty collection is encountered.
     */
    private static void findAndMoveToValidPosition(Map<Location> map, Bot botToMove, Player currentPlayer, Player opponentPlayer, ArrayList<Location> visitedLocations) throws EmptyCollectionException {
        Location currentPosition = botToMove.getActualPosition();
        System.out.println("\nVerificando nova localização!");
        for (Location neighbor : map.getAdjacentVertices(currentPosition)) {
            if (isValidMove(neighbor, botToMove, opponentPlayer, visitedLocations)) {
                visitedLocations.add(neighbor);
                moveBotToNewPosition(botToMove, botToMove.getActualPosition(), neighbor, currentPlayer, opponentPlayer, visitedLocations);
                return;
            }
        }
        visitedLocations.clear();
        System.out.println(redColor + "Movimento inválido para o Bot " + botToMove.getId() + ". Não foi possível encontrar uma localização válida." + resetColor);
    }

    /**
     * Moves the bot to the new position and handles flag capturing and game end conditions.
     *
     * @param botToMove         The bot being moved.
     * @param currentPosition   The current position of the bot.
     * @param newPosition       The new position to move the bot to.
     * @param currentPlayer     The current player.
     * @param opponentPlayer    The opponent player.
     * @param visitedLocations  The list of visited locations.
     */
    private static void moveBotToNewPosition(Bot botToMove, Location currentPosition, Location newPosition, Player currentPlayer, Player opponentPlayer, ArrayList<Location> visitedLocations) {
        System.out.println("\nBOT " + botToMove.getId() + " movendo-se de " + currentPosition + " para " + newPosition);
        botToMove.setActualPosition(newPosition);

        if (newPosition.equals(opponentPlayer.getFlagPosition())) {
            System.out.println(greenColor + "\nBOT " + botToMove.getId() + " do " + currentPlayer.getPlayerName() + " capturou a bandeira inimiga!" + resetColor);
            botToMove.setCarryingFlag(true);
            flagCaptured = true;
            visitedLocations.clear();
        }

        if (botToMove.isCarryingFlag() && newPosition.equals(currentPlayer.getFlagPosition())) {
            System.out.println(greenColor + "\nBOT " + botToMove.getId() + " retornou à base com a bandeira!" + resetColor);
            endgame = currentPlayer.getPlayerName();
            visitedLocations.clear();
        }
    }

    /**
     * Checks the game status and prints the winner if the game has ended.
     *
     * @param player1 The first player.
     * @param player2 The second player.
     * @return True if the game has ended, false otherwise.
     */
    private static boolean checkGameStatus(Player player1, Player player2) {
        if (endgame != null && endgame.equals(player1.getPlayerName())) {
            System.out.println(greenColor + "\nFim de jogo! O " + player1.getPlayerName() + " venceu!" + resetColor);
            return true;
        } else if (endgame != null && endgame.equals(player2.getPlayerName())) {
            System.out.println(greenColor + "\nFim de jogo! O " + player2.getPlayerName() + " venceu!" + resetColor);
            return true;
        }
        return false;
    }

    /**
     * Checks if the move to the new position is valid.
     *
     * @param newPosition      The new position to move the bot to.
     * @param currentBot       The bot attempting to move.
     * @param opponentPlayer   The opponent player.
     * @param visitedLocations The list of visited locations.
     * @return True if the move is valid, false otherwise.
     * @throws EmptyCollectionException If an empty collection is encountered.
     */
    public static boolean isValidMove(Location newPosition, Bot currentBot, Player opponentPlayer, ArrayList<Location> visitedLocations) throws EmptyCollectionException {
        if (!visitedLocations.isEmpty() && visitedLocations.contains(newPosition)) {
            return false;
        }

        for (Bot bot : opponentPlayer.getBots()) {
            if (bot.getActualPosition().equals(newPosition) && bot.isCarryingFlag()) {
                currentBot.returnFlagToBase(opponentPlayer.getFlagPosition());
                visitedLocations.clear();
                flagCaptured = false;
                System.out.println(redColor + "\nO bot " + currentBot.getId() + " encontrou um inimigo com a bandeira! A bandeira está retornando à base." + resetColor);
                return true;
            } else if (opponentPlayer.getFlagPosition().equals(newPosition) && currentBot.getOwner().getFlagPosition().equals(newPosition)) {
                currentBot.returnFlagToBase(currentBot.getOwner().getFlagPosition());
                bot.returnFlagToBase(bot.getOwner().getFlagPosition());
                visitedLocations.clear();
                flagCaptured = false;
                System.out.println(redColor + "\nAs bandeiras se encontraram! Ambas estão retornando à base." + resetColor);
                return true;
            }
        }

        if (flagCaptured && newPosition.equals(opponentPlayer.getFlagPosition())) {
            System.out.println(redColor + "\nA bandeira já foi capturada. Não é possível capturá-la novamente." + resetColor);
            visitedLocations.clear();
            return false;
        }
        return true;
    }
}
