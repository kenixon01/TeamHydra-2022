package Room;

import java.io.*;
import java.util.*;

/**
 * Author: Brian Smithers
 */
public class RoomReader implements Serializable{
    private final String filePath;
    private final HashMap<Integer, String[]> roomReaderRoomDetailsHashMap = new HashMap<>();
    private final HashMap<Integer, String[]> roomReaderRoomKeysToUnlockRoomHashMap = new HashMap<>();

    private ArrayList<String> tempKeysRequired = new ArrayList<>();

    public RoomReader(String filePath) {
        this.filePath = filePath;
    }

    public String getFilePath() {
        return filePath;
    }

    public HashMap<Integer, String[]> getRoomReaderRoomDetailsHashMap() {
        return roomReaderRoomDetailsHashMap;
    }

    public void read() {
        try {
            File file = new File(getFilePath());

            Scanner scanner = new Scanner(file);

            int currentRoom = 1;
            while (scanner.hasNext()) {
                String roomName = scanner.nextLine();
                String isLocked = scanner.nextLine();

                String[] roomData = new String[3];
                roomData[0] = String.valueOf(currentRoom);
                roomData[1] = roomName;
                roomData[2] = isLocked;

                /*
                If the next String is not the deliminator, this is a key.
                Add this key to the tempKeysRequired list. When no more
                keys exist, make a copy of this list and assign to the
                array.
                 */
                boolean nextPass = true;
                while (scanner.hasNext() && nextPass) {
                    String next = scanner.nextLine();
                    if (!next.equalsIgnoreCase("----")) {
                        tempKeysRequired.add(next);
                    }
                    else {
                        nextPass = false;
                    }
                }
                String[] keysRequired;
                if (!tempKeysRequired.isEmpty()) {
                    keysRequired = new String[tempKeysRequired.size()];
                    for (int i = 0; i < tempKeysRequired.size(); i++) {
                        keysRequired[i] = tempKeysRequired.get(i);
                    }
                    tempKeysRequired.clear();
                }
                // No keys required to open this room
                else {
                    keysRequired = new String[1];
                    keysRequired[0] = "";
                }
                getRoomReaderRoomKeysToUnlockRoomHashMap().put(currentRoom, keysRequired);
                getRoomReaderRoomDetailsHashMap().put(currentRoom, roomData);
                currentRoom++;
            }
        }
        catch (FileNotFoundException e) {
            System.out.printf("The %s file was not found.%n", getFilePath());
        }
    }

    public HashMap<Integer, String[]> getRoomReaderRoomKeysToUnlockRoomHashMap() {
        return roomReaderRoomKeysToUnlockRoomHashMap;
    }
}