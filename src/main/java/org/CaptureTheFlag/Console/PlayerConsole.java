package org.CaptureTheFlag.Console;

import org.CaptureTheFlag.Models.Bots.Bot;
import org.CaptureTheFlag.Models.Location.Location;
import org.CaptureTheFlag.Models.Map.Map;
import org.Estruturas.ArrayList.ArrayList;

import java.util.Scanner;

public class PlayerConsole {
    Scanner scanner = new Scanner(System.in);

    /**
     * Displays the available locations on the map.
     *
     * @param map The map containing locations.
     */
    public void displayAvailableLocations(Map<Location> map) {
        System.out.println("\nLocalizações Disponíveis:");
        int index = 1;
        for (Location location : map.getLocations()) {
            System.out.println(index + ": " + location.getName());
            index++;
        }
    }

    /**
     * Retrieves the player's choice from the console input.
     *
     * @return The player's choice.
     */
    public int getPlayerChoice() {
        if (scanner.hasNextInt()) {
            return scanner.nextInt();
        } else {
            System.out.println("Escolha inválida. Digite um número.");
            scanner.next();
            return 0;
        }
    }

    /**
     * Checks if the choice is valid within the given range.
     *
     * @param choice The choice to validate.
     * @param max    The maximum valid choice.
     * @return True if the choice is valid, false otherwise.
     */
    public boolean isValidChoice(int choice, int max) {
        return choice >= 1 && choice <= max;
    }

    /**
     * Checks if a location is occupied by any bot.
     *
     * @param location The location to check.
     * @param bots     The list of bots.
     * @return True if the location is occupied by a bot, false otherwise.
     */
    public boolean isOccupiedByBot(Location location, ArrayList<Bot> bots) {
        for (Bot bot : bots) {
            if (bot.getActualPosition().equals(location)) {
                return true;
            }
        }
        return false;
    }
}
