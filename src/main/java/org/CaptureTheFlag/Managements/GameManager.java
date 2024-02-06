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

public class GameManager {
    static final int maxRounds = 15;

    public static void startGame(Map<Location> map, Player player1, Player player2) throws EmptyCollectionException {

        Random random = new Random();
        int round = 1;
        boolean gameOver = false;
        int firstToPlay = random.nextInt(2) + 1;

        System.out.println("\n############### START ############### ");
        while (!gameOver) {
            System.out.println("\n--------------------- Round " + round + " ---------------------");

            if (firstToPlay == 1) {
                System.out.println("\nPlayer 1 ");
                moveBots(player1, player2, map);
                if (checkGameStatus(player1, player2)) {
                    gameOver = true;
                    break;
                }

                System.out.println("\nPlayer 2 ");
                moveBots(player2, player1, map);
                if (checkGameStatus(player1, player2)) {
                    gameOver = true;
                    break;
                }

            } else {
                System.out.println("\nPlayer 1 ");
                moveBots(player1, player2, map);
                if (checkGameStatus(player1, player2)) {
                    gameOver = true;
                    break;
                }

                System.out.println("\nPlayer 2 ");
                moveBots(player2, player1, map);
                if (checkGameStatus(player1, player2)) {
                    gameOver = true;
                    break;
                }
            }

            // Verifica se atingiu o número máximo de rodadas
            if (round >= maxRounds) {
                System.out.println("\n############ Game tied after " + maxRounds + " rounds ############ ");
                gameOver = true;
            }

            round++;
            System.out.println("----------------------------------------------------");
        }
    }

    private static void moveBots(Player currentPlayer, Player opponentPlayer, Map<Location> map) throws EmptyCollectionException {
        int lastMovedBotIndex = currentPlayer.getLastMovedBotIndex();
        Bot botToMove = currentPlayer.getBots().get(lastMovedBotIndex);

        ArrayList<Location> visitedLocations = botToMove.getVisitedLocations();
        if (visitedLocations.isEmpty() || !visitedLocations.contains(currentPlayer.getFlagPosition())) {
            visitedLocations.add(botToMove.getActualPosition());
            //TODO se calhar criar uma lista à parte para o caminho percorrido e o visitados, em que o visitados é para as validações  e o caminho percorrido, para mostrar no fim todo o caminho
        }

        System.out.println("Visitados: " + visitedLocations);

        botToMove.setMovedThisRound(true);

        System.out.println("Bot " + botToMove.getId() + ":");

        IMovementAlgorithm algorithm = botToMove.getMovementAlgorithm();
        Location currentPosition = botToMove.getActualPosition();
        Location newPosition = currentPosition;
        if (botToMove.isCarryingFlag()) {
            newPosition = algorithm.move(map, botToMove, currentPlayer);
        } else {
            newPosition = algorithm.move(map, botToMove, opponentPlayer);
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

    private static void findAndMoveToValidPosition(Map<Location> map, Bot botToMove, Player currentPlayer, Player opponentPlayer, ArrayList<Location> visitedLocations) throws EmptyCollectionException {
        Location currentPosition = botToMove.getActualPosition();
        System.out.println("Verificando ligações a localização!");
        for (Location neighbor : map.getAdjacentVertices(currentPosition)) {
            if (isValidMove(neighbor, botToMove, opponentPlayer, visitedLocations)) {
                visitedLocations.add(neighbor);
                moveBotToNewPosition(botToMove, botToMove.getActualPosition(), neighbor, currentPlayer, opponentPlayer, visitedLocations);
                return;
            }
        }
        System.out.println("Movimento inválido para o Bot " + botToMove.getId() + ". Não foi possível encontrar uma localização válida.");
    }

    private static void moveBotToNewPosition(Bot botToMove, Location currentPosition, Location newPosition, Player currentPlayer, Player opponentPlayer, ArrayList<Location> visitedLocations) {
        System.out.println("\nBot " + botToMove.getId() + " movendo-se de " + currentPosition + " para " + newPosition);
        botToMove.setActualPosition(newPosition);

        if (newPosition.equals(opponentPlayer.getFlagPosition())) {
            System.out.println(greenColor + "\nBot " + botToMove.getId() + " do " + currentPlayer.getPlayerName() +" capturou a bandeira inimiga!" + resetColor);
            botToMove.setCarryingFlag(true);
            visitedLocations.clear();
        }


        if (botToMove.isCarryingFlag() && newPosition.equals(currentPlayer.getFlagPosition())) {
            System.out.println(redColor + "\nBot " + botToMove.getId() + " retornou à base com a bandeira!" + resetColor);
        }
    }

    private static boolean checkGameStatus(Player player1, Player player2) {
        if (player1.getFlagPosition().equals(player2.getFlagPosition())) {
            System.out.println("Fim de jogo! O jogador " + player1.getPlayerName() + " venceu!");
            return true;
        } else if (player2.getFlagPosition().equals(player1.getFlagPosition())) {
            System.out.println("Fim de jogo! O jogador " + player2.getPlayerName() + " venceu!");
            return true;
        }
        return false;
    }


    public static boolean isValidMove(Location newPosition, Bot currentBot, Player opponentPlayer, ArrayList<Location> visitedLocations) throws EmptyCollectionException {
        if (!visitedLocations.isEmpty() && visitedLocations.contains(newPosition)) {
            System.out.println("\nLocalização já visitada!");
            return false;
        } else if (newPosition.equals(opponentPlayer.getFlagPosition())) {
            return true;
        }

        for (Bot bot : opponentPlayer.getBots()) {
            if (bot.getActualPosition().equals(newPosition) && bot.isCarryingFlag()) {
                currentBot.returnFlagToBase(opponentPlayer.getFlagPosition());
                System.out.println("O bot " + currentBot.getId() + " encontrou um inimigo com a bandeira! A bandeira está retornando à base.");
                return true;
            } else if (opponentPlayer.getFlagPosition().equals(newPosition) && currentBot.getOwner().getFlagPosition().equals(newPosition)) {
                currentBot.returnFlagToBase(currentBot.getOwner().getFlagPosition());
                bot.returnFlagToBase(bot.getOwner().getFlagPosition());
                System.out.println("As bandeiras se encontraram! Ambas estão retornando à base.");
                return true;
            }
        }
        return true;
    }
}
