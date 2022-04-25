package Room;

/**
 * Prints information for requirements of Room functions
 * @author David W. Huber
 */
public class RoomView {
    public void roomDescription(Room room) {
        System.out.println(room.getRoomName());
        System.out.println(room.getRoomDescription());
    }
}
