package behavioral.command.commands;

import behavioral.command.receiver.Light;

/**
 * Concrete Command: Light On Command.
 */
public class LightOnCommand implements Command {
    private final Light light;

    public LightOnCommand(Light light) {
        this.light = light;
    }

    @Override
    public void execute() {
        light.turnOn();
    }

    @Override
    public void undo() {
        light.turnOff();
    }

    @Override
    public String getDescription() {
        return "Turn ON light (" + light.getLocation() + ")";
    }
}
