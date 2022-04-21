//import java.util.HashMap;
//import java.util.Map;

/**
 * The Room class define a room's description and
 * its neighbors. It will reference the RoomReader class
 * and has the following attributes:
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
    private String[] neighbors_; //names of neighboring rooms
    private String description_; //short description



    public Room(String name, String description, String[] neighbors) {
        name_ = name;
        description_ = description;
        neighbors_ = neighbors;

    }


    /**
     * The look method allows the player to see their exits.
     */
    public void look() {
        System.out.println(description_);

        System.out.println("Can exit to the ");
        // traverse neighbors array
        if (!neighbors_[0].equals("-")) { // if there is spot to north
            System.out.print("N, ");
        }

        if (!neighbors_[1].equals("-")) { // if there is spot to south
            System.out.print("S, ");
        }

        if (!neighbors_[2].equals("-")) { // if there is spot to east
            System.out.print("E, ");
        }

        if (!neighbors_[3].equals("-")) { // if there is spot to west
            System.out.print("W, ");
        }

        System.out.println();
        System.out.println();


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

