package structural.bridge.remotes;

import structural.bridge.devices.Device;

/**
 * Refined Abstraction 2: Smart Touch Remote Control with Voice Command.
 * Extends BasicRemote with voice recognition and favorite quick-launch capabilities.
 */
public class SmartTouchRemote extends BasicRemote {

    public SmartTouchRemote(Device device) {
        super(device);
    }

    public void voiceCommand(String query) {
        System.out.printf("[Smart Remote Voice] Processing prompt: \"%s\" for [%s]...%n",
                query, device.getDeviceName());

        if (query.toLowerCase().contains("sports") || query.toLowerCase().contains("espn")) {
            System.out.println("  [Voice Engine] Recognized: Switch to Sports Channel.");
            device.setChannel(7);
        } else if (query.toLowerCase().contains("news")) {
            System.out.println("  [Voice Engine] Recognized: Switch to News Channel.");
            device.setChannel(4);
        } else if (query.toLowerCase().contains("loud")) {
            System.out.println("  [Voice Engine] Recognized: Boost volume.");
            device.setVolume(device.getVolume() + 25);
        } else {
            System.out.println("  [Voice Engine] Command executed.");
        }
    }

    public void quickPreset(int presetChannel) {
        System.out.printf("[Smart Remote] Quick Preset button tapped -> Channel #%d%n", presetChannel);
        device.setChannel(presetChannel);
    }
}
