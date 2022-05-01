package Room;

import java.io.*;
import java.util.*;

/**
 * Author: Brian Smithers
 */
public class RoomReader implements Serializable{
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
}