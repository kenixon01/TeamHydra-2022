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
    public void printHelp(Character model) {
        view.printHelp(model.help());
    }

    /**
     * Author: Brian Smithers
     */
    public void printPlayerDetails(Character model) {
        view.printPlayerDetails(model.help());
    }
}
