package org.CaptureTheFlag;

import org.CaptureTheFlag.Console.GameConsole;
import org.Estruturas.Exceptions.EmptyCollectionException;
import org.Estruturas.Exceptions.InvalidElementException;

/**
 * The main class for the Capture The Flag game. It initializes and starts the game console.
 * <p>
 * This class contains the main method responsible for launching the game. It creates an instance of the
 * {@link GameConsole} class and calls its {@link GameConsole#start()} method to begin the game.
 * </p>
 *
 * @author 8190357 - Marcelo Barbosa
 * @author 8180325 - Miguel Fonseca
 */
public class Main {

    /**
     * The main entry point for the Capture The Flag game.
     *
     * @param args command-line arguments (not used)
     * @throws EmptyCollectionException if an attempt is made to access an element from an empty collection
     */
    public static void main(String[] args) throws EmptyCollectionException, InvalidElementException {
        GameConsole console = new GameConsole();
        console.start();
    }
}