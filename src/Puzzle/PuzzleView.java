package Puzzle;

/**
 * @author Khamilah Nixon
 */

import java.io.Serializable;
import Utilities.ConsoleColors;


/**
 * Author: Jayson Dasher David Huber
 */
public class PuzzleView implements Serializable {
    private ConsoleColors colors = new ConsoleColors();

    public PuzzleView() {
        colors.setTextColor("blue");
    }

    public void puzzleHint(Puzzle puzzle) {
        System.out.println(
                colors.textColor(puzzle.getHint(),"b/blue"));
    }

    public void puzzleSuccess(Puzzle puzzle) {
        System.out.println(
                colors.textColor(puzzle.getSolvedMessage(),"green"));
        System.out.println(" ");
    }

    public void puzzleDescription(Puzzle puzzle) {
        System.out.println(
                colors.textColor(puzzle.getDescription()));
    }

    public void puzzleCommands() {
        System.out.println(" ");
        System.out.println(
                colors.textColor(
                        "You may enter the solution to solve the puzzle, 'hint' to get help solving the puzzle, " +
                                "or 'exit' to leave the puzzle.", "b/blue"));
    }

    public void puzzleIncorrect(Puzzle puzzle) {
        System.out.println(
                colors.textColor(puzzle.getIncorrectMsg(),"b/red"));
    }

    public void puzzleExit() {
        System.out.println(
                colors.textColor("You have exited the puzzle and are free to explore again."));
    }

    public void lockedIn() {
        System.out.println(
                colors.textColor("Oops. Can't leave this puzzle without solving it","b/red"));
    }

    public void randomNumHigh() {
        System.out.println(
                colors.textColor("Oops. That number was too high. Try again.","b/red"));
    }

    public void randomNumLow() {
        System.out.println(
                colors.textColor("Oops. That number was too low. Try again.","b/red"));
    }

    public void numberFormatException() {
        System.out.println(
                colors.textColor("Please enter a number between 0 and 20","b/red"));
    }

    public void youDead() {
        System.out.println(
                colors.textColor("May you rest in peace.","b/red"));
    }

}
