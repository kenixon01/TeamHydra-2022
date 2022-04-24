package Console;

import Character.Character;
import Item.Item;
import Monster.Monster;
import Puzzle.Puzzle;
import Room.Room;

public class ConsoleView {
    public void print(String message) {
        System.out.println(message);
    }

    public void invalidCommand() {
        System.out.println("Invalid command");
    }

    public void menu() {
        System.out.println("ESCAPE FROM HONTHIN ESTATE");
        System.out.println("<<=<<=<<=<<<==>>>=>>=>>=>>");
        System.out.println("-> Start");
        System.out.println("-> Continue");
        System.out.println("-> Exit");
    }

    public void gameDescription() {

    }

    public void playerDescription(Character player) {
        System.out.println(player.getDescription());
    }

    public void roomDescription(Room room) {
        System.out.println(room.getDescription_());
    }

    public void monsterDescription(Monster monster) {
        System.out.println(monster.getDESCRIPTION());
    }

    public void puzzleDescription(Puzzle puzzle) {
        System.out.println(puzzle.getDescription());
    }

    public void itemDescription(Item item) {
        System.out.println(item.get_itemDescription());
    }

    public void playerStats(Character player) {
        System.out.println("Your Stats:");
        System.out.println("Dodge Chance:\t" + player.getDodgeChance());
    }

    public void commandList(Character player) {
        System.out.println(player.help());
    }

    public void inventory(Character player) {
        System.out.println("Inventory:");
        System.out.println(player.getPlayerItemInventory());
    }

    /*
    public void playerLocation(Character player) {
        System.out.println("You are in " + player.getLocation());
    }
     */
}
