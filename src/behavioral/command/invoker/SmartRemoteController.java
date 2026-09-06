package behavioral.command.invoker;

import behavioral.command.commands.Command;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Invoker: SmartRemoteController.
 * Holds slots with assigned commands and maintains execution history for Undo / Redo support.
 */
public class SmartRemoteController {
    private final Command[] slots;
    private final Deque<Command> undoStack = new ArrayDeque<>();
    private final Deque<Command> redoStack = new ArrayDeque<>();

    public SmartRemoteController(int slotCount) {
        this.slots = new Command[slotCount];
    }

    public void setCommand(int slot, Command command) {
        if (slot >= 0 && slot < slots.length) {
            slots[slot] = command;
        } else {
            throw new IndexOutOfBoundsException("Invalid slot index: " + slot);
        }
    }

    /**
     * Executes the command in the requested slot and records it for undo.
     */
    public void pressButton(int slot) {
        if (slot < 0 || slot >= slots.length || slots[slot] == null) {
            System.out.printf("[Remote] Slot %d is unassigned.%n", slot);
            return;
        }

        Command command = slots[slot];
        System.out.printf("[Remote] Pressed Button %d -> Executing: \"%s\"%n", slot, command.getDescription());
        command.execute();

        undoStack.push(command);
        redoStack.clear(); // Clear redo stack on new action
    }

    /**
     * Executes an arbitrary command on demand (not bound to a fixed slot).
     */
    public void executeCommand(Command command) {
        if (command == null) return;
        System.out.printf("[Remote] Executing: \"%s\"%n", command.getDescription());
        command.execute();
        undoStack.push(command);
        redoStack.clear();
    }

    /**
     * Undo the last executed command.
     */
    public void pressUndo() {
        if (undoStack.isEmpty()) {
            System.out.println("[Remote Undo] No commands in history to undo.");
            return;
        }

        Command command = undoStack.pop();
        System.out.printf("[Remote Undo] Undoing: \"%s\"%n", command.getDescription());
        command.undo();
        redoStack.push(command);
    }

    /**
     * Redo the last undone command.
     */
    public void pressRedo() {
        if (redoStack.isEmpty()) {
            System.out.println("[Remote Redo] No commands in history to redo.");
            return;
        }

        Command command = redoStack.pop();
        System.out.printf("[Remote Redo] Redoing: \"%s\"%n", command.getDescription());
        command.execute();
        undoStack.push(command);
    }
}
