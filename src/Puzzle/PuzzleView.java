package Puzzle;

/**
 * @author Khamilah Nixon
 */
public class PuzzleView {
    public void puzzleHint(Puzzle puzzle) {
        System.out.println(puzzle.getHint());
    }

    public void puzzleSuccess(Puzzle puzzle) {
        System.out.println("Puzzle complete");
    }

    public void puzzleExit(Puzzle puzzle)
    {
        System.out.println();
    }
}
