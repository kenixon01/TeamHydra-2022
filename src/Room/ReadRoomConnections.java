package Room;

import Item.Item;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

/**
 * Author: Brian Smithers
 */
public class ReadRoomConnections {

    private final String filePath;
    private final HashMap<Integer, String[][]> hashMap = new HashMap<>();

    public ReadRoomConnections(String filePath) {
        this.filePath = filePath;
    }

    public String getFilePath() {
        return filePath;
    }

    public HashMap<Integer, String[][]> getHashMap() {
        return hashMap;
    }

    public void read() {
        try {
            File file = new File(getFilePath());

            Scanner scanner = new Scanner(file);

            int currentRoom = 0;
            while (scanner.hasNext()) {
                currentRoom++; // init to 1 for hashmap

                String line = scanner.nextLine();

                String[] words = line.split("\\s+");

                // Use word count to determine number of dimensions in 2D array
                int wordCount = words.length;
                int oneDimensionCount = wordCount / 2;

                // Init new array that is custom for the number of room connections
                String[][] roomData = new String[oneDimensionCount][2];

                LinkedList<String> arrayCopy = new LinkedList<>(List.of(words));

                // Fill 2D array with room connection data in order
                for (int i = 0; i < roomData.length; i++) {
                    for (int j = 0; j < roomData[i].length; j++) {
                        roomData[i][j] = arrayCopy.pop();
                    }
                }
                getHashMap().put(currentRoom, roomData);
            }
        }
        catch (FileNotFoundException e) {
            System.out.printf("The %s file was not found.%n", getFilePath());
        }
    }
}
