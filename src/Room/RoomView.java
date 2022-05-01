package Room;

import java.io.Serializable;

/**
 * Prints information for requirements of Room functions
 * @author Brian Smithers and David W. Huber
 */
public class RoomView implements Serializable {
    public void roomDescription(Room room) {
        System.out.println(room.getRoomName());
        System.out.println(room.getRoomDescription());
    }
}
