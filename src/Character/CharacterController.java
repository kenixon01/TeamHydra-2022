package Character;

import Room.Room;

/**
 * Author: Brian Smithers
 */
public class CharacterController {
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
        view.printPlayerDetails(model.help());
    }

    /**
     * Author: Brian Smithers
     */
    public void printPlayerLocation() {
        view.printPlayerLocation(
                Room.listOfRooms.get(model.getRoomNumber()).getRoomDescription());
    }

    /**
     * Author: Brian Smithers
     */
    public void chooseCharacter() {
        view.characterSelect();
        Character.loadCharacterData(model.scanUserInput());
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

    public Character getModel() {
        return model;
    }

    public CharacterView getView() {
        return view;
    }
}
