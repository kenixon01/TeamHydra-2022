package Room;
//import java.util.HashMap;
//import java.util.Map;

import Inventory.*;

import java.io.Serializable;
import java.util.HashMap;

/**
 * Author: Brian Smithers
 */

public class Room implements Serializable {
    private int roomNumber;
    private final String roomName;
    private final String roomDescription;
    protected static StringBuilder sb = new StringBuilder();

    private boolean isLocked;
    private final String[][] roomConnections;
    private final InventoryController inventoryController;
    private final String[] keysRequired;

    // The idea is that it is a static database that houses all the rooms.
    public static final HashMap<Integer, Room> listOfRooms = new HashMap<>();


    public Room(int roomNumber, String roomName, String roomDescription, boolean isLocked,
                String[][] roomConnections, InventoryController inventoryController,
                String[] keysRequired) {
        if (roomNumber > 0) {
            this.roomNumber = roomNumber;
        }
        this.roomName = roomName;
        this.roomDescription = roomDescription;
        this.isLocked = isLocked;
        this.roomConnections = roomConnections;
        this.inventoryController = inventoryController;
        this.keysRequired = keysRequired;
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

    public static void addRoom(Room room) {
        listOfRooms.put(room.getRoomNumber(), room);
    }

    public static Room getRoom(int roomNumber) {
        return listOfRooms.getOrDefault(roomNumber, null);
    }

    public boolean isLocked() {
        return isLocked;
    }

//    public Inventory getRoomInventory() {
//        return roomInventory;
//    }

    public InventoryController getInventoryController() {
        return inventoryController;
    }

    public String[] getKeysRequired() {
        return keysRequired;
    }
}

