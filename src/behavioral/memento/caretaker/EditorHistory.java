package behavioral.memento.caretaker;

import behavioral.memento.editor.EditorState;
import behavioral.memento.editor.TextEditor;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Caretaker: EditorHistory / HistoryManager
 * Keeps track of Memento snapshots without ever reading, modifying, or inspecting their contents.
 * Provides multi-level Undo and Redo capabilities.
 */
public class EditorHistory {
    private final TextEditor editor;
    private final Deque<EditorState> undoStack = new ArrayDeque<>();
    private final Deque<EditorState> redoStack = new ArrayDeque<>();

    public EditorHistory(TextEditor editor) {
        this.editor = editor;
    }

    /**
     * Creates and saves a checkpoint snapshot of the editor.
     */
    public void backup(String actionDescription) {
        EditorState snapshot = editor.saveState();
        undoStack.push(snapshot);
        redoStack.clear(); // Clear redo on new modification
        System.out.printf("  [History Backup] Saved checkpoint after '%s' -> %s%n", actionDescription, snapshot);
    }

    /**
     * Rolls back editor state to the previous snapshot.
     */
    public void undo() {
        if (undoStack.isEmpty()) {
            System.out.println("  [History Undo] No previous checkpoints to undo.");
            return;
        }

        // Save current state to redo stack before reverting
        redoStack.push(editor.saveState());

        EditorState previousState = undoStack.pop();
        System.out.printf("  [History Undo] Reverting back to -> %s%n", previousState);
        editor.restoreState(previousState);
    }

    /**
     * Re-applies the next undone snapshot.
     */
    public void redo() {
        if (redoStack.isEmpty()) {
            System.out.println("  [History Redo] No checkpoints in redo history.");
            return;
        }

        // Save current state to undo stack before redoing
        undoStack.push(editor.saveState());

        EditorState nextState = redoStack.pop();
        System.out.printf("  [History Redo] Re-applying state -> %s%n", nextState);
        editor.restoreState(nextState);
    }

    public int getUndoCount() {
        return undoStack.size();
    }

    public int getRedoCount() {
        return redoStack.size();
    }
}
