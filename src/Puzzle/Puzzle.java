
package Puzzle;

import java.io.Serializable;

/**
 * Author: Jayson Dasher
 */
public class Puzzle implements Serializable {

    private int id;
    private String name;
    private String room;
    private String description;
    private String hint;
    private String solution;
    private String solvedMessage;
    private String type;
    private Boolean isSolved;
    private int damage;
    private int hpModifier;
    private String itemDropped;
    private String incorrectMsg;

    public Puzzle(int id, String name, String description, String room, String hint, String solution, String solvedMessage, String type, int damage, int hpModifier, String itemDropped, String incorrectMsg) {
        this.id = id;
        this.name = name;
        this.room = room;
        this.description = description;
        this.hint = hint;
        this.solution = solution;
        this.solvedMessage = solvedMessage;
        this.type = type;
        this.damage = damage;
        this.hpModifier = hpModifier;
        this.itemDropped = itemDropped;
        this.incorrectMsg = incorrectMsg;
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

    public String getHint() {
        return hint;
    }

    public String getSolution() {
        return solution;
    }

    public String getSolvedMessage() {
        return solvedMessage;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Boolean getSolved() {
        return isSolved;
    }

    public void setSolved(Boolean solved) {
        isSolved = solved;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getHpModifier() {
        return hpModifier;
    }

    public String getIncorrectMsg() {
        return incorrectMsg;
    }
}
