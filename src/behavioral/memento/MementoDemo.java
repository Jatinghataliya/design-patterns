package behavioral.memento;

import behavioral.memento.caretaker.EditorHistory;
import behavioral.memento.editor.TextEditor;

/**
 * Demo runner for Memento Pattern.
 */
public class MementoDemo {

    public static void main(String[] args) {
        System.out.println("==================================================");
        System.out.println("           MEMENTO DESIGN PATTERN DEMO            ");
        System.out.println("       Rich Text Editor Snapshot Rollback         ");
        System.out.println("==================================================\n");

        // 1. Initialize Originator (TextEditor) and Caretaker (EditorHistory)
        TextEditor editor = new TextEditor();
        EditorHistory history = new EditorHistory(editor);

        System.out.println("--- 1. Authoring Document & Creating Checkpoints ---");
        // State 0: Initial empty state
        history.backup("Initial Empty Document");

        // Action 1: Type paragraph 1
        editor.type("Design patterns are proven architectural solutions.");
        editor.setFormat("Bold", 14);
        System.out.println("Current: " + editor);
        history.backup("Added Paragraph 1");

        // Action 2: Type paragraph 2
        editor.type(" They make code reusable and maintainable.");
        editor.setFormat("Italic", 12);
        System.out.println("Current: " + editor);
        history.backup("Added Paragraph 2");

        // Action 3: Type paragraph 3 (Unwanted change / mistake)
        editor.type(" Random text with typos: asdkfjhsdf!");
        editor.setFormat("Underline", 10);
        System.out.println("Current (with typos): " + editor);

        // 2. Testing Multi-level Undo operations
        System.out.println("\n--- 2. Step-by-Step UNDO Operations ---");
        System.out.println("Undoing mistake...");
        history.undo(); // Reverts typos, restores Paragraph 2 state
        System.out.println("Current: " + editor);

        System.out.println("\nUndoing Paragraph 2...");
        history.undo(); // Reverts Paragraph 2, restores Paragraph 1 state
        System.out.println("Current: " + editor);

        System.out.println("\nUndoing Paragraph 1...");
        history.undo(); // Reverts to initial empty document
        System.out.println("Current: " + editor);

        // 3. Testing REDO operations
        System.out.println("\n--- 3. Step-by-Step REDO Operations ---");
        history.redo(); // Restores Paragraph 1
        System.out.println("Current: " + editor);

        history.redo(); // Restores Paragraph 2
        System.out.println("Current: " + editor);

        // 4. Making a new edit invalidates previous redo history
        System.out.println("\n--- 4. New Edits Clear Redo History ---");
        editor.type(" [NEW FINAL ADDITION] - The End.");
        history.backup("Added Final Statement");
        System.out.println("Current: " + editor);

        System.out.printf("%nAvailable Undo checkpoints: %d, Redo checkpoints: %d%n",
                history.getUndoCount(), history.getRedoCount());
    }
}
