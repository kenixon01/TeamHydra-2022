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

    /**
     * @param input player's answer
     * @param roomID player's current location
     * @param puzzleID puzzle id
     * @author Khamilah Nixon
     */
    public void solvePuzzle(String input, int roomID, int puzzleID) {
        if(input.equalsIgnoreCase(puzzles.get(roomID + "").get(puzzleID).getSolution())) {
            view.puzzleSuccess(puzzles.get(roomID + "").get(puzzleID));
        }
    }

    //TODO: Puzzle.Puzzle Hint
    /*
    DESC: The player must be able to type “Puzzle.Puzzle Hint” in the console while in a puzzle to display the hint for the puzzle in the console
    RAT: This allow the player to get a hint to solve the puzzle to help  the player who stuck on solving the puzzle
     */

    /**
     * @param roomID player's current location
     * @param puzzleID puzzle id
     * @author Jayson Dasher and Khamilah Nixon
     */
    public void puzzleHint(int roomID, int puzzleID){
        view.puzzleHint(puzzles.get(roomID + "").get(puzzleID));
    }

    /**
     * @return puzzles
     * @author Khamilah Nixon
     */
    public Map<String, List<Puzzle>> getPuzzles() {
        return puzzles;
    }
//TODO: Exit Puzzle.Puzzle
    /*
    DESC: The player must be able to type “Exit Puzzle.Puzzle” in the console while in a puzzle to exit the puzzle.
    RAT: This allows the player to exit the puzzle and walk around the room or other rooms to find possible clues.
     */
    public void exitPuzzle(String input)
    {
        if(input.equalsIgnoreCase("Exit Puzzle"))
        {

        }

    }
    //TODO: Check for Puzzle

}
