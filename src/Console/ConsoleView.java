package Console;

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

    public void gameCrash(String message) {
        System.out.println("Error running game: " + message);
    }

    public void invalidCommand() {
        System.out.println("Invalid command");
    }

    public void mainMenu() {
        System.out.println("""
                ESCAPE FROM HONTHIN ESTATE
                <<=<<=<<=<<<==>>>=>>=>>=>>
                -> Start
                -> Continue
                -> Exit
                """);
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

    public void gameOver() {
        System.out.println("Game Over");
    }
}
