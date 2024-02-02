package org.CaptureTheFlag.Console;

import org.CaptureTheFlag.Models.Bots.Bot;
import org.CaptureTheFlag.Models.Location.Location;
import org.CaptureTheFlag.Models.Map.Map;
import org.Estruturas.ArrayList.ArrayList;

import java.util.Scanner;

public class PlayerConsole {

    Scanner scanner = new Scanner(System.in);

    public void displayAvailableLocations(Map<Location> map) {
        System.out.println("\nLocalizações Disponíveis:");
        for (Location location : map.getLocations()) {
            System.out.println(location.getId() + ": " + location.getName());
        }
    }

    public int getPlayerChoice() {
        if (scanner.hasNextInt()) {
            return scanner.nextInt();
        } else {
            System.out.println("Escolha inválida. Digite um número.");
            scanner.next();
            return 0;
        }
    }

    public boolean isValidChoice(int choice, int max) {
        return choice >= 1 && choice <= max;
    }

    public boolean isOccupiedByBot(Location location, ArrayList<Bot> bots) {
        for (Bot bot : bots) {
            if (bot.getActualPosition().equals(location)) {
                return true;
            }
        }
        return false;
    }
}
