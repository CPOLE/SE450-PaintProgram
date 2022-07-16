package controller;

public class UndoAction implements ICommand {
    public void run() {
        CommandHistory.undo();
    }
}
