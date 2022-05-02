package Room;

import java.io.Serializable;
import Utilities.ConsoleColors;

/**
 * Prints information for requirements of Room functions
 * @author Brian Smithers and David W. Huber
 */
public class RoomView implements Serializable {
    private ConsoleColors colors = new ConsoleColors();

    public RoomView() {
        colors.setTextColor("b/white");
    }
    public void roomDescription(Room room) {
        System.out.println(
                colors.textColor(room.getRoomName()));
        System.out.println(room.getRoomDescription());
    }
}
