import java.util.Scanner;

public class Console {
    private String input = "";

    public String getInput() {
        return input;
    }

    public void enterCommand() {
        input = "";
        Scanner scanner = new Scanner(System.in);
        input = scanner.nextLine().toLowerCase();
    }
}
