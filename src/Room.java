//import java.util.HashMap;
//import java.util.Map;

/**
 * The Room class define a room's description and
 * its neighbors. It will reference the RoomReader class
 * and has the following attributes:
 *      Room ID
 *      Room Name
 *      Room Description
 *      Neighboring Rooms or Hallways
 * @Since 1.0
 * @author David W. Huber
 * @version 1.0
 */

public class Room
{
    private String name_; //name of room
    private String roomID_; //A room's ID need for item sorting
    private String[] neighbors_; //names of neighboring rooms
    private String description_; //short description


    public Room(String roomID, String name, String description, String[] neighbors) {
        roomID_ = roomID;
        name_ = name;
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

    public String getDescription_() {
        return description_;
    }
}

