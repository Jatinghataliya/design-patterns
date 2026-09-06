package structural.bridge.remotes;

import structural.bridge.devices.Device;

/**
 * Abstraction: Basic Remote Control.
 * Holds a reference to the Device implementation (the "Bridge").
 * High-level control logic delegates actual hardware actions to the Device.
 */
public class BasicRemote {
    protected final Device device;

    public BasicRemote(Device device) {
        this.device = device;
    }

    public void togglePower() {
        System.out.printf("[Basic Remote] Power button pressed for [%s]...%n", device.getDeviceName());
        if (device.isEnabled()) {
            device.disable();
        } else {
            device.enable();
        }
    }

    public void volumeDown() {
        System.out.printf("[Basic Remote] Volume [-] pressed for [%s]...%n", device.getDeviceName());
        device.setVolume(device.getVolume() - 10);
    }

    public void volumeUp() {
        System.out.printf("[Basic Remote] Volume [+] pressed for [%s]...%n", device.getDeviceName());
        device.setVolume(device.getVolume() + 10);
    }

    public void channelDown() {
        System.out.printf("[Basic Remote] Channel [v] pressed for [%s]...%n", device.getDeviceName());
        device.setChannel(device.getChannel() - 1);
    }

    public void channelUp() {
        System.out.printf("[Basic Remote] Channel [^] pressed for [%s]...%n", device.getDeviceName());
        device.setChannel(device.getChannel() + 1);
    }

    public Device getDevice() {
        return device;
    }
}
