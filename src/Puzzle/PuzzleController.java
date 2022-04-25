package Puzzle;

import Puzzle.Puzzle;

import java.util.List;
import java.util.Map;

/**
 *  Author: Jayson Dasher
 */
public class PuzzleController {
    private Map<String, List<Puzzle>> puzzles;
    private PuzzleView view;

    public PuzzleController(PuzzleView view){
        this.view = view;
        this.puzzles = new PuzzleReader().CreatePuzzles();
    }

    //TODO: Solve Puzzle.Puzzle
    /*
    DESC: The player must be able to enter the correct puzzle answer into the console to solve the puzzle
    RAT: This allow the player to solve a puzzle to be able to unlock and retrieve an item if the puzzle is solved.
     */
    pub


    //TODO: Puzzle.Puzzle Hint
    /*
    DESC: The player must be able to type “Puzzle.Puzzle Hint” in the console while in a puzzle to display the hint for the puzzle in the console
    RAT: This allow the player to get a hint to solve the puzzle to help  the player who stuck on solving the puzzle
     */

    /**
     *
     * @param roomNum player's current location
     * @param puzzleNum
     * @author Khamilah Nixon
     */
    public void puzzleHint(String roomNum, int puzzleNum){
        view.puzzleHint(puzzles.get(roomNum).get(puzzleNum));
    }

    //TODO: Exit Puzzle.Puzzle
    /*
    DESC: The player must be able to type “Exit Puzzle.Puzzle” in the console while in a puzzle to exit the puzzle.
    RAT: This allows the player to exit the puzzle and walk around the room or other rooms to find possible clues.
     */
    //TODO: Check for Puzzle

}
