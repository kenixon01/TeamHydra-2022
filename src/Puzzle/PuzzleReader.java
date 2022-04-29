package Puzzle;

import java.io.File;
import java.util.*;

/**
 * Author: Jayson Dasher
 */
public class PuzzleReader {
    private String _puzzlesFilePath;

    public PuzzleReader() {
        this._puzzlesFilePath = (System.getProperty("user.dir") + "/src/Puzzle/");
    }

    private Scanner GetFileInputString() {
        Scanner reader;
        File newFile = new File(_puzzlesFilePath + "puzzles.txt");

        while (true) {
            try {
                reader = new Scanner(newFile);
                break;
            } catch (Exception e) {
                System.out.println("Error: File not found. RE-hard code your puzzles file name.");
            }
        }

        return reader;
    }

    public Map<String, List<Puzzle>> CreatePuzzles() {
        Scanner reader = this.GetFileInputString();

        Map<String, List<Puzzle>> puzzles = new HashMap<>();
        Puzzle currPuzzle; // dummy

        while (reader.hasNextLine()) { // reading through file loop
            String currLine = reader.nextLine();
            if (currLine.length() > 0 && currLine.charAt(0) == '-') {
                continue;
            }

            if (currLine.equals("")) { // New Puzzle.Puzzle
                int puzzleNum = reader.nextInt();
                reader.nextLine();
                String puzzleName = reader.nextLine();
                String puzzleRoom = reader.nextLine();
                String puzzleDesc = reader.nextLine();
                String puzzleHint = reader.nextLine();
                String puzzleSolution = reader.nextLine();
                String solvedMessage = reader.nextLine();
                String type = reader.nextLine();
                int puzzleDamage = reader.nextInt();
                int puzzleHpModifier = reader.nextInt();
                reader.nextLine();
                String puzzleItem = reader.nextLine();

                currPuzzle = new Puzzle(puzzleNum, puzzleName, puzzleDesc, puzzleRoom, puzzleHint, puzzleSolution, solvedMessage, type, puzzleDamage, puzzleHpModifier, puzzleItem);

                //Check if a previous puzzle has been added to a room.
                if (puzzles.get(currPuzzle.getRoom()) != null) {
                    //Room exists in puzzles
                    puzzles.get(currPuzzle.getRoom()).add(currPuzzle);
                } else {
                    // New list of puzzles being added to the Map<>() so we need to initialize the value for the Map entry
                    List roomPuzzles = new ArrayList<Puzzle>();
                    roomPuzzles.add(currPuzzle);
                    puzzles.put(currPuzzle.getRoom(), roomPuzzles);
                }

            }
        }

        return puzzles;
    }
}

class PuzzleReaderTester {
    public static void main(String[] args) {
        PuzzleReader puzzleReader = new PuzzleReader();
        puzzleReader.CreatePuzzles();
    }
}