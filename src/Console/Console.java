package Console;

import java.util.Scanner;

/**
 * Author: Brian Smithers
 */
public class Console {
    private String item = "";
    private String input = "";

    public String getInput() {
        return input;
    }

    public String getItem() {
        return item;
    }

    public void enterCommand() {
        input = "";
        Scanner scanner = new Scanner(System.in);
        input = scanner.nextLine().toLowerCase();
    }

    /**
     * This method will verify if the player enters a valid
     * menu option in the console
     * @return the player's menu choice
     * @author Khamilah Nixon
     */
    public String menuInputValidator(String[] menuOptions) {
        enterCommand();
        for(String option : menuOptions) {
            if(input.equalsIgnoreCase(option)) {
                return input;
            }
        }
        return input;
    }

    // This is used to validate input that is done to an object
    public String inputValidator() {
        String[] inputValidator = input.split("\\s+");
        item = "";
        if (inputValidator[0].equalsIgnoreCase("pickup")
                || inputValidator[0].equalsIgnoreCase("inspect")
                || inputValidator[0].equalsIgnoreCase("drop")
                || inputValidator[0].equalsIgnoreCase("use")
                || inputValidator[0].equalsIgnoreCase("equip")
                || inputValidator[0].equalsIgnoreCase("unequip")
                || inputValidator[0].equalsIgnoreCase("north")
                || inputValidator[0].equalsIgnoreCase("east")
                || inputValidator[0].equalsIgnoreCase("south")
                || inputValidator[0].equalsIgnoreCase("west")
                || inputValidator[0].equalsIgnoreCase("help")
                || inputValidator[0].equalsIgnoreCase("attack")
                || inputValidator[0].equalsIgnoreCase("inventory")
                || inputValidator[0].equalsIgnoreCase("save")
                || inputValidator[0].equalsIgnoreCase("resume")
                || inputValidator[0].equalsIgnoreCase("block")
                || inputValidator[0].equalsIgnoreCase("dodge")
                || inputValidator[0].equalsIgnoreCase("hint")
                || inputValidator[0].equalsIgnoreCase("examine")
                || inputValidator[0].equalsIgnoreCase("explore")
        ) {
            input = inputValidator[0];
            // recombine string to pass for method
            for (int i = 1; i < inputValidator.length; i++) {
                item += inputValidator[i] + " ";
            }
            return input;
        }
        return input;
    }
}
