package behavioral.command.commands;

import behavioral.command.receiver.Light;

/**
 * Concrete Command: Dim / Set Light Brightness Command with Undo state capture.
 */
public class DimLightCommand implements Command {
    private final Light light;
    private final int newBrightness;
    private int previousBrightness;

    public DimLightCommand(Light light, int newBrightness) {
        this.light = light;
        this.newBrightness = newBrightness;
    }

    @Override
    public void execute() {
        // Save previous state before modifying receiver
        this.previousBrightness = light.getBrightness();
        light.setBrightness(newBrightness);
    }

    @Override
    public void undo() {
        System.out.printf("  [Undo Dim Light] Restoring brightness to previous level: %d%%%n", previousBrightness);
        light.setBrightness(previousBrightness);
    }

    @Override
    public String getDescription() {
        return "Set light (" + light.getLocation() + ") brightness to " + newBrightness + "%";
    }
}
