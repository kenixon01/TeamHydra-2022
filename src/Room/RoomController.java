package Room;

import java.io.Serializable;

/**
 * Author: Brian Smithers
 */
public class RoomController implements Serializable {
    private Room model;
    private RoomView view;

    public RoomController(Room model, RoomView view)
    {
        this.model = model;
        this.view = view;
    }

    public void printRoomDescription() {
        view.roomDescription(model);
    }

    public void setModel(Room model) {
        this.model = model;
    }
}
