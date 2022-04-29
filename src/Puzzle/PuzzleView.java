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

    public void puzzleDescription(Puzzle puzzle) {
        System.out.println(puzzle.getDescription());
    }

    public void puzzleCommands() {
        System.out.println("You may enter the solution to solve the puzzle, 'hint' to get help solving the puzzle, or 'exit' to leave the puzzle.");
    }

    public void puzzleIncorrect(Puzzle puzzle) {
        System.out.println(puzzle.getIncorrectMsg());
    }

    public void puzzleExit() {
        System.out.println("You have exited the puzzle and are free to explore again.");
    }

    public void lockedIn() {
        System.out.println("Oops. Can't leave this puzzle without solving it");
    }

    public void randomNumHigh() {
        System.out.println("Oops. That number was too high. Try again.");
    }

    public void randomNumLow() {
        System.out.println("Oops. That number was too low. Try again.");
    }

    public void numberFormatException() {
        System.out.println("Please enter a number between 0 and 20");
    }

}
