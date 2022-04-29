package Puzzle;

/**
 * @author Khamilah Nixon
 */

/**
 * Author: Jayson Dasher David Huber
 */
public class PuzzleView {

    public void puzzleHint(Puzzle puzzle) {
        System.out.println(puzzle.getHint());
    }

    public void puzzleSuccess(Puzzle puzzle) {
        System.out.println(puzzle.getSolvedMessage());
        System.out.println("");
    }

    public void puzzleExit(Puzzle puzzle) {
        System.out.println("Exited puzzle");
    }

    public void puzzleDescription(Puzzle puzzle) {
        System.out.println(puzzle.getDescription());
    }

    public void puzzleCommands() {
        System.out.println("You may enter the solution to solve the puzzle, 'hint' to get help solving the puzzle, or 'exit' to leave the puzzle.");
    }

    public void puzzleIncorrect() {
        System.out.println("Sorry that is incorrect. Might want to use a hint.");
    }

    public void puzzleSolved(Puzzle puzzle) {
        System.out.println();
    }

    public void puzzleExit() {
        System.out.println("You have exited the puzzle and are free to explore again.");
    }

    public void lockedIn() {
        System.out.println("Oops. Can't leave this puzzle without solving it");
    }

    public void puzzleDamage(){
        System.out.println("The bottle picks up one of the many glass shards and slits your wrists. You lose 20 health");
        System.out.println("Try again.");
    }

    public void randomNumHigh(){
        System.out.println("Oops. That number was too high. Try again.");
    }

    public void randomNumLow(){
        System.out.println("Oops. That number was too low. Try again.");
    }

}
