package Character;

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
        view.printPlayerLocation(model.getLocation());
    }

    /**
     * Author: Brian Smithers
     */
    public void chooseCharacter() {
        view.characterSelect();
        Character.loadCharacterData(model.scanUserInput());
    }
}
