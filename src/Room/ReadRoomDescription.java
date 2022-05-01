package Room;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Scanner;

/**
 * Author: Brian Smithers
 */
public class ReadRoomDescription  implements Serializable {
    private StringBuilder roomDescription;
    private final String roomDescriptionFilePath;
    private final HashMap<Integer, String> roomDescriptionHashMap = new HashMap<>();
    private int roomNumber;

    public ReadRoomDescription(String roomDescriptionFilePath) {
        this.roomDescriptionFilePath = roomDescriptionFilePath;
    }

    // Authors: Brian and Khamilah
    // Read in room's description
    public void read() {
        try {
            File file = new File(roomDescriptionFilePath);
            Scanner scanner = new Scanner(file);
            roomDescription = new StringBuilder();

            roomNumber = 1;
            while (scanner.hasNext()) {
                String next = scanner.nextLine();
                if (!next.equalsIgnoreCase("----")) {
                    roomDescription.append(next).append("\n");
                }
                else {
                    roomDescription.setLength(roomDescription.length() - 1); // removes last \n
                    roomNumber++;
                    roomDescription.setLength(0); // reset Stringbuilder
                }
                roomDescriptionHashMap.put(roomNumber, roomDescription.toString().trim());
            }
        }
        catch (FileNotFoundException ex) {
            System.out.println(getClass() + " " + ex + " while reading " +
                    " roomDescriptionFilePath.txt");
        }
    }

    public HashMap<Integer, String> getRoomDescriptionHashMap() {
        return roomDescriptionHashMap;
    }

    public int getRoomNumber() {
        return roomNumber;
    }
}
