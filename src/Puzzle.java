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
