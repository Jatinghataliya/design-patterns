package behavioral.command.commands;

import behavioral.command.receiver.AirConditioner;

/**
 * Concrete Command: Set AC Temperature Command with Undo state capture.
 */
public class SetACTempCommand implements Command {
    private final AirConditioner ac;
    private final int newTemp;
    private int prevTemp;

    public SetACTempCommand(AirConditioner ac, int newTemp) {
        this.ac = ac;
        this.newTemp = newTemp;
    }

    @Override
    public void execute() {
        this.prevTemp = ac.getTemperature();
        ac.setTemperature(newTemp);
    }

    @Override
    public void undo() {
        System.out.printf("  [Undo AC Temp] Restoring temperature to previous level: %d°C%n", prevTemp);
        ac.setTemperature(prevTemp);
    }

    @Override
    public String getDescription() {
        return "Set AC (" + ac.getLocation() + ") temp to " + newTemp + "°C";
    }
}
