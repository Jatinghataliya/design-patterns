package behavioral.command;

import behavioral.command.commands.*;
import behavioral.command.invoker.SmartRemoteController;
import behavioral.command.receiver.AirConditioner;
import behavioral.command.receiver.Light;

import java.util.Arrays;

/**
 * Demo runner for Command Pattern.
 */
public class CommandDemo {

    public static void main(String[] args) {
        System.out.println("==================================================");
        System.out.println("           COMMAND DESIGN PATTERN DEMO            ");
        System.out.println("==================================================\n");

        // 1. Receivers
        Light livingRoomLight = new Light("Living Room");
        Light bedroomLight = new Light("Bedroom");
        AirConditioner ac = new AirConditioner("Living Room");

        // 2. Invoker (Remote with 5 buttons)
        SmartRemoteController remote = new SmartRemoteController(5);

        // 3. Concrete Commands
        Command livingRoomLightOn = new LightOnCommand(livingRoomLight);
        Command livingRoomLightOff = new LightOffCommand(livingRoomLight);
        Command dimLivingRoomLight = new DimLightCommand(livingRoomLight, 40);

        Command acOn = new Command() {
            @Override
            public void execute() { ac.turnOn(); }
            @Override
            public void undo() { ac.turnOff(); }
            @Override
            public String getDescription() { return "Turn ON AC (Living Room)"; }
        };
        Command setAcCool = new SetACTempCommand(ac, 19);

        // 4. Assign commands to remote slots
        remote.setCommand(0, livingRoomLightOn);
        remote.setCommand(1, livingRoomLightOff);
        remote.setCommand(2, dimLivingRoomLight);
        remote.setCommand(3, acOn);
        remote.setCommand(4, setAcCool);

        // 5. Test button presses and execution
        System.out.println("--- 1. Executing Remote Slot Commands ---");
        remote.pressButton(0); // Turn ON Light
        remote.pressButton(2); // Dim Light to 40%
        remote.pressButton(3); // Turn ON AC
        remote.pressButton(4); // Set AC temp to 19C

        // 6. Test Undo operations
        System.out.println("\n--- 2. Testing Step-by-Step UNDO Operations ---");
        remote.pressUndo(); // Undo set AC temp (restores 24C)
        remote.pressUndo(); // Undo AC on (turns off AC)
        remote.pressUndo(); // Undo Dim light (restores 100% brightness)

        // 7. Test Redo operation
        System.out.println("\n--- 3. Testing REDO Operation ---");
        remote.pressRedo(); // Redo Dim light to 40%

        // 8. Test Macro Command (Good Night Routine)
        System.out.println("\n--- 4. Macro Command: 'Good Night Mode' ---");
        Command turnOffBedroomLight = new LightOffCommand(bedroomLight);
        Command setAcNightTemp = new SetACTempCommand(ac, 22);

        // Turn on bedroom light first so we can watch it turn off
        bedroomLight.turnOn();

        MacroCommand goodNightRoutine = new MacroCommand("Good Night Routine", Arrays.asList(
                livingRoomLightOff,
                turnOffBedroomLight,
                setAcNightTemp
        ));

        remote.executeCommand(goodNightRoutine);

        System.out.println("\n--- 5. Undoing Entire Macro ---");
        remote.pressUndo(); // Undoes all 3 actions in reverse order
    }
}
