package org.CaptureTheFlag.Console;

import org.CaptureTheFlag.Managements.GameSettings;
import org.Estruturas.Exceptions.EmptyCollectionException;
import org.Estruturas.Exceptions.InvalidElementException;

import java.util.Scanner;

/**
 * The {@code GameConsole} class provides a simple console interface to interact with the Capture The Flag game.
 * It allows users to choose between starting the game and exiting the application.
 * The class relies on the {@link GameSettings} class to handle game settings and initialization.
 * <p>
 * Example usage:
 * <pre>
 * GameConsole gameConsole = new GameConsole();
 * gameConsole.start();
 * </pre>
 * </p>
 * @author 8190357 - Marcelo Barbosa
 * @author 8180325 - Miguel Fonseca
 */
public class GameConsole {
    static Scanner scanner = new Scanner(System.in);

    /**
     * Starts the console interface, allowing users to choose between starting the game and exiting the application.
     *
     * @throws EmptyCollectionException if the collection is empty.
     */
    public void start() throws EmptyCollectionException, InvalidElementException {
        Scanner scanner = new Scanner(System.in);
        int mode = 1;
        do {
            System.out.print("\n*********** Menu **********\n");
            System.out.print("1 - Iniciar Jogo\n");
            System.out.print("0 - Sair\n");
            System.out.print("***************************\n");
            System.out.print("Opção: ");

            if (scanner.hasNextInt()) {
                mode = scanner.nextInt();

                switch (mode) {
                    case 0:
                        System.out.print("A sair!\n");
                        break;
                    case 1:
                        GameSettings.settings();
                        break;
                    default:
                        System.out.print("ERRO - Operação Inexistente\n");
                        break;
                }
            } else {
                scanner.next();
                System.out.println("ERRO - Insira um número válido.");
            }
        } while (mode != 0);
        scanner.close();
    }
}
