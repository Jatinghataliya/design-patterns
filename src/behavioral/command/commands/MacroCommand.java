package behavioral.command.commands;

import java.util.ArrayList;
import java.util.List;

/**
 * Macro Command (Composite Command).
 * Executes a sequence of multiple commands as a single atomic macro (e.g. "Good Night Mode").
 */
public class MacroCommand implements Command {
    private final String name;
    private final List<Command> commands;

    public MacroCommand(String name, List<Command> commands) {
        this.name = name;
        this.commands = new ArrayList<>(commands);
    }

    @Override
    public void execute() {
        System.out.printf("  >>> Running Macro: '%s' (%d commands) <<<%n", name, commands.size());
        for (Command cmd : commands) {
            cmd.execute();
        }
    }

    @Override
    public void undo() {
        System.out.printf("  >>> Undoing Macro: '%s' in reverse order <<<%n", name);
        // Undo commands in reverse execution order
        for (int i = commands.size() - 1; i >= 0; i--) {
            commands.get(i).undo();
        }
    }

    @Override
    public String getDescription() {
        return "Macro: " + name;
    }
}
