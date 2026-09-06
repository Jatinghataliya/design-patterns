package structural.bridge;

import structural.bridge.devices.Device;
import structural.bridge.devices.PhilipsRadio;
import structural.bridge.devices.SonyTv;
import structural.bridge.remotes.AdvancedRemote;
import structural.bridge.remotes.BasicRemote;
import structural.bridge.remotes.SmartTouchRemote;

/**
 * Demo runner for Bridge Pattern.
 */
public class BridgeDemo {

    public static void main(String[] args) {
        System.out.println("==================================================");
        System.out.println("            BRIDGE DESIGN PATTERN DEMO            ");
        System.out.println("     Decoupling Remotes (Abstraction) from        ");
        System.out.println("         Devices (Implementation Platform)        ");
        System.out.println("==================================================\n");

        // 1. Concrete Implementation Platforms (Devices)
        Device sonyTv = new SonyTv();
        Device philipsRadio = new PhilipsRadio();

        // 2. Pair 1: Basic Remote controlling Sony TV
        System.out.println("--- 1. Basic Remote + Sony TV ---");
        BasicRemote basicTvRemote = new BasicRemote(sonyTv);
        basicTvRemote.togglePower();
        basicTvRemote.volumeUp();
        basicTvRemote.channelUp();
        basicTvRemote.channelUp();

        // 3. Pair 2: Advanced Remote (with Mute) controlling Philips Radio
        System.out.println("\n--- 2. Advanced Remote (with Mute) + Philips Radio ---");
        AdvancedRemote advancedRadioRemote = new AdvancedRemote(philipsRadio);
        advancedRadioRemote.togglePower();
        advancedRadioRemote.volumeUp();
        advancedRadioRemote.channelUp();
        advancedRadioRemote.mute(); // Mute radio
        advancedRadioRemote.mute(); // Unmute radio

        // 4. Pair 3: Smart Voice Remote controlling Sony TV
        System.out.println("\n--- 3. Smart Touch Remote (with Voice AI) + Sony TV ---");
        SmartTouchRemote smartRemote = new SmartTouchRemote(sonyTv);
        smartRemote.voiceCommand("Switch to sports channel");
        smartRemote.voiceCommand("Make it louder");
        smartRemote.quickPreset(99);

        // 5. Cross-platform flexibility: Same Smart Remote now plugged into Philips Radio
        System.out.println("\n--- 4. Same Smart Remote used with Philips Radio (Zero code change) ---");
        SmartTouchRemote smartRadioRemote = new SmartTouchRemote(philipsRadio);
        smartRadioRemote.voiceCommand("Switch to news");
    }
}
