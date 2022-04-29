package Puzzle;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * Author: Jayson Dasher and David Huber
 */
public class PuzzleController {
    public Map<String, List<Puzzle>> puzzles; //Named puzzles but is the model
    private PuzzleView view;


    public PuzzleController(PuzzleView view) {
        this.view = view;
        this.puzzles = new PuzzleReader().CreatePuzzles();
    }

    /**
     * @return puzzles
     * @author Khamilah Nixon
     */
    public Map<String, List<Puzzle>> getPuzzles() {
        return puzzles;
    }

    public void puzzleSolved(Puzzle puzzle) {
        //print puzzlesolved message
        view.puzzleSuccess(puzzle);
        //set puzzle to solved
        puzzle.setSolved(true);
    }

    /**
     * Author: Jayson
     */
    //method referenced from main controller (rest of the puzzle interaction is self contained)
    public void checkForPuzzle(int roomID) {
        //if there is a puzzle in room, start puzzle interaction, else continue
        if (puzzles.containsKey(Integer.toString(roomID))) {
            //if puzzle in first key index is not solved yet, start puzzle
            if (puzzles.get(Integer.toString(roomID)).get(0).getSolved() == false) {
                //passes the puzzle for the room to the startPuzzle method.
                startPuzzle(puzzles.get(Integer.toString(roomID)).get(0));
            }
            //if keysize is 2 (holding two puzzles) run second puzzle interaction
            if (puzzles.get(Integer.toString(roomID)).size() == 2) {
                if (puzzles.get(Integer.toString(roomID)).get(1).getSolved() == false) {
                    //passes the puzzle for the room to the startPuzzle method.
                    startPuzzle(puzzles.get(Integer.toString(roomID)).get(1));
                }
            }
        }
    }

    /**
     * Author: Jayson
     */
    public void startPuzzle(Puzzle puzzle) {
        //print puzzle description
        view.puzzleDescription(puzzle);
        //print command options
        view.puzzleCommands();

        while (true) {
            //scan for user input
            Scanner reader = new Scanner(System.in);
            String userInput = reader.nextLine();
            if (userInput.equalsIgnoreCase("hint")) {
                view.puzzleHint(puzzle);
                continue;
            }
            if (userInput.equalsIgnoreCase("exit")) {
                view.puzzleExit();
                //TODO: maybe change requirement to lock player in puzzle instead of the room for puzzle #3?

                break;
            }

            //if the puzzle in this room is an Item Giver type
            if (puzzle.getType().equalsIgnoreCase("Item Giver")) {
                //if correct solution is inputted
                if (puzzle.getSolution().equalsIgnoreCase(userInput)) {
                    puzzleSolved(puzzle); //print message and set puzzle to solved
                    //TODO: address how we are going to handle the item associated. Drop in room? Add to inventory? (puzzle #1,#4,#7)

                    break; //break from puzzle interaction loop
                }
            }
            //if the puzzle in this room is a stat modifier
            if (puzzle.getType().equalsIgnoreCase("Stat Modifier")) {
                //if correct solution is inputted
                if (puzzle.getSolution().equalsIgnoreCase(userInput)) {
                    puzzleSolved(puzzle); //print message and set puzzle to solved
                    //TODO: handle stat modifier type (puzzle #6) (adds 15 hp to players total healthpool)

                    break; //break from puzzle interaction loop
                }
            }
            //if the puzzle in this room is a Monster Unlocker
            if (puzzle.getType().equalsIgnoreCase("Monster Unlocker")) {
                //if correct solution is inputted
                if (puzzle.getSolution().equalsIgnoreCase(userInput)) {
                    puzzleSolved(puzzle); //print message and set puzzle to solved
                    //TODO: handle Monster Unlocker type (puzzle #2) (must complete puzzle first before encountering monster in this room(room6))

                    break; //break from puzzle interaction loop
                }
            }
            //if the puzzle in this room is a room locker
            if (puzzle.getType().equalsIgnoreCase("Room Locker")) {
                //if correct solution is inputted
                if (puzzle.getSolution().equalsIgnoreCase(userInput)) {
                    puzzleSolved(puzzle); //print message and set puzzle to solved
                    //TODO: handle Room Locker type (puzzle #3) (once started, player can exit puzzle, but not room until the puzzle is solved)

                    break; //break from puzzle interaction loop
                }
                //TODO: if answer is wrong, player will be told if the number is too high or too low and keep guessing until they are correct.
            }
            //if the puzzle in this room is a room locker
            if (puzzle.getType().equalsIgnoreCase("Double Threat")) {
                //if correct solution is inputted
                if (puzzle.getSolution().equalsIgnoreCase(userInput)) {
                    puzzleSolved(puzzle); //print message and set puzzle to solved
                    //TODO: handle Double Threat type (puzzle #5) (drops item when solved, if incorrect, does damage to player)

                    break; //break from puzzle interaction loop
                }
            } else { //input doesn't match an available command or puzzle solution
                //TODO: if puzzle type is double threat, deal damage to player
                view.puzzleIncorrect();
            }
        }
    }
}

class PuzzleControllerTester {
    public static void main(String[] args) {
        PuzzleView view = new PuzzleView();
        PuzzleController puzzleController = new PuzzleController(view);
        //command to call method from main controller (int roomID as the argument being passed)
        puzzleController.checkForPuzzle(6);
//        puzzleController.puzzles.get(Integer.toString(6)).get(0).getSolved();
    }
}
