package Console;

import Utilities.ConsoleColors;
import Character.Character;

/**
 * This class will print any general game messages and information
 * @author Khamilah Nixon
 */
public class ConsoleView {
    public void print(String message) {
        System.out.println(message);
    }

    public void continueGame() {
        System.out.println("Save game loaded.");
    }

    public void startGame() {
        System.out.println("Do you want to start the game? [y] or [n]");
    }

    public void exitGame() {
        System.out.println("Exiting game.");
    }

    public void saveGameCreated() {
        System.out.println("NEW SAVE CREATED");
    }

    public void fileNotFound() {
        System.out.println("Save file does not exist.\nCreating a new save file.");
    }

    public void cannotSaveGame() {
        System.out.println("You cannot save here.\nProceed to a hallway to save the game.");
    }

    public void invalidCommand(String commandCategory) {
        commandCategory += " ";
        System.out.println("Invalid " + commandCategory + "command.\n");
    }

    public void mainMenu() {
        System.out.println("""
                <<=<<=<<=<<=<<=<<=<<<==>>>=>>=>>=>>=>>=>>=>>
                         ESCAPE FROM HONTHIN ESTATE
                         <<=<<=<<=<<<==>>>=>>=>>=>>
                
                                   
                → Start
                → Continue
                → Exit
                """);
    }

    public void verifyCharacter(Character character) {
        System.out.println("Would you like to continue as " +
                character.getName() + "\nThis decision is final. [y] or [n]"
        );
    }

    public void gameDescription() {
        System.out.println("""
                On an alternate earth in the year 1515. A mysterious cataclysm has claimed the world, giving rise to
                beasts, monsters, zombies, demons, and everything in between. On year 5 of the apocalypse, our story
                picks up following a group of four survivors - who between uncertain circumstances - have come to
                travel together in an attempt to hold onto their humanity while they still have it.\s
                                
                They happen to stumble upon a mansion in their travels, and decide to send one of themselves in to
                scope out the front area in order to make sure it is clear to move in for a scavenge and loot run.
                Unknown to them, this particular abode belongs to the Honthin family, a heretical cult-like lineage
                that has been rumored to be the cause of all of this mess.\s
                           
                When the chosen survivor walks up to the house and opens the front door to take a look 
                inside, an unnatural force from behind pushes them in and slams the door shut with a loud knock 
                as the door is sealed.\s
                                
                No sound can be heard from outside, and there is no sign of the rest of the group through the 
                windows. What happened? Where are the others? The only way is forward, since we now have to 
                Escape from Honthin Estate.\s
                """);
    }

    public void saveGame() {
        System.out.println("Saved.");
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
