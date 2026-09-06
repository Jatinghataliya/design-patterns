package structural.bridge.remotes;

import structural.bridge.devices.Device;

/**
 * Refined Abstraction 1: Advanced Remote Control.
 * Extends BasicRemote with extra feature: Mute functionality.
 */
public class AdvancedRemote extends BasicRemote {
    private int volumeBeforeMute = 0;
    private boolean isMuted = false;

    public AdvancedRemote(Device device) {
        super(device);
    }

    public void mute() {
        System.out.printf("[Advanced Remote] MUTE button pressed for [%s]...%n", device.getDeviceName());
        if (!isMuted) {
            volumeBeforeMute = device.getVolume();
            device.setVolume(0);
            isMuted = true;
            System.out.printf("  [Advanced Remote] Audio MUTED (was %d%%)%n", volumeBeforeMute);
        } else {
            device.setVolume(volumeBeforeMute);
            isMuted = false;
            System.out.printf("  [Advanced Remote] Audio UNMUTED (restored to %d%%)%n", volumeBeforeMute);
        }
    }
}
