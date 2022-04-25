package Room;

import java.io.*;
import java.util.*;

/**
 * Author: Brian Smithers
 */
public class RoomReader {
    private final String filePath;
    private final HashMap<Integer, String[]> hashMap = new HashMap<>();

    private int currentRoom = 0;
    private String roomName;
    private String isLocked;

    public RoomReader(String filePath) {
        this.filePath = filePath;
    }

    public String getFilePath() {
        return filePath;
    }

    public HashMap<Integer, String[]> getHashMap() {
        return hashMap;
    }

    public void read() {
        try {
            File file = new File(getFilePath());

            Scanner scanner = new Scanner(file);

            currentRoom = 1;
            while (scanner.hasNext()) {
                roomName = scanner.nextLine();
                isLocked = scanner.nextLine();
                scanner.nextLine();

                String[] roomData = new String[3];
                roomData[0] = String.valueOf(currentRoom);
                roomData[1] = roomName;
                roomData[2] = isLocked;

                getHashMap().put(currentRoom, roomData);
                currentRoom++;
            }
        }
        catch (FileNotFoundException e) {
            System.out.printf("The %s file was not found.%n", getFilePath());
        }
    }


    /*
    public static TreeMap<Integer, Room> roomReader() {
        try {

            BufferedReader reader = new BufferedReader(new FileReader("src/Room/RoomTextFile/Rooms_1.txt"));
            String line = reader.readLine();
            TreeMap<Integer, Room> rooms = new TreeMap<Integer, Room>();
            int key = 1;
            while (line != null) { // while we can still read from file
                String roomID = line;

                line = reader.readLine();

                String name = line;

                line = reader.readLine();

                boolean roomLocked = Boolean.parseBoolean(line);

                line = reader.readLine();
                /**
                 * This reads from the text file and makes
                 * assigns the neighboring rooms accordingly
                 */
    /*
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
    /*
                while (!line.equals("END")) { // while reader has not hit "END"

                    description = description + line + '\n';
                    line = reader.readLine();
                }

                // put new room object and rooms name in HashMap
                rooms.put(key, new Room(roomID,name, roomLocked, description, neighbors));

                line = reader.readLine();
                key++;
            }
            return rooms;
        } catch (IOException e) {
            System.out.println("File could not be accessed");
        }
        return null;
    }
   /*
     */
}