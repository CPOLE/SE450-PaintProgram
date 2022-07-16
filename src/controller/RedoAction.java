package controller;

public class RedoAction implements ICommand {
    public void run() {
        CommandHistory.redo();
    }
}
