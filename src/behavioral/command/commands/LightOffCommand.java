package behavioral.command.commands;

import behavioral.command.receiver.Light;

/**
 * Concrete Command: Light Off Command.
 */
public class LightOffCommand implements Command {
    private final Light light;

    public LightOffCommand(Light light) {
        this.light = light;
    }

    @Override
    public void execute() {
        light.turnOff();
    }

    @Override
    public void undo() {
        light.turnOn();
    }

    @Override
    public String getDescription() {
        return "Turn OFF light (" + light.getLocation() + ")";
    }
}
