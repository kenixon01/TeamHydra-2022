package Utilities;

import java.io.Serializable;
import java.util.Arrays;

/**
 * The ConsoleColors class describes the valid ASCII colors, and applies those colors to
 * the text and background of any String value that is printed in the console.
 * @author Khamilah Nixon
 * @since 1.0
 * @version 1.0
 */

public final class ConsoleColors implements Serializable {

    /**
     * A String's background color.
     */
    private String backgroundColor;

    /**
     * A String's text color.
     */
    private String textColor;

    /**
     * The color code to reset the text or background color to its default value.
     */
    private final String ANSI_RESET = "\u001B[0m";

    /**
     * A 2D array of all the ANSI color values
     */
    private final String[][]ANSI_COLOR_NAME = new String[][]{
            {"BLACK", "RED","GREEN","YELLOW","BLUE","PURPLE","CYAN","WHITE",
            "BBLACK", "BRED", "BGREEN", "BYELLOW", "BBLUE", "BPURPLE", "BCYAN", "BWHITE"},
            {"\u001B[30m","\u001B[31m","\u001B[32m","\u001B[33m","\u001B[34m","\u001B[35m","\u001B[36m","\u001B[37m",
                    "\u001b[90m","\u001B[91m","\u001B[92m","\u001B[93m", "\u001B94m","\u001B[95m",
                    "\u001B[96m","\u001B[97m"},
            {"\u001B[40m","\u001B[41m","\u001B[42m","\u001B[43m","\u001B[44m","\u001B[45m","\u001B[46m","\u001B[47m"}
    };

    /**
     * Checks whether the String color argument is a valid color, provided by {@link #ANSI_COLOR_NAME}
     * @param color - The desired color
     * @param code - Either "TXT" for a text color or "BG" for background color
     * @return a String of the appropriate ANSI color value
     * @throws IllegalArgumentException - If the desired color is not listed in the first
     * row of {@link #ANSI_COLOR_NAME}
     */

    private String checkColor(String color,String code) throws IllegalArgumentException {
        for (int i = 0; i < ANSI_COLOR_NAME[0].length; i++) {
            if (ANSI_COLOR_NAME[0][i].equalsIgnoreCase(color)) {
                if(code.equals("TXT")) return ANSI_COLOR_NAME[1][i];
                if(code.equals("BG")) return ANSI_COLOR_NAME[2][i];
            }
        }
        throw new IllegalArgumentException("Invalid color selection");
    }

    /**
     * This method returns the defined ANSI background color code
     * @return ANSI background color code
     */

    public String getBackgroundColor() {
        return backgroundColor;
    }

    /**
     * Method: setBackgroundColor
     * <p>This method will check if the backgroundColor argument is a verified ANSI color code, then sets the
     * backgroundColor field to that color</p>
     * @param backgroundColor Desired color
     */

    public void setBackgroundColor(String backgroundColor) {
        this.backgroundColor = checkColor(backgroundColor,"BG");
    }

    public String colorString(String msg) {
        return (textColor != null ? textColor : "") +
                (backgroundColor != null ? backgroundColor : "") +
                msg + ANSI_RESET;
    }

    /**
     * Method: getANSI_RESET
     * <p>This method resets the ANSI text color to its original hue</p>
     * @return Standard ANSI color
     */

    public String getANSI_RESET() {
        return ANSI_RESET;
    }
    /**
     * Method: getTextColor
     * <p>This method returns the defined ANSI text color code</p>
     * @return ANSI text color code
     */

    public String getTextColor() {
        return textColor;
    }

    /**
     * Method: setTextColor
     * <p>This method will check if the textColor argument is a verified ANSI color code, then sets the
     * textColor field to that color</p>
     * @param textColor Desired color
     */

    public void setTextColor(String textColor) {
        this.textColor = checkColor(textColor,"TXT");
    }

    /**
     * Returns a String of text with the appended ANSI color code to change the text color
     * @return a String with a varied color
     */
    public String textColor(String msg) {
        return textColor + msg + ANSI_RESET;
    }
    /**
     * Method: toString
     * <p>Creates default message of the field values when object is called</p>
     * @return Summary of fields
     */

    public String toString() {
        return "ConsoleColors{" +
                "backgroundColor='" + backgroundColor + '\'' +
                ", textColor='" + textColor + '\'' +
                ", ANSI_COLOR_NAME=" + Arrays.toString(ANSI_COLOR_NAME) +
                '}';
    }
}
