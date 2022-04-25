//package Puzzle;
//
//import Monster.Monster;
//
//import java.util.HashMap;
//
//public class Puzzle {
//    private int id,roomID;
//    private String name, description, hint, solution;
//    private boolean isSolved;
//
//    public Puzzle(int id, int roomID, String name, String description, String hint, String solution, boolean isSolved) {
//        this.id = id;
//        this.roomID = roomID;
//        this.name = name;
//        this.description = description;
//        this.hint = hint;
//        this.solution = solution;
//        this.isSolved = isSolved;
//    }
//
//    public static HashMap<Integer,Puzzle> createPuzzles() {
//        HashMap<Integer, Puzzle> hashMap = new HashMap<>();
//        PuzzleReader puzzleReader = new PuzzleReader();
//        int roomNum = 1;
//        for(int i = 0; i < 7; i++) {
//            int id = puzzleReader.getID();
//            int roomId = puzzleReader.getRoomId();
//            int name = puzzleReader.getName();
//            String description = puzzleReader.getDescription();
//            String hint = puzzleReader.getHint();
//            String solution = puzzleReader.getSolution();
//            boolean isSolved = puzzleReader.isSolved();
//        }
//    }
//    public void setId(int id) {
//        this.id = id;
//    }
//
//    public void setRoomID(int roomID) {
//        this.roomID = roomID;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public void setDescription(String description) {
//        this.description = description;
//    }
//
//    public void setHint(String hint) {
//        this.hint = hint;
//    }
//
//    public void setSolution(String solution) {
//        this.solution = solution;
//    }
//
//    public void setSolved(boolean solved) {
//        isSolved = solved;
//    }
//}
//
//
package Puzzle;
/**
 * TODO Recode this entirely
 * Issues: puzzle doesn't check to see if solved
 * Puzzle doesn't return item if solved
 *
 */

/**
 *  Author: Jayson Dasher
 */
public class Puzzle {

    private int id;
    private String name;
    private String room;
    private String description;
    private String hint;
    private String solution;
    private Boolean isSolved;

    public Puzzle(int id, String name, String room, String description, String hint, String solution) {
        this.id = id;
        this.name = name;
        this.room = room;
        this.description = description;
        this.hint = hint;
        this.solution = solution;
        this.isSolved = false;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getHint() {
        return hint;
    }

    public void setHint(String hint) {
        this.hint = hint;
    }

    public String getSolution() {
        return solution;
    }

    public void setSolution(String solution) {
        this.solution = solution;
    }

}
