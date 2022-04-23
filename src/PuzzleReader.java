import java.io.File;
import java.util.*;

/**
 *  Author: Jayson Dasher
 */
public class PuzzleReader {
    private String _puzzlesFilePath;

    public PuzzleReader() { this._puzzlesFilePath = (System.getProperty("user.dir") + "\\src\\PuzzleTextFiles\\"); }

    private Scanner GetFileInputString() {
        Scanner reader;
        File newFile = new File(_puzzlesFilePath + "puzzles.txt");

        while(true) {
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

            /*
            -Puzzle Number-
            -Puzzle Name-
            -Puzzle Description-
            -Room the Puzzle is located in-
            -Puzzle Hint-
            -Puzzle Solution-
            -USE A BLANK LINE AS A SEPARATOR-
             */

            if (currLine.equals("")) { // New Puzzle
                int puzzleNum = reader.nextInt();
                reader.nextLine();
                String puzzleName = reader.nextLine();
                String puzzleDesc = reader.nextLine();
                String puzzleRoom = reader.nextLine();
                String puzzleHint = reader.nextLine();
                String puzzleSolution = reader.nextLine();
                currPuzzle = new Puzzle(puzzleNum, puzzleName, puzzleDesc, puzzleRoom, puzzleHint, puzzleSolution);

                //Check if a previous item has been added to a room.
                if(puzzles.get(currPuzzle.getRoom()) != null) {
                    //Room exists in items
                    puzzles.get(currPuzzle.getRoom()).add(currPuzzle);
                }
                else {
                    // New list of items being added to the Map<>() so we need to initialize the value for the Map entry
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