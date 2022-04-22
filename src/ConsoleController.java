public class ConsoleController {

    private Console console;
    private ConsoleView consoleView;

    public ConsoleController(Console model, ConsoleView view) {
        this.console = model;
        this.consoleView = view;
    }

    public void enterCommand() {
        while (!console.getInput().equalsIgnoreCase("exit")) {
            console.enterCommand();

            switch (console.inputValidator()) {
                
            }
        }
    }
}
