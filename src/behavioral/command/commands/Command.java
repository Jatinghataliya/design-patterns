package behavioral.command.commands;

/**
 * Command Interface.
 * Declares execution and undo methods.
 */
public interface Command {
    void execute();
    void undo();
    String getDescription();
}
