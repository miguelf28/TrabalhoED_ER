package org.CaptureTheFlag.Managements;

import org.CaptureTheFlag.Algorithms.Interface.IMovementAlgorithm;
import org.CaptureTheFlag.Models.Bots.Bot;
import org.CaptureTheFlag.Models.Location.Location;
import org.CaptureTheFlag.Models.Map.Map;
import org.CaptureTheFlag.Models.Player.Player;
import org.Estruturas.Exceptions.EmptyCollectionException;

import java.util.Random;

public class GameManager {
    static final int maxRounds = 8;

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
                moveBots(player1, player2, map, round);
                if (checkGameStatus(player1, player2)) {
                    gameOver = true;
                    break;
                }

                System.out.println("\nPlayer 2 ");
                moveBots(player2, player1, map, round);
                if (checkGameStatus(player1, player2)) {
                    gameOver = true;
                    break;
                }
            } else {
                System.out.println("\nPlayer 1 ");
                moveBots(player1, player2, map, round);
                if (checkGameStatus(player1, player2)) {
                    gameOver = true;
                    break;
                }

                System.out.println("\nPlayer 2 ");
                moveBots(player2, player1, map, round);
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

    private static void moveBots(Player currentPlayer, Player opponentPlayer, Map<Location> map, int round) throws EmptyCollectionException {
        int lastMovedBotIndex = currentPlayer.getLastMovedBotIndex();
        Bot botToMove = currentPlayer.getBots().get(lastMovedBotIndex);

        if (botToMove != null) {

            botToMove.setMovedThisRound(true);

            System.out.println("Bot " + botToMove.getId() + ":");

            IMovementAlgorithm algorithm = botToMove.getMovementAlgorithm();
            Location currentPosition = botToMove.getActualPosition();
            Location newPosition = algorithm.move(map, botToMove, opponentPlayer);

            if (isValidMove(newPosition, botToMove, opponentPlayer)) {
                System.out.println("\nBot " + botToMove.getId() + " movendo de " + currentPosition + " para " + newPosition);
                botToMove.setActualPosition(newPosition);

                // Check if the bot reached the opponent's flag position
                if (newPosition.equals(opponentPlayer.getFlagPosition())) {
                    System.out.println("\nBot " + botToMove.getId() + " captured the flag!");
                    botToMove.setCarryingFlag(true);
                }

                // Check if the bot returned to its base with the opponent's flag
                if (botToMove.isCarryingFlag() && newPosition.equals(currentPlayer.getFlagPosition())) {
                    System.out.println("\nBot " + botToMove.getId() + " returned to base with the flag!");
                    endGame(currentPlayer);
                    return;
                }
            } else {
                boolean validMoveFound = false;

                // Iterar sobre as possíveis ligações da posição atual do bot
                for (Location neighbor : map.getAdjacentVertices(currentPosition)) {
                    if (isValidMove(neighbor, botToMove, opponentPlayer)) {
                        newPosition = neighbor;
                        validMoveFound = true;
                        break;
                    }
                }

                if (validMoveFound && newPosition != null) {
                    System.out.println("\nBot " + botToMove.getId() + " movendo de " + currentPosition + " para " + newPosition);
                    botToMove.setActualPosition(newPosition);

                    // Check if the bot reached the opponent's flag position
                    if (newPosition.equals(opponentPlayer.getFlagPosition())) {
                        System.out.println("\nBot " + botToMove.getId() + " captured the flag!");
                        botToMove.setCarryingFlag(true);
                    }

                    // Check if the bot returned to its base with the opponent's flag
                    if (botToMove.isCarryingFlag() && newPosition.equals(currentPlayer.getFlagPosition())) {
                        System.out.println("\nBot " + botToMove.getId() + " returned to base with the flag!");
                        endGame(currentPlayer);
                        return;
                    }
                } else {
                    System.out.println("Movimento inválido para o Bot " + botToMove.getId() + ". Não foi possível encontrar uma posição válida.");
                }
            }

            // Atualiza o índice do último bot movido
            lastMovedBotIndex = (lastMovedBotIndex + 1) % currentPlayer.getBots().size();
            currentPlayer.setLastMovedBotIndex(lastMovedBotIndex);
        } else {
            System.out.println("Nenhum bot disponível para mover nesta rodada.");
        }
    }


    private static boolean checkGameStatus(Player player1, Player player2) {
        if (player1.getFlagPosition().equals(player2.getFlagPosition())) {
            System.out.println("Game over! Player " + player1.getPlayerName() + " wins!");
            return true;
        } else if (player2.getFlagPosition().equals(player1.getFlagPosition())) {
            System.out.println("Game over! Player " + player2.getPlayerName() + " wins!");
            return true;
        }
        return false;
    }

    private static void endGame(Player winner) {
        System.out.println("Game over! Player " + winner.getPlayerName() + " wins!");
    }


    public static boolean isValidMove(Location newPosition, Bot currentBot, Player opponentPlayer) {
        // Verificar se a nova posição está ocupada por bots adversários
        for (Bot bot : opponentPlayer.getBots()) {
            if (bot.getActualPosition().equals(newPosition) && bot.isCarryingFlag()) {
                // Se houver um bot adversário carregando a bandeira na nova posição
                currentBot.returnFlagToBase(opponentPlayer.getFlagPosition());
                return false;
            }
        }

        // Verificar se a nova posição está ocupada pelos bots do jogador atual
        for (Bot bot : currentBot.getOwner().getBots()) {
            if (!bot.equals(currentBot) && bot.getActualPosition().equals(newPosition) && bot.isCarryingFlag()) {
                // Se houver um bot da mesma equipe carregando a bandeira na nova posição
                // Ambas as bandeiras devem voltar para suas bases
                currentBot.returnFlagToBase(currentBot.getOwner().getFlagPosition());
                bot.returnFlagToBase(currentBot.getOwner().getFlagPosition());
                return false;
            }
        }
        return true;
    }
}
