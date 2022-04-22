public class Entry {
    public static void main(String[] args) {
        Console console = new Console();
        ConsoleView consoleView = new ConsoleView();
        ConsoleController consoleController =
                new ConsoleController(console, consoleView);
        consoleController.enterCommand();
    }
}
