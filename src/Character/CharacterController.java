package Character;

import Room.Room;

import java.io.Serializable;

/**
 * Author: Brian Smithers
 */
public class CharacterController implements Serializable {
    private Character model;
    private CharacterView view;

    public CharacterController(Character model, CharacterView view) {
        this.model = model;
        this.view = view;
    }
    /**
     * Author: Brian Smithers
     */
    public void printHelp() {
        view.printHelp(model.help());
    }

    /**
     * Author: Brian Smithers
     */
    public void printPlayerDetails() {
        view.printPlayerDetails(model.toString());
    }

    /**
     * Author: Brian Smithers
     */
    public void printPlayerLocation() {
        view.printPlayerLocation(
                Room.listOfRooms.get(model.getRoomNumber()).getRoomDescription());
    }

    /**
     * author: Brian Smithers
     * 
     */
    public void move(String direction) {
        view.failedRoomTraversal(model.traverseRooms(direction));
    }

    /**
     * @author Khamilah Nixon
     */
    public void printInventory() {
        view.printInventory(model);
    }

    /**
     * Author: Brian Smithers
     */
    public void equip(String itemName) {
        System.out.println(getModel().equipItem(itemName));
    }

    /**
     * Author: Brian Smithers
     */
    public void unEquipItem(String itemName) {
        System.out.println(getModel().unEquipItem(itemName));
    }

    // TODO this may need to be removed after testing //
    public void pickUpItem(String item) {
        Room room = Room.getRoom(model.getRoomNumber());
        model.getInventoryController().transferItem(
                "Room", "Pick Up",
                room.getInventoryController().getItemInventory(), item.strip());
    }

    public Character getModel() {
        return model;
    }

    public CharacterView getView() {
        return view;
    }
}
