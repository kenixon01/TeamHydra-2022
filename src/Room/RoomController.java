package Room;

public class RoomController {
    private Room model;
    private RoomView view;

    public RoomController(Room model, RoomView view)
    {
        this.model = model;
        this.view = view;
    }
}