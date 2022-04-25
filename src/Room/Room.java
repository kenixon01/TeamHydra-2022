package Room;
//import java.util.HashMap;
//import java.util.Map;

import java.util.HashMap;

/**
 * Author: Brian Smithers
 */

public class Room {
    private int roomNumber;
    private final String roomName;
    private final String roomDescription;
    protected static StringBuilder sb = new StringBuilder();

    private boolean isLocked;
    private final String[][] roomConnections;
    //private final Inventory roomInventory;
    //private final LinkedList<Monster> monstersInRooms;
    private boolean visited;

    // This variable should become its own class and call it the ListOfRooms.
    // The idea is that it is a static database that houses all the rooms.
    protected static final HashMap<Integer, Room> listOfRooms = new HashMap<>();

    // All variables
//    public Room(int roomNumber, String roomName, String roomDescription,
//                String[][] roomConnections, Inventory roomInventory,
//                boolean visited) {
//        if (roomNumber > 0) {
//            this.roomNumber = roomNumber;
//        }
//        this.roomName = roomName;
//        this.roomDescription = roomDescription;
//        this.roomConnections = roomConnections;
//        this.roomInventory = roomInventory;
//        this.visited = visited;
//        this.monstersInRooms = new LinkedList<>();
//    }

    // All variables
    public Room(int roomNumber, String roomName, String roomDescription, boolean isLocked,
                String[][] roomConnections
                /*boolean visited*/) {
        if (roomNumber > 0) {
            this.roomNumber = roomNumber;
        }
        this.roomName = roomName;
        this.roomDescription = roomDescription;
        this.isLocked = isLocked;
        this.roomConnections = roomConnections;
        //this.visited = visited;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public String getRoomName() {
        return roomName;
    }

    public String getRoomDescription() {
        return roomDescription;
    }

    public String[][] getRoomConnections() {
        return roomConnections;
    }

    public void addRoom(Room room) {
        listOfRooms.put(room.getRoomNumber(), room);
    }

    public static Room getRoom(int roomNumber) {
        return listOfRooms.getOrDefault(roomNumber, null);
    }

    public boolean isLocked() {
        return isLocked;
    }

    public void setIsLocked(boolean status) {
        this.isLocked = status;
    }

//    public Inventory getRoomInventory() {
//        return roomInventory;
//    }

    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }

//    public void addMonster(Monster monster) {
//        monstersInRooms.add(monster);
//    }
//
//    public void removeMonster() {
//        monstersInRooms.removeFirst();
//    }
//
//    public LinkedList<Monster> getMonstersInRooms() {
//        return monstersInRooms;
//    }
    /*
    private String name_; //name of room
    private String roomID_; //A room's ID need for item sorting
    private boolean isLocked_;
    private String[] neighbors_; //names of neighboring rooms
    private String description_; //short description


    public Room(String roomID, String name, boolean isLocked, String description, String[] neighbors) {
        roomID_ = roomID;
        name_ = name;
        isLocked_ = isLocked;
        description_ = description;
        neighbors_ = neighbors;

    }

    public String getRoomID_() {
        return roomID_;
    }
    public String[] getNeighbors() {
        return neighbors_;
    }

    public String getName() {
        return name_;
    }

    public boolean isLocked() {
        return isLocked_;
    }

    public String getDescription_() {
        return description_;
    }

     */
}

