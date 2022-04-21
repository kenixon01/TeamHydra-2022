import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

/**
 * The RoomReader class will build the environment of the game.
 *  Upon building, the various rooms and their respected descriptions
 *  will have been read from the text file.
 *  All rooms with have the following
 *     Room Name
 *     Room Description
 *     Neighboring Rooms or Hallways
 *  @since 1.0
 *  @author David W. Huber
 *  @version 1.0
 */



public class RoomReader
{
    public static HashMap<String, Room> roomReader() {
        try {

            BufferedReader reader = new BufferedReader(new FileReader("Rooms_1"));
            String line = reader.readLine();
            HashMap<String, Room> rooms = new HashMap<String, Room>();

            while (line != null) { // while we can still read from file
                String name = line;

                line = reader.readLine();
                /**
                 * This reads from the text file and makes
                 * assigns the neighboring rooms accordingly
                 */
                String[] neighbors = line.split(",");
                for (int i = 0; i < neighbors.length; i++) {
                    neighbors[i] = neighbors[i];
                }

                line = reader.readLine();
                String description = "";
                /**
                 * The description of the rooms could be
                 * longer than one line. This allows us to
                 * read multiple lines and stopping when it
                 * hits END.
                 */
                while (!line.equals("END")) { // while reader has not hit "END"

                    description = description + line + '\n';
                    line = reader.readLine();
                }

                // put new room object and rooms name in HashMap
                rooms.put(name, new Room(name, description, neighbors));

                line = reader.readLine();
            }

            return rooms;
        } catch (IOException e) {
            System.out.println("File could not be accessed");
        }
        return null;
    }
}
