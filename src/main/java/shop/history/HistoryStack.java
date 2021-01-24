package shop.history;

import java.util.Stack;

public class HistoryStack {

    static Stack<HistoryState> redoStack = new Stack<>();
    static Stack<HistoryState> undoStack = new Stack<>();


    public static void undo(){
        int index = undoStack.size() - 1;
        undoStack.get(index).undo.execute();
        redoStack.push(undoStack.peek());
        undoStack.pop();

    }

    public static void redo() {
        if (redoStack.size() > 0) {
            undoStack.push(redoStack.peek());
            redoStack.pop().redo.execute();
        }
    }

    public static void addHistoryState(HistoryState historyState) {
        undoStack.push(historyState);
        redoStack.clear();
    }
}