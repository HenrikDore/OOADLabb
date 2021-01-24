package shop.history;

public class HistoryState {
    Command undo;
    Command redo;

    public HistoryState(Command h1, Command h2) {
        this.undo = h1;
        this.redo = h2;
    }
}